package com.minisiasisten.app.student.controllers;

import com.minisiasisten.app.student.dto.CreateStudentDto;
import com.minisiasisten.app.student.models.Student;
import com.minisiasisten.app.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/{npmMahasiswa}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudentById(@PathVariable Long npmMahasiswa) {
        return studentService.getStudentById(npmMahasiswa);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@Valid @RequestBody CreateStudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @PutMapping(value = "/{npmMahasiswa}/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(@PathVariable Long npmMahasiswa, @Valid @RequestBody CreateStudentDto studentDto) {
        return studentService.updateStudent(npmMahasiswa, studentDto);
    }

    @DeleteMapping("/{npmMahasiswa}/delete")
    public void deleteStudent(@PathVariable Long npmMahasiswa) {
        studentService.deleteStudent(npmMahasiswa);
    }
}
