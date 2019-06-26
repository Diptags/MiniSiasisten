package com.minisiasisten.app.course.services;

import com.minisiasisten.app.course.dto.CreateCourseDto;
import com.minisiasisten.app.course.models.Course;
import com.minisiasisten.app.course.repositories.CourseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CourseServiceTest {

    @InjectMocks
    CourseService courseService;

    @Mock
    CourseRepository courseRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCourses() {
        courseService.getAllCourses();
        Mockito.verify(courseRepository, times(1)).findAll();
    }

    @Test
    public void getCourseById_success() {
        Course mataKuliah = new Course();
        Mockito.when(courseRepository.getOne("COURSE001")).thenReturn(mataKuliah);
        courseService.getCourseById("COURSE001");
        Mockito.verify(courseRepository).getOne("COURSE001");
        Mockito.verifyZeroInteractions(courseRepository);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getCourseById_notFound_throwsResourceNotFoundException(){
        Mockito.when(courseRepository.getOne("COURSE001")).thenThrow(ResourceNotFoundException.class);
        courseService.getCourseById("COURSE001");
        Mockito.verify(courseRepository).getOne("COURSE001");
        Mockito.verifyZeroInteractions(courseRepository);
    }

    @Test
    public void createCourse_success(){
        Course mataKuliah = new Course();
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(mataKuliah);
        CreateCourseDto dto = new CreateCourseDto("COURSE001", "Bahasa Inggris", "Sistem Informasi", "S1", 1L, "Mata kuliah dasar");
        courseService.createCourse(dto);
        Mockito.verify(courseRepository).save(Mockito.any(Course.class));
    }

    @Test
    public void updateCourse_success(){
        Course mataKuliah = new Course();
        Mockito.when(courseRepository.getOne("COURSE001")).thenReturn(mataKuliah);
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(mataKuliah);
        CreateCourseDto dto = new CreateCourseDto("COURSE001", "Bahasa Inggris", "Sistem Informasi", "S1", 1L, "Mata kuliah dasar");
        courseService.updateCourse("COURSE001", dto);
        Mockito.verify(courseRepository).save(Mockito.any(Course.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateCourse_notFound_throwsResourceNotFoundException(){
        Mockito.when(courseRepository.getOne("COURSE001")).thenThrow(ResourceNotFoundException.class);
        CreateCourseDto dto = new CreateCourseDto("COURSE001", "Bahasa Inggris", "Sistem Informasi", "S1", 1L, "Mata kuliah dasar");
        courseService.updateCourse("COURSE001", dto);
        Mockito.verify(courseRepository).getOne("COURSE001");
    }

    @Test
    public void deleteCourse_success() {
        courseService.deleteCourse("COURSE001");
        Mockito.verify(courseRepository).deleteById("COURSE001");
    }
}
