package com.minisiasisten.app.vacancy.repositories;

import com.minisiasisten.app.vacancy.models.StudentVacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentVacancyRepository extends JpaRepository<StudentVacancy, String> {

}