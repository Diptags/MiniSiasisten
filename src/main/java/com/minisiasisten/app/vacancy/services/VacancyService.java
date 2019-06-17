package com.minisiasisten.app.vacancy.services;

import com.minisiasisten.app.course.services.CourseService;
import com.minisiasisten.app.vacancy.dto.CreateVacancyDto;
import com.minisiasisten.app.vacancy.models.Vacancy;
import com.minisiasisten.app.vacancy.repositories.VacancyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private CourseService courseService;

    private String vacancyNotFoundMessage = "Data lowongan asisten tidak ditemukan!";

    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    public Vacancy getVacancyById(String idLowongan) {
        return vacancyRepository.findById(idLowongan)
                .orElseThrow(() -> new ResourceNotFoundException(vacancyNotFoundMessage));
    }

    public Vacancy createVacancy(CreateVacancyDto vacancyDto) {
        Vacancy currentVacancy = new Vacancy();
        setterVacancyFromDto(currentVacancy, vacancyDto);

        return vacancyRepository.save(currentVacancy);
    }

    public Vacancy updateVacancy(String idLowongan, CreateVacancyDto vacancyDto) {
        Vacancy currentVacancy = vacancyRepository.findById(idLowongan)
                .orElseThrow(() -> new ResourceNotFoundException(vacancyNotFoundMessage));

        setterVacancyFromDto(currentVacancy, vacancyDto);

        return vacancyRepository.save(currentVacancy);
    }

    public void deleteVacancy(String idLowongan) {
        vacancyRepository.deleteById(idLowongan);
    }

    private void setterVacancyFromDto(Vacancy currentVacancy, CreateVacancyDto vacancyDto) {
        currentVacancy.setIdLowongan(vacancyDto.getIdLowongan());
        currentVacancy.setMataKuliah(courseService.getCourseById(vacancyDto.getIdMataKuliah()));
        currentVacancy.setProdiLowongan(vacancyDto.getProdiLowongan());
        currentVacancy.setJenjangLowongan(vacancyDto.getJenjangLowongan());
        currentVacancy.setSemesterMinimalLowongan(vacancyDto.getSemesterMinimalLowongan());
        currentVacancy.setNamaDosenPembukaLowongan(vacancyDto.getNamaDosenPembukaLowongan());
        currentVacancy.setStatusLowongan(vacancyDto.getStatusLowongan());
        currentVacancy.setSlotLamaran(vacancyDto.getSlotLamaran());
        currentVacancy.setDeskripsiLowongan(vacancyDto.getDeskripsiLowongan());
        currentVacancy.setStatusLamaran(vacancyDto.getStatusLamaran());
    }
}
