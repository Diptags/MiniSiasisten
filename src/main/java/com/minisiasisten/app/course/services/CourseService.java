package com.minisiasisten.app.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.minisiasisten.app.course.dto.CreateCourseDto;
import com.minisiasisten.app.course.repositories.CourseRepository;
import com.minisiasisten.app.course.models.Course;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    private String courseNotFoundMessage = "Data mata kuliah tidak ditemukan";

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course getCourseById(String idMataKuliah) {
        return courseRepository.findById(idMataKuliah)
                .orElseThrow(() -> new ResourceNotFoundException(courseNotFoundMessage));
    }

    public Course createCourse(CreateCourseDto courseDto){
        Course currentCourse = new Course();
        updateCourseUtil(currentCourse, courseDto);

        return courseRepository.save(currentCourse);
    }

    public Course updateCourse(String idMataKuliah, CreateCourseDto courseDto){
        Course currentCourse = getCourseById(idMataKuliah);

        updateCourseUtil(currentCourse, courseDto);
        return courseRepository.save(currentCourse);
    }

    public void deleteCourse(String idMataKuliah){
        courseRepository.deleteById(idMataKuliah);
    }

    private void updateCourseUtil(Course currentCourse, CreateCourseDto courseDto){
        currentCourse.setIdMataKuliah(courseDto.getIdMataKuliah());
        currentCourse.setNamaMataKuliah(courseDto.getNamaMataKuliah());
        currentCourse.setProdiMataKuliah(courseDto.getProdiMataKuliah());
        currentCourse.setJenjangMataKuliah(courseDto.getJenjangMataKuliah());
        currentCourse.setSemesterMataKuliah(courseDto.getSemesterMataKuliah());
        currentCourse.setDeskripsiMataKuliah(courseDto.getDeskripsiMataKuliah());
    }
}
