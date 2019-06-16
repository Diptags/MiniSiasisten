package com.minisiasisten.app.vacancy.dto;

public class CreateStudentVacancyDto {
    private String idLowongan;
    private String idMataKuliah;
    private Long npmMahasiswa;

    public CreateStudentVacancyDto() {
    }

    public CreateStudentVacancyDto(String idLowongan, String idMataKuliah, Long npmMahasiswa) {
        this.idLowongan = idLowongan;
        this.idMataKuliah = idMataKuliah;
        this.npmMahasiswa = npmMahasiswa;
    }

    public String getIdLowongan() {
        return idLowongan;
    }

    public String getIdMataKuliah() {
        return idMataKuliah;
    }

    public Long getNpmMahasiswa() {
        return npmMahasiswa;
    }
}
