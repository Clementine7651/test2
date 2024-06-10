package com.hmc.his.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmc.his.config.PulsarConfig;
import com.hmc.his.model.Patient;
import com.hmc.his.service.PatientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = PatientController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean PatientService patientService;

    @MockBean PulsarConfig pulsarConfig;

    @Test
    void getPatients() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/patients"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println(result);
        assertTrue(result.contains("["));
    }

    @Test
    void getPatientNames() throws Exception {
        Mockito.when(patientService.getPatientNames()).thenReturn(List.of("张三","李四"));

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/patients/names"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println(result);
        assertTrue(result.contains("\"张三\",\"李四\""));
    }

    @Test
    void getPatient() throws Exception {
        Mockito.when(patientService.getPatient(1)).thenReturn(new Patient(1, "张三"));

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/patients/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println(result);
        assertTrue(result.contains("张三"));
    }

    @Test
    void addPatients() throws Exception {
        var patient = new Patient(1, "张三");

        ObjectMapper objectMapper = new ObjectMapper();
        String patientJson = objectMapper.writeValueAsString(patient);

        Mockito.when(patientService.addPatients(patient)).thenReturn(1);

        String result = mockMvc.perform(MockMvcRequestBuilders.post("/patients")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(patientJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println(result);
        assertTrue(result.contains("张三"));
    }

    @Test
    void modifyPatients() {
    }

    @Test
    void removePatients() {
    }

    @Test
    void send() {
    }

    @Test
    void receive() {
    }
}