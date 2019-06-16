package com.minisiasisten.app.student.repositories;

import com.minisiasisten.app.student.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {}
