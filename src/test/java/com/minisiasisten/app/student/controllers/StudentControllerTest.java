package com.minisiasisten.app.student.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minisiasisten.app.student.dto.CreateStudentDto;
import com.minisiasisten.app.student.models.Student;
import com.minisiasisten.app.student.services.StudentService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    private MockMvc mockMvc;

    @Before
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testGetAllStudents_studentAvailabe() throws Exception {
        List<Student> daftarMahasiswa = new ArrayList<>();
        daftarMahasiswa.add(new Student());

        Mockito.when(studentService.getAllStudents()).thenReturn(daftarMahasiswa);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/students/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)));

        Mockito.verify(studentService, Mockito.times(1)).getAllStudents();
        Mockito.verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testGetAllStudents_noStudentAvailabe() throws Exception {
        List<Student> daftarMahasiswa = new ArrayList<>();

        Mockito.when(studentService.getAllStudents()).thenReturn(daftarMahasiswa);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/students/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(studentService, Mockito.times(1)).getAllStudents();
        Mockito.verifyNoMoreInteractions(studentService);
    }


    @Test
    public void testGetStudentById_success() throws Exception {
        Student mahasiswa = new Student();
        mahasiswa.setNpmMahasiswa(1L);

        Mockito.when(studentService.getStudentById(1L)).thenReturn(mahasiswa);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/students/{npmMahasiswa}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        Mockito.verify(studentService, Mockito.times(1)).getStudentById(eq(1L));
        Mockito.verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testGetStudentById_notFound() throws Exception {
        Mockito.when(studentService.getStudentById(1L)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/students/{npmMahasiswa}", 1))
                .andExpect(status().isNotFound());

        Mockito.verify(studentService, Mockito.times(1)).getStudentById(eq(1L));
        Mockito.verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testCreateStudent_success() throws Exception {
        Student mahasiswa = new Student();
        CreateStudentDto dto = new CreateStudentDto(1L, "Doraemon", "Ilmu Komputer", "S1", 2L, 3F, 10L);

        Mockito.when(studentService.createStudent(dto)).thenReturn(mahasiswa);

        // Mapping objek ke JSON String
        ObjectMapper mapper = new ObjectMapper();
        String mahasiswaJson = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/students/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mahasiswaJson))
                .andExpect(status().isOk());

        Mockito.verify(studentService, Mockito.times(1)).createStudent(refEq(dto));
        Mockito.verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testUpdateStudent_success() throws Exception {
        Student mahasiswa = new Student();
        CreateStudentDto dto = new CreateStudentDto(1L, "Doraemon", "Ilmu Komputer", "S1", 2L, 3F, 10L);

        Mockito.when(studentService.updateStudent(1L, dto)).thenReturn(mahasiswa);

        // Mapping objek ke JSON String
        ObjectMapper mapper = new ObjectMapper();
        String mahasiswaJson = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/students/{npmMahasiswa}/update", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mahasiswaJson))
                .andExpect(status().isOk());

        Mockito.verify(studentService, Mockito.times(1)).updateStudent(eq(1L), refEq(dto));
        Mockito.verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testUpdateStudent_notFound() throws Exception {
        CreateStudentDto dto = new CreateStudentDto(1L, "Doraemon", "Ilmu Komputer", "S1", 2L, 3F, 10L);

        Mockito.when(studentService.updateStudent(ArgumentMatchers.eq(1L), ArgumentMatchers.any(CreateStudentDto.class))).thenThrow(ResourceNotFoundException.class);

        // Mapping objek ke JSON String
        ObjectMapper mapper = new ObjectMapper();
        String mahasiswaJson = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/students/{npmMahasiswa}/update", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mahasiswaJson))
                .andExpect(status().isNotFound());

        Mockito.verify(studentService, Mockito.times(1)).updateStudent(ArgumentMatchers.eq(1L), ArgumentMatchers.any(CreateStudentDto.class));
        Mockito.verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testDeleteStudent_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/students/{npmMahasiswa}/delete", 1))
                .andExpect(status().isOk());

        Mockito.verify(studentService).deleteStudent(1L);
    }

    @Test
    public void testDeleteCourse_notFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(studentService).deleteStudent(1L);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/students/{npmMahasiswa}/delete", 1))
                .andExpect(status().isNotFound());

        Mockito.verify(studentService).deleteStudent(1L);
    }
}
