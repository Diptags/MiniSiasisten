package com.minisiasisten.app.course.controllers;

import com.minisiasisten.app.course.dto.CreateCourseDto;
import com.minisiasisten.app.course.models.Course;
import com.minisiasisten.app.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping(value = "/{idMataKuliah}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course getCourseById(@PathVariable String idMataKuliah) {
        return courseService.getCourseById(idMataKuliah);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course createCourse(@Valid @RequestBody CreateCourseDto courseDto) {
        return courseService.createCourse(courseDto);
    }

    @PutMapping(value = "/{idMataKuliah}/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course updateCourse(@PathVariable String idMataKuliah, @Valid @RequestBody CreateCourseDto courseDto) {
        return courseService.updateCourse(idMataKuliah, courseDto);
    }

    @DeleteMapping("/{idMataKuliah}/delete")
    public void deleteCourse(@PathVariable String idMataKuliah) {
        courseService.deleteCourse(idMataKuliah);
    }
}
