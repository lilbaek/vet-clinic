package com.lilbaek.clinic.management.integration;

import com.lilbaek.clinic.management.repository.db.DoctorDbEntry;
import com.lilbaek.clinic.management.repository.DoctorRepository;
import com.lilbaek.clinic.management.resource.model.CreateDoctorModel;
import com.lilbaek.clinic.management.resource.model.UpdateDoctorModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class DoctorIntegrationTest extends BaseIntegrationTest {


    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void doctor_isAdded_whenPostIsCalled() throws Exception {
        //Arrange
        var doctor = CreateDoctorModel.builder().name("Doctor1").build();

        //Act
        var result = doPostWithResult(doctor, DoctorDbEntry.class, "/doctors");

        //Assert
        var entries = doGetWithResult(DoctorDbEntry[].class, "/doctors");
        assertEquals("Doctor1", Arrays.stream(entries)
                .findFirst()
                .map(DoctorDbEntry::getName)
                .orElse(""));

        //Cleanup
        doDelete("/doctors/{id}", result.getId());
    }

    @Test
    void doctor_isUpdated_whenPutIsCalled() throws Exception {
        //Arrange
        var doctor = CreateDoctorModel.builder().name("Doctor1").build();
        var doctorUpdate = UpdateDoctorModel.builder().name("DoctorRenamed").build();

        //Act
        var result = doPostWithResult(doctor, DoctorDbEntry.class, "/doctors");
        doPut(doctorUpdate, "/doctors/{id}", result.getId());

        //Assert
        var entries = doGetWithResult(DoctorDbEntry[].class, "/doctors");
        assertEquals("DoctorRenamed", Arrays.stream(entries)
                .findFirst()
                .map(DoctorDbEntry::getName)
                .orElse(""));

        //Cleanup
        doDelete("/doctors/{id}", result.getId());
    }

    @Test
    void doctor_isDeleted_whenDeleteIsCalled() throws Exception {
        //Arrange
        var doctor = CreateDoctorModel.builder().name("Doctor1").build();

        //Act
        var result = doPostWithResult(doctor, DoctorDbEntry.class, "/doctors");
        doDelete("/doctors/{id}", result.getId());

        //Assert
        var entries = doctorRepository.findAll();
        assertEquals(0, StreamSupport.stream(entries.spliterator(), false)
                .count());
    }
}
