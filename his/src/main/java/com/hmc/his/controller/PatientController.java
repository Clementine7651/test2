package com.hmc.his.controller;

import com.hmc.his.config.PulsarConfig;
import com.hmc.his.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.hmc.his.model.Patient;

@Slf4j
@RestController
public class PatientController {
    private final  PatientService patientService;

    private final PulsarConfig pulsarConfig;

    public PatientController(PatientService patientService, PulsarConfig pulsarConfig){
        this.patientService = patientService;
        this.pulsarConfig = pulsarConfig;
    }

    @GetMapping("patients")
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping("patients/names")
    public List<String> getPatientNames(){
        return patientService.getPatientNames();
    }

    @Cacheable(value = "patients", key = "#id")
    @GetMapping("patients/{id}")
    public Patient getPatient(@PathVariable("id") Integer id){
        return patientService.getPatient(id);
    }

    @CachePut(value = "patients", key = "#patient.id")
    @PostMapping("patients")
    public Patient addPatients(@RequestBody Patient patient){
        patientService.addPatients(patient);
        return patient;
    }

    @CacheEvict(value = "patients", key = "#patient.id")
    @PutMapping("patients")
    public int modifyPatients(@RequestBody Patient patient){
        return patientService.modifyPatients(patient);
    }

    @CacheEvict(value = "patients", key = "#id")
    @DeleteMapping("patients/{id}")
    public int removePatients(@PathVariable("id") Integer id){
        return patientService.removePatients(id);
    }

    @GetMapping("/patients/send")
    public MessageId send(String msg) throws PulsarClientException {
        PulsarClient pulsarFactory = pulsarConfig.pulsarFactory();
        Producer<byte[]> producer1 = pulsarFactory.newProducer()
                .topic("his-topic")
                .create();
        return producer1.send(msg.getBytes());
    }

    @GetMapping("/patients/receive")
    public String receive() throws PulsarClientException {
        PulsarClient pulsarFactory = pulsarConfig.pulsarFactory();
        Consumer<byte[]> consumer = pulsarFactory.newConsumer()
                .topic("his-topic")
                .subscriptionName("his-subscription")
                .subscribe();
        Message<byte[]> receive = consumer.receive();
        String data = new String(receive.getData());
        log.info(data);
        consumer.acknowledge(receive);
        consumer.close();
        return data;
    }
}
