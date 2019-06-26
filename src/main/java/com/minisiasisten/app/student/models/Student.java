package com.minisiasisten.app.student.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id_mahasiswa")
    private Long npmMahasiswa;

    @NotNull
    @Column(name = "nama")
    private String namaMahasiswa;

    @NotNull
    @Column(name = "prodi")
    private String prodiMahasiswa;

    @NotNull
    @Column(name = "jenjang")
    private String jenjangMahasiswa;

    @NotNull
    @Column(name = "semester")
    private Long semesterMahasiswa;

    @NotNull
    @Column(name = "ipk")
    private Float ipkMahasiswa;

    @NotNull
    @Column(name = "total_sks")
    private Long totalSksMahasiswa;

    public Student() {
    }

    public Long getNpmMahasiswa() {
        return npmMahasiswa;
    }

    public void setNpmMahasiswa(Long npmMahasiswa) {
        this.npmMahasiswa = npmMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getProdiMahasiswa() {
        return prodiMahasiswa;
    }

    public void setProdiMahasiswa(String prodiMahasiswa) {
        this.prodiMahasiswa = prodiMahasiswa;
    }

    public String getJenjangMahasiswa() {
        return jenjangMahasiswa;
    }

    public void setJenjangMahasiswa(String jenjangMahasiswa) {
        this.jenjangMahasiswa = jenjangMahasiswa;
    }

    public Long getSemesterMahasiswa() {
        return semesterMahasiswa;
    }

    public void setSemesterMahasiswa(Long semesterMahasiswa) {
        this.semesterMahasiswa = semesterMahasiswa;
    }

    public Float getIpkMahasiswa() {
        return ipkMahasiswa;
    }

    public void setIpkMahasiswa(Float ipkMahasiswa) {
        this.ipkMahasiswa = ipkMahasiswa;
    }

    public Long getTotalSksMahasiswa() {
        return totalSksMahasiswa;
    }

    public void setTotalSksMahasiswa(Long totalSksMahasiswa) {
        this.totalSksMahasiswa = totalSksMahasiswa;
    }
}
