package com.minisiasisten.app.student.services;

import com.minisiasisten.app.student.dto.CreateStudentDto;
import com.minisiasisten.app.student.models.Student;
import com.minisiasisten.app.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private String studentNotFoundMessage = "Data mahasiswa tidak ditemukan";

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long npmMahasiswa) {
        return studentRepository.findById(npmMahasiswa)
                .orElseThrow(() -> new ResourceNotFoundException(studentNotFoundMessage));
    }

    public Student createStudent(CreateStudentDto studentDto) {
        Student currentStudent = new Student();
        updateStudentUtil(currentStudent, studentDto);

        return studentRepository.save(currentStudent);
    }

    public Student updateStudent(Long npmMahasiswa, CreateStudentDto studentDto) {
        Student currentStudent = studentRepository.findById(npmMahasiswa)
                .orElseThrow(() -> new ResourceNotFoundException(studentNotFoundMessage));

        updateStudentUtil(currentStudent, studentDto);
        return studentRepository.save(currentStudent);
    }

    public void deleteStudent(Long npmMahasiswa) {
        studentRepository.deleteById(npmMahasiswa);
    }

    public void updateStudentUtil(Student currentStudent, CreateStudentDto studentDto) {
        currentStudent.setNpmMahasiswa(studentDto.getNpmMahasiswa());
        currentStudent.setNamaMahasiswa(studentDto.getNamaMahasiswa());
        currentStudent.setProdiMahasiswa(studentDto.getProdiMahasiswa());
        currentStudent.setJenjangMahasiswa(studentDto.getJenjangMahasiswa());
        currentStudent.setSemesterMahasiswa(studentDto.getSemesterMahasiswa());
        currentStudent.setIpkMahasiswa(studentDto.getIpkMahasiswa());
        currentStudent.setTotalSksMahasiswa(studentDto.getTotalSksMahasiswa());
    }
}
