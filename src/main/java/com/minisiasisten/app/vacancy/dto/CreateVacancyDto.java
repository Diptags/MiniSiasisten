package com.minisiasisten.app.vacancy.dto;

public class CreateVacancyDto {
    private String idLowongan;
    private String idMataKuliah;
    private String prodiLowongan;
    private String jenjangLowongan;
    private Long semesterMinimalLowongan;
    private String namaDosenPembukaLowongan;
    private String statusLowongan;
    private Long slotLamaran;
    private String deskripsiLowongan;
    private String statusLamaran;

    public String getIdLowongan() {
        return idLowongan;
    }

    public String getIdMataKuliah() {
        return idMataKuliah;
    }

    public String getProdiLowongan() {
        return prodiLowongan;
    }

    public String getJenjangLowongan() {
        return jenjangLowongan;
    }

    public Long getSemesterMinimalLowongan() {
        return semesterMinimalLowongan;
    }

    public String getNamaDosenPembukaLowongan() {
        return namaDosenPembukaLowongan;
    }

    public String getStatusLowongan() {
        return statusLowongan;
    }

    public Long getSlotLamaran() {
        return slotLamaran;
    }

    public String getDeskripsiLowongan() {
        return deskripsiLowongan;
    }

    public String getStatusLamaran() {
        return statusLamaran;
    }
}
