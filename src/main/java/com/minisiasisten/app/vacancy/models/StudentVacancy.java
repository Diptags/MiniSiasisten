package com.minisiasisten.app.vacancy.models;

import com.minisiasisten.app.course.models.Course;
import com.minisiasisten.app.student.models.Student;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "student_vacancy")
public class StudentVacancy {

    @Id
    @Column(name = "id_lowongan")
    private String idLowongan;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matkul")
    private Course mataKuliah;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mahasiswa")
    private Student mahasiswaPelamar;

    public StudentVacancy() {
    }

    public StudentVacancy(String idLowongan, @NotNull Course mataKuliah, @NotNull Student mahasiswaPelamar) {
        this.idLowongan = idLowongan;
        this.mataKuliah = mataKuliah;
        this.mahasiswaPelamar = mahasiswaPelamar;
    }

    public void setIdLowongan(String idLowongan) {
        this.idLowongan = idLowongan;
    }

    public void setMataKuliah(Course mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public void setMahasiswaPelamar(Student mahasiswaPelamar) {
        this.mahasiswaPelamar = mahasiswaPelamar;
    }

    public String getIdLowongan() {
        return idLowongan;
    }

    public Course getMataKuliah() {
        return mataKuliah;
    }

    public Student getMahasiswaPelamar() {
        return mahasiswaPelamar;
    }
}
