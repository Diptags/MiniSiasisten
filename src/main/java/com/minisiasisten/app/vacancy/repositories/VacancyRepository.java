package com.minisiasisten.app.vacancy.repositories;

import com.minisiasisten.app.vacancy.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, String> {

}