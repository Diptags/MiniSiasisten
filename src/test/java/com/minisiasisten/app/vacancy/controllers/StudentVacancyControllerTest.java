package com.minisiasisten.app.vacancy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minisiasisten.app.vacancy.dto.CreateStudentVacancyDto;
import com.minisiasisten.app.vacancy.models.StudentVacancy;
import com.minisiasisten.app.vacancy.services.StudentVacancyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentVacancyControllerTest {

    @InjectMocks
    StudentVacancyController studentVacancyController;

    @Mock
    StudentVacancyService studentVacancyService;

    private MockMvc mockMvc;

    @Before
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentVacancyController).build();
    }

    @Test
    public void testGetAllStudentVacancy_vacancyAvailabe() throws Exception {
        List<StudentVacancy> daftarLowongan = new ArrayList<>();
        daftarLowongan.add(new StudentVacancy());

        Mockito.when(studentVacancyService.getAllVacancies()).thenReturn(daftarLowongan);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/vacancy/students/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)));

        Mockito.verify(studentVacancyService, Mockito.times(1)).getAllVacancies();
        Mockito.verifyNoMoreInteractions(studentVacancyService);
    }

    @Test
    public void testGetAllStudentVacancy_noVacancyAvailabe() throws Exception {
        List<StudentVacancy> daftarLowongan = new ArrayList<>();

        Mockito.when(studentVacancyService.getAllVacancies()).thenReturn(daftarLowongan);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/vacancy/students/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(studentVacancyService, Mockito.times(1)).getAllVacancies();
        Mockito.verifyNoMoreInteractions(studentVacancyService);
    }

    @Test
    public void testGetStudentVacancyById_success() throws Exception {
        StudentVacancy lowongan = new StudentVacancy();
        lowongan.setIdLowongan("VACANCY001");

        Mockito.when(studentVacancyService.getVacancyById("VACANCY001")).thenReturn(lowongan);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/vacancy/students/{idLowongan}", "VACANCY001"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        Mockito.verify(studentVacancyService, Mockito.times(1)).getVacancyById(eq("VACANCY001"));
        Mockito.verifyNoMoreInteractions(studentVacancyService);
    }

    @Test
    public void testGetStudentVacancyById_notFound() throws Exception {
        Mockito.when(studentVacancyService.getVacancyById("VACANCY001")).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/vacancy/students/{idLowongan}", "VACANCY001"))
                .andExpect(status().isNotFound());

        Mockito.verify(studentVacancyService, Mockito.times(1)).getVacancyById(eq("VACANCY001"));
        Mockito.verifyNoMoreInteractions(studentVacancyService);
    }

    @Test
    public void testCreateStudentVacancy_success() throws Exception{
        StudentVacancy lowongan = new StudentVacancy();

        CreateStudentVacancyDto dto = new CreateStudentVacancyDto("VACANCY001", "COURSE001", 1L);
        Mockito.when(studentVacancyService.createVacancy(dto)).thenReturn(lowongan);

        // Mapping objek ke JSON String
        ObjectMapper mapper = new ObjectMapper();
        String lowonganJson = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/vacancy/students/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lowonganJson))
                .andExpect(status().isOk());

        Mockito.verify(studentVacancyService, Mockito.times(1)).createVacancy(refEq(dto));
        Mockito.verifyNoMoreInteractions(studentVacancyService);
    }

    @Test
    public void testUpdateStudentVacancy_success() throws Exception{
        StudentVacancy lowongan = new StudentVacancy();

        CreateStudentVacancyDto dto = new CreateStudentVacancyDto("VACANCY001", "COURSE001", 1L);
        Mockito.when(studentVacancyService.updateVacancy("VACANCY001", dto)).thenReturn(lowongan);

        // Mapping objek ke JSON String
        ObjectMapper mapper = new ObjectMapper();
        String lowonganJson = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/vacancy/students/{idLowongan}/update", "VACANCY001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lowonganJson))
                .andExpect(status().isOk());

        Mockito.verify(studentVacancyService, Mockito.times(1)).updateVacancy(eq("VACANCY001"), refEq(dto));
        Mockito.verifyNoMoreInteractions(studentVacancyService);
    }

    @Test
    public void testUpdateStudentVacancy_notFound() throws Exception{
        CreateStudentVacancyDto dto = new CreateStudentVacancyDto("VACANCY001", "COURSE001", 1L);

        Mockito.when(studentVacancyService.updateVacancy(ArgumentMatchers.eq("VACANCY001"), ArgumentMatchers.any(CreateStudentVacancyDto.class))).thenThrow(ResourceNotFoundException.class);

        // Mapping objek ke JSON String
        ObjectMapper mapper = new ObjectMapper();
        String lowonganJson = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/vacancy/students/{idLowongan}/update", "VACANCY001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lowonganJson))
                .andExpect(status().isNotFound());

        Mockito.verify(studentVacancyService, Mockito.times(1)).updateVacancy(eq("VACANCY001"), refEq(dto));
        Mockito.verifyNoMoreInteractions(studentVacancyService);
    }

    @Test
    public void testDeleteStudentVacancy_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/vacancy/students/{idLowongan}/delete", "VACANCY001"))
                .andExpect(status().isOk());

        Mockito.verify(studentVacancyService).deleteVacancy("VACANCY001");
    }

    @Test
    public void testDeleteCourse_notFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(studentVacancyService).deleteVacancy("VACANCY001");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/vacancy/students/{idLowongan}/delete", "VACANCY001"))
                .andExpect(status().isNotFound());

        Mockito.verify(studentVacancyService).deleteVacancy("VACANCY001");
    }
}
