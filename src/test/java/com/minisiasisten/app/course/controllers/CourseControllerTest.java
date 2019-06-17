package com.minisiasisten.app.course.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minisiasisten.app.course.dto.CreateCourseDto;
import com.minisiasisten.app.course.models.Course;
import com.minisiasisten.app.course.services.CourseService;
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

public class CourseControllerTest {

    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    private MockMvc mockMvc;

    @Before
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    public void testGetAllCourses_courseAvailabe() throws Exception {
        List<Course> daftarMataKuliah = new ArrayList<>();
        daftarMataKuliah.add(new Course());

        Mockito.when(courseService.getAllCourses()).thenReturn(daftarMataKuliah);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/courses/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)));

        Mockito.verify(courseService, Mockito.times(1)).getAllCourses();
        Mockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    public void testGetAllCourses_noCourseAvailabe() throws Exception {
        List<Course> daftarMataKuliah = new ArrayList<>();

        Mockito.when(courseService.getAllCourses()).thenReturn(daftarMataKuliah);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/courses/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(courseService, Mockito.times(1)).getAllCourses();
        Mockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    public void testGetCourseById_success() throws Exception {
        Course mataKuliah = new Course();
        mataKuliah.setIdMataKuliah("COURSE001");

        Mockito.when(courseService.getCourseById("COURSE001")).thenReturn(mataKuliah);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/courses/COURSE001"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        Mockito.verify(courseService, Mockito.times(1)).getCourseById(eq("COURSE001"));
        Mockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    public void testGetCourseById_notFound() throws Exception {
        Mockito.when(courseService.getCourseById("COURSE100")).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/courses/COURSE100"))
                .andExpect(status().isNotFound());

        Mockito.verify(courseService, Mockito.times(1)).getCourseById(eq("COURSE100"));
        Mockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    public void testCreateCourse_success() throws Exception {
        Course mataKuliah = new Course();

        CreateCourseDto dto = new CreateCourseDto("COURSE001", "Bahasa Inggris", "Ilmu Komputer", "S1", 1L, "Mata Kuliah yang mempelajari Bahasa Inggris");
        Mockito.when(courseService.createCourse(dto)).thenReturn(mataKuliah);

        // Mapping objek ke JSON String
        ObjectMapper mapper = new ObjectMapper();
        String mataKuliahJson = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/courses/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mataKuliahJson))
                .andExpect(status().isOk());

        Mockito.verify(courseService, Mockito.times(1)).createCourse(refEq(dto));
        Mockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    public void testUpdateCourse_success() throws Exception {
        Course mataKuliah = new Course();

        CreateCourseDto dto = new CreateCourseDto("COURSE001", "Bahasa Inggris", "Ilmu Komputer", "S1", 1L, "Mata Kuliah yang mempelajari Bahasa Inggris");
        Mockito.when(courseService.updateCourse("COURSE001", dto)).thenReturn(mataKuliah);

        // Mapping objek ke JSON String
        ObjectMapper mapper = new ObjectMapper();
        String mataKuliahJson = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/courses/{idMataKuliah}/update", "COURSE001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mataKuliahJson))
                .andExpect(status().isOk());

        Mockito.verify(courseService, Mockito.times(1)).updateCourse(eq("COURSE001"), refEq(dto));
        Mockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    public void testUpdateCourse_notFound() throws Exception {
        CreateCourseDto dto = new CreateCourseDto("COURSE001", "Bahasa Inggris", "Ilmu Komputer", "S1", 1L, "Mata Kuliah yang mempelajari Bahasa Inggris");

        Mockito.when(courseService.updateCourse(ArgumentMatchers.eq("COURSE001"), ArgumentMatchers.any(CreateCourseDto.class))).thenThrow(ResourceNotFoundException.class);

        // Mapping objek ke JSON String
        ObjectMapper mapper = new ObjectMapper();
        String mataKuliahJson = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/courses/{idMataKuliah}/update", "COURSE001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mataKuliahJson))
                .andExpect(status().isNotFound());

        Mockito.verify(courseService, Mockito.times(1)).updateCourse(ArgumentMatchers.eq("COURSE001"), ArgumentMatchers.any(CreateCourseDto.class));
        Mockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    public void testDeleteCourse_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/courses/{idMataKuliah}/delete", "COURSE001"))
                .andExpect(status().isOk());

        Mockito.verify(courseService).deleteCourse("COURSE001");
    }

    @Test
    public void testDeleteCourse_notFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(courseService).deleteCourse("COURSE001");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/courses/{idMataKuliah}/delete", "COURSE001"))
                .andExpect(status().isNotFound());

        Mockito.verify(courseService).deleteCourse("COURSE001");
    }
}
