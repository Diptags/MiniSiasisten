package com.minisiasisten.app.student.services;

import com.minisiasisten.app.student.dto.CreateStudentDto;
import com.minisiasisten.app.student.models.Student;
import com.minisiasisten.app.student.repositories.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static org.mockito.internal.verification.VerificationModeFactory.times;

public class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStudents() {
        studentService.getAllStudents();
        Mockito.verify(studentRepository, times(1)).findAll();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getStudentById_notFound_throwsResourceNotFoundException(){
        Mockito.when(studentRepository.getOne(1L)).thenThrow(ResourceNotFoundException.class);
        studentService.getStudentById(1L);
        Mockito.verify(studentRepository).getOne(1L);
        Mockito.verifyZeroInteractions(studentRepository);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateStudent_notFound_throwsResourceNotFoundException(){
        Mockito.when(studentRepository.getOne(1L)).thenThrow(ResourceNotFoundException.class);
        CreateStudentDto dto = new CreateStudentDto(1L, "Dipsi", "Ilkom", "S1", 1L, 4F, 1L);
        studentService.updateStudent(1L, dto);
        Mockito.verify(studentRepository).getOne(1L);
    }

    @Test
    public void deleteStudent_success() {
        studentService.deleteStudent(1L);
        Mockito.verify(studentRepository).deleteById(1L);
    }
}
