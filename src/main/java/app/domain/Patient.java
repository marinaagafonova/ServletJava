package app.domain;

import java.time.LocalDate;

public class Patient implements Item{
    private Long id;
    private String fio;
    private LocalDate dayOfBirth;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate  getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate  dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Patient(long id) {
        this.id = id;
    }
    public Patient(Long id, String fio, LocalDate dayOfBirth) {
        this.id = id;
        this.fio = fio;
        this.dayOfBirth = dayOfBirth;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", dayOfBirth=" + dayOfBirth +
                '}';
    }
}
