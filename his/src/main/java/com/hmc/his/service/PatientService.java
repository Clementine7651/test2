package com.hmc.his.service;

import com.hmc.his.model.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

public interface PatientService {
    public List<Patient> getPatients();
    public List<String> getPatientNames();
    public Patient getPatient(Integer id);
    public int addPatients(Patient patient);
    public int modifyPatients(Patient patient);
    public int removePatients(Integer id);
}
