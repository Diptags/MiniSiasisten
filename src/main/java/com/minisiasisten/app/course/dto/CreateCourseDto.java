package com.minisiasisten.app.course.dto;

public class CreateCourseDto {
    private String idMataKuliah;
    private String namaMataKuliah;
    private String prodiMataKuliah;
    private String jenjangMataKuliah;
    private Long semesterMataKuliah;
    private String deskripsiMataKuliah;

    public CreateCourseDto(){

    }

    public CreateCourseDto(String idMataKuliah, String namaMataKuliah, String prodiMataKuliah, String jenjangMataKuliah, Long semesterMataKuliah, String deskripsiMataKuliah) {
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

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public String getProdiMataKuliah() {
        return prodiMataKuliah;
    }

    public String getJenjangMataKuliah() {
        return jenjangMataKuliah;
    }

    public Long getSemesterMataKuliah() {
        return semesterMataKuliah;
    }

    public String getDeskripsiMataKuliah() {
        return deskripsiMataKuliah;
    }
}
