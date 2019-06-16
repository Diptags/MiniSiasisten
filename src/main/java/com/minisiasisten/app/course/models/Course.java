package com.minisiasisten.app.course.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "id")
    private String idMataKuliah;

    @NotNull
    @Column(name = "nama")
    private String namaMataKuliah;

    @NotNull
    @Column(name = "prodi")
    private String prodiMataKuliah;

    @NotNull
    @Column(name = "jenjang")
    private String jenjangMataKuliah;

    @NotNull
    @Column(name = "semester")
    private Long semesterMataKuliah;

    @Column(name = "deskripsi")
    private String deskripsiMataKuliah;

    public Course() {

    }

    public Course(String idMataKuliah, @NotNull String namaMataKuliah, @NotNull String prodiMataKuliah, @NotNull String jenjangMataKuliah, @NotNull Long semesterMataKuliah, String deskripsiMataKuliah) {
        this.idMataKuliah = idMataKuliah;
        this.namaMataKuliah = namaMataKuliah;
        this.prodiMataKuliah = prodiMataKuliah;
        this.jenjangMataKuliah = jenjangMataKuliah;
        this.semesterMataKuliah = semesterMataKuliah;
        this.deskripsiMataKuliah = deskripsiMataKuliah;
    }

    public String getIdMataKuliah() {
        return idMataKuliah;
    }

    public void setIdMataKuliah(String idMataKuliah) {
        this.idMataKuliah = idMataKuliah;
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setNamaMataKuliah(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }

    public String getProdiMataKuliah() {
        return prodiMataKuliah;
    }

    public void setProdiMataKuliah(String prodiMataKuliah) {
        this.prodiMataKuliah = prodiMataKuliah;
    }

    public String getJenjangMataKuliah() {
        return jenjangMataKuliah;
    }

    public void setJenjangMataKuliah(String jenjangMataKuliah) {
        this.jenjangMataKuliah = jenjangMataKuliah;
    }

    public Long getSemesterMataKuliah() {
        return semesterMataKuliah;
    }

    public void setSemesterMataKuliah(Long semesterMataKuliah) {
        this.semesterMataKuliah = semesterMataKuliah;
    }

    public String getDeskripsiMataKuliah() {
        return deskripsiMataKuliah;
    }

    public void setDeskripsiMataKuliah(String deskripsiMataKuliah) {
        this.deskripsiMataKuliah = deskripsiMataKuliah;
    }
}
