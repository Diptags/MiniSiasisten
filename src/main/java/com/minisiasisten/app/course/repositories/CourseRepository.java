package com.minisiasisten.app.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minisiasisten.app.course.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String>{}