package com.hmc.his.service;

import com.hmc.his.model.Patient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Test
    @Order(1)
    void addPatients() {
        int i = patientService.addPatients(new Patient(999,"测试999"));
        assertEquals(1, i);
    }

    @Test
    @Order(2)
    void modifyPatients() {
        int i = patientService.modifyPatients(new Patient(999,"测试999-m"));
        assertEquals(1, i);
    }

    @Test
    @Order(3)
    void getPatients() {
        int i = patientService.getPatients().size();
        assertEquals(1, i);
    }

    @Test
    @Order(4)
    void getPatientNames() {
        String name = patientService.getPatientNames().stream().findFirst().orElse("");
        assertEquals("测试999-m", name);
    }

    @Test
    @Order(5)
    void getPatient() {
        var patient = patientService.getPatient(999);
        assertEquals("测试999-m", patient.getName());
    }

    @Test
    @Order(6)
    void removePatients() {
        int i = patientService.removePatients(999);
        assertEquals(1, i);
    }
}