package app.domain;

import java.util.Date;

public class ServiceRegistration implements Item {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_department() {
        return id_department;
    }

    public void setId_department(Long id_department) {
        this.id_department = id_department;
    }

    public Long getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(Long id_doctor) {
        this.id_doctor = id_doctor;
    }

    public Long getId_workingTime() {
        return id_workingTime;
    }

    public void setId_workingTime(Long id_workingTime) {
        this.id_workingTime = id_workingTime;
    }

    public Long getId_service() {
        return id_service;
    }

    public void setId_service(Long id_service) {
        this.id_service = id_service;
    }

    public Long getId_patient() {
        return id_patient;
    }

    public void setId_patient(Long id_patient) {
        this.id_patient = id_patient;
    }

    public Long getId_departmentHead() {
        return id_departmentHead;
    }

    public void setId_departmentHead(Long id_departmentHead) {
        this.id_departmentHead = id_departmentHead;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private Long id;
    private Date dateOfRegistration;
    private String comment;
    private Long id_department;
    private Long id_doctor;
    private Long id_workingTime;
    private Long id_service;
    private Long id_patient;
    private Long id_departmentHead;


    public ServiceRegistration(long id) {
        this.id = id;
    }

    public ServiceRegistration(Long id, Long id_department, Long id_doctor, Long id_workingTime, Long id_service, Long id_patient, Long id_departmentHead, Date dateOfRegistration, String comment) {
        this.id = id;
        this.id_department = id_department;
        this.id_doctor = id_doctor;
        this.id_workingTime = id_workingTime;
        this.id_service = id_service;
        this.id_patient = id_patient;
        this.id_departmentHead = id_departmentHead;
        this.dateOfRegistration = dateOfRegistration;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ServiceRegistration{" +
                "id=" + id +
                ", id_department=" + id_department +
                ", id_doctor=" + id_doctor +
                ", id_workingTime=" + id_workingTime +
                ", id_service=" + id_service +
                ", id_patient=" + id_patient +
                ", id_departmentHead=" + id_departmentHead +
                ", dateOfRegistration=" + dateOfRegistration +
                ", comment='" + comment + '\'' +
                '}';
    }
}
