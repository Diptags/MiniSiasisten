package com.minisiasisten.app.vacancy.models;

import com.minisiasisten.app.course.models.Course;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @Column(name = "id")
    private String idLowongan;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matkul")
    private Course mataKuliah;

    @NotNull
    @Column(name = "prodi")
    private String prodiLowongan;

    @NotNull
    @Column(name = "jenjang")
    private String jenjangLowongan;

    @NotNull
    @Column(name = "semester_minimal")
    private Long semesterMinimalLowongan;

    @NotNull
    @Column(name = "dosen_pembuka")
    private String namaDosenPembukaLowongan;

    @NotNull
    @Column(name = "status_lowongan")
    private String statusLowongan;

    @NotNull
    @Column(name = "slot_tersedia")
    private Long slotLamaran;

    @Column(name = "deskripsi")
    private String deskripsiLowongan;

    @NotNull
    @Column(name = "status_lamaran")
    private String statusLamaran;

    public Vacancy() {
    }

    public Vacancy(String idLowongan, @NotNull Course mataKuliah, @NotNull String prodiLowongan, @NotNull String jenjangLowongan, @NotNull Long semesterMinimalLowongan, @NotNull String namaDosenPembukaLowongan, @NotNull String statusLowongan, @NotNull Long slotLamaran, String deskripsiLowongan, @NotNull String statusLamaran) {
        this.idLowongan = idLowongan;
        this.mataKuliah = mataKuliah;
        this.prodiLowongan = prodiLowongan;
        this.jenjangLowongan = jenjangLowongan;
        this.semesterMinimalLowongan = semesterMinimalLowongan;
        this.namaDosenPembukaLowongan = namaDosenPembukaLowongan;
        this.statusLowongan = statusLowongan;
        this.slotLamaran = slotLamaran;
        this.deskripsiLowongan = deskripsiLowongan;
        this.statusLamaran = statusLamaran;
    }

    public String getIdLowongan() {
        return idLowongan;
    }

    public void setIdLowongan(String idLowongan) {
        this.idLowongan = idLowongan;
    }

    public Course getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(Course mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getProdiLowongan() {
        return prodiLowongan;
    }

    public void setProdiLowongan(String prodiLowongan) {
        this.prodiLowongan = prodiLowongan;
    }

    public String getJenjangLowongan() {
        return jenjangLowongan;
    }

    public void setJenjangLowongan(String jenjangLowongan) {
        this.jenjangLowongan = jenjangLowongan;
    }

    public Long getSemesterMinimalLowongan() {
        return semesterMinimalLowongan;
    }

    public void setSemesterMinimalLowongan(Long semesterMinimalLowongan) {
        this.semesterMinimalLowongan = semesterMinimalLowongan;
    }

    public String getNamaDosenPembukaLowongan() {
        return namaDosenPembukaLowongan;
    }

    public void setNamaDosenPembukaLowongan(String namaDosenPembukaLowongan) {
        this.namaDosenPembukaLowongan = namaDosenPembukaLowongan;
    }

    public String getStatusLowongan() {
        return statusLowongan;
    }

    public void setStatusLowongan(String statusLowongan) {
        this.statusLowongan = statusLowongan;
    }

    public Long getSlotLamaran() {
        return slotLamaran;
    }

    public void setSlotLamaran(Long slotLamaran) {
        this.slotLamaran = slotLamaran;
    }

    public String getDeskripsiLowongan() {
        return deskripsiLowongan;
    }

    public void setDeskripsiLowongan(String deskripsiLowongan) {
        this.deskripsiLowongan = deskripsiLowongan;
    }

    public String getStatusLamaran() {
        return statusLamaran;
    }

    public void setStatusLamaran(String statusLamaran) {
        this.statusLamaran = statusLamaran;
    }
}
