package com.lilbaek.clinic.management.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lilbaek.clinic.management.db.DoctorDbEntry;
import com.lilbaek.clinic.management.repository.DoctorRepository;
import com.lilbaek.clinic.management.resource.model.CreateDoctorModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class DoctorIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void doctor_isAdded_whenPostIsCalled() throws Exception {
        CreateDoctorModel doctor = new CreateDoctorModel();
        doctor.setName("Doctor1");

        mockMvc.perform(post("/doctors")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isOk());

        var entries = doctorRepository.findAll();
        assertEquals("Doctor1", StreamSupport.stream(entries.spliterator(), false)
                .findFirst()
                .map(DoctorDbEntry::getName)
                .orElse(""));
    }
}
