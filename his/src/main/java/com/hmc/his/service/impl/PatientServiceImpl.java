package com.hmc.his.service.impl;

import com.hmc.his.model.Patient;
import com.hmc.his.repository.PatientRepository;
import com.hmc.his.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients(){
        log.error("error,msg={}", "查询所有病人时出现故障！");
        return patientRepository.selectPatients();
    }

    public List<String> getPatientNames(){
        return patientRepository.selectPatientNames();
    }

    public Patient getPatient(Integer id){
        return patientRepository.selectPatientById(id);
    }

    public int addPatients(Patient patient){
        return patientRepository.insertPatients(patient);
    }

    @Override
    public int modifyPatients(Patient patient) {
        return patientRepository.updatePatients(patient);
    }

    @Override
    public int removePatients(Integer id) {
        return patientRepository.deletePatients(id);
    }
}
