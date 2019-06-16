package com.minisiasisten.app.vacancy.services;

import com.minisiasisten.app.student.services.StudentService;
import com.minisiasisten.app.vacancy.dto.CreateStudentVacancyDto;
import com.minisiasisten.app.vacancy.exceptions.StudentVacancyNotEligibleException;
import com.minisiasisten.app.vacancy.models.StudentVacancy;
import com.minisiasisten.app.vacancy.repositories.StudentVacancyRepository;
import com.minisiasisten.app.course.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentVacancyService {

    @Autowired
    private StudentVacancyRepository studentVacancyRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    private String studentVacancyNotFoundMessage = "Lowongan untuk mahasiswa ini tidak ditemukan!";

    public List<StudentVacancy> getAllVacancies() {
        return studentVacancyRepository.findAll();
    }

    public StudentVacancy getVacancyById(String idLowongan) {
        return studentVacancyRepository.findById(idLowongan)
                .orElseThrow(() -> new ResourceNotFoundException(studentVacancyNotFoundMessage));
    }

    public StudentVacancy createVacancy(CreateStudentVacancyDto vacancyDto) {
        StudentVacancy currentStudentVacancy = new StudentVacancy();
        setterVacancyFromDto(currentStudentVacancy, vacancyDto);
        vacancyValidator(currentStudentVacancy);

        return studentVacancyRepository.save(currentStudentVacancy);
    }

    public StudentVacancy updateVacancy(String idLowongan, CreateStudentVacancyDto vacancyDto) {
        StudentVacancy currentStudentVacancy = studentVacancyRepository.findById(idLowongan)
                .orElseThrow(() -> new ResourceNotFoundException(studentVacancyNotFoundMessage));

        setterVacancyFromDto(currentStudentVacancy, vacancyDto);
        vacancyValidator(currentStudentVacancy);

        return studentVacancyRepository.save(currentStudentVacancy);
    }

    public void deleteVacancy(String idLowongan) {
        studentVacancyRepository.deleteById(idLowongan);
    }

    private void setterVacancyFromDto(StudentVacancy currentStudentVacancy, CreateStudentVacancyDto vacancyDto) {
        currentStudentVacancy.setIdLowongan(vacancyDto.getIdLowongan());
        currentStudentVacancy.setMataKuliah(courseService.getCourseById(vacancyDto.getIdMataKuliah()));
        currentStudentVacancy.setMahasiswaPelamar(studentService.getStudentById(vacancyDto.getNpmMahasiswa()));
    }

    private void vacancyValidator(StudentVacancy studentVacancy) throws StudentVacancyNotEligibleException {
        if(!isProdiMeetRequirement(studentVacancy)){
            throw new StudentVacancyNotEligibleException("Pendaftaran gagal! Prodi tidak memenuhi syarat!");
        }
        if(!isJenjangMeetRequirement(studentVacancy)){
            throw new StudentVacancyNotEligibleException("Pendaftaran gagal! Jenjang tidak memenuhi syarat!");
        }
        if(!isSemesterMeetRequirement(studentVacancy)){
            throw new StudentVacancyNotEligibleException("Pendaftaran gagal! Semester tidak memenuhi syarat minimum!");
        }
    }

    // ISSUE : Harusnya mebandingkan dengan objek yang lebih besar lagi, yaitu Objeck VancancyData

    private boolean isProdiMeetRequirement(StudentVacancy studentVacancy){
        // Membandingkan prodi mahasiswa dengan prodi mata kuliah yang didaftarkan

        String prodiMahasiswa = studentVacancy.getMahasiswaPelamar().getProdiMahasiswa();
        String prodiRequirement = studentVacancy.getMataKuliah().getProdiMataKuliah();

        return prodiMahasiswa.equals(prodiRequirement);
    }

    private boolean isJenjangMeetRequirement(StudentVacancy studentVacancy){

        String jenjangMahasiswa = studentVacancy.getMahasiswaPelamar().getJenjangMahasiswa();
        String jenjangRequirement = studentVacancy.getMataKuliah().getJenjangMataKuliah();

        return jenjangMahasiswa.equals(jenjangRequirement);
    }

    private boolean isSemesterMeetRequirement(StudentVacancy studentVacancy){

        Long semesterMahasiswa = studentVacancy.getMahasiswaPelamar().getSemesterMahasiswa();
        Long semesterRequirement = studentVacancy.getMataKuliah().getSemesterMataKuliah();

        if (semesterMahasiswa.compareTo(semesterRequirement) < 0) return false;
        return true;
    }
}
