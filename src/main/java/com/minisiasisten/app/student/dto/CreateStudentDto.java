package com.minisiasisten.app.student.dto;

public class CreateStudentDto {
    private Long npmMahasiswa;
    private String namaMahasiswa;
    private String prodiMahasiswa;
    private String jenjangMahasiswa;
    private Long semesterMahasiswa;
    private Float ipkMahasiswa;
    private Long totalSksMahasiswa;

    public CreateStudentDto() {
    }

    public CreateStudentDto(Long npmMahasiswa, String namaMahasiswa, String prodiMahasiswa, String jenjangMahasiswa, Long semesterMahasiswa, Float ipkMahasiswa, Long totalSksMahasiswa) {
        this.npmMahasiswa = npmMahasiswa;
        this.namaMahasiswa = namaMahasiswa;
        this.prodiMahasiswa = prodiMahasiswa;
        this.jenjangMahasiswa = jenjangMahasiswa;
        this.semesterMahasiswa = semesterMahasiswa;
        this.ipkMahasiswa = ipkMahasiswa;
        this.totalSksMahasiswa = totalSksMahasiswa;
    }

    public Long getNpmMahasiswa() {
        return npmMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public String getProdiMahasiswa() {
        return prodiMahasiswa;
    }

    public String getJenjangMahasiswa() {
        return jenjangMahasiswa;
    }

    public Long getSemesterMahasiswa() {
        return semesterMahasiswa;
    }

    public Float getIpkMahasiswa() {
        return ipkMahasiswa;
    }

    public Long getTotalSksMahasiswa() {
        return totalSksMahasiswa;
    }
}
