package com.minisiasisten.app.vacancy.controllers;

import com.minisiasisten.app.vacancy.models.StudentVacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.minisiasisten.app.vacancy.dto.CreateStudentVacancyDto;
import com.minisiasisten.app.vacancy.services.StudentVacancyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vacancy/students")
public class StudentVacancyController {

    @Autowired
    private StudentVacancyService studentVacancyService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentVacancy> getAllVacancies() {
        return studentVacancyService.getAllVacancies();
    }

    @GetMapping(value = "/{idLowongan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentVacancy getVacancyById(@PathVariable String idLowongan) {
        return studentVacancyService.getVacancyById(idLowongan);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentVacancy createVacancy(@Valid @RequestBody CreateStudentVacancyDto vacancyDto){
        return studentVacancyService.createVacancy(vacancyDto);
    }

    @PutMapping(value = "/{idLowongan}/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentVacancy updateVacancy(@PathVariable String idLowongan, @Valid @RequestBody CreateStudentVacancyDto vacancyDto){
        return studentVacancyService.updateVacancy(idLowongan, vacancyDto);
    }

    @DeleteMapping("/{idLowongan}/delete")
    public void deleteVacancy(@PathVariable String idLowongan){
        studentVacancyService.deleteVacancy(idLowongan);
    }
}
