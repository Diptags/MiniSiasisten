package com.minisiasisten.app.vacancy.services;

import com.minisiasisten.app.vacancy.dto.CreateStudentVacancyDto;
import com.minisiasisten.app.vacancy.repositories.StudentVacancyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static org.mockito.internal.verification.VerificationModeFactory.times;

public class StudentVacancyServiceTest {

    @InjectMocks
    StudentVacancyService studentVacancyService;

    @Mock
    StudentVacancyRepository studentVacancyRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStudentVacancies() {
        studentVacancyService.getAllVacancies();
        Mockito.verify(studentVacancyRepository, times(1)).findAll();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getStudentVacancyById_notFound_throwsResourceNotFoundException() {
        Mockito.when(studentVacancyRepository.getOne("VACANCY001")).thenThrow(ResourceNotFoundException.class);
        studentVacancyService.getVacancyById("VACANCY001");
        Mockito.verify(studentVacancyRepository).getOne("VACANCY001");
        Mockito.verifyZeroInteractions(studentVacancyRepository);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateStudentVacancy_notFound_throwsResourceNotFoundException() {
        Mockito.when(studentVacancyRepository.getOne("VACANCY001")).thenThrow(ResourceNotFoundException.class);
        CreateStudentVacancyDto dto = new CreateStudentVacancyDto("VACANCY001", "COURSE001", 1L);
        studentVacancyService.updateVacancy("VACANCY001", dto);
        Mockito.verify(studentVacancyRepository).getOne("VACANCY001");
    }

    @Test
    public void deleteStudentVacancy_success() {
        studentVacancyService.deleteVacancy("VACANCY001");
        Mockito.verify(studentVacancyRepository).deleteById("VACANCY001");
    }
}
