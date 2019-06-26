package com.minisiasisten.app.course.services;

import com.minisiasisten.app.course.dto.CreateCourseDto;
import com.minisiasisten.app.course.models.Course;
import com.minisiasisten.app.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return courseRepository.getOne(idMataKuliah);
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
