package app.domain;

public class DepartmentHead implements Item{
    private Long id;
    private String fio;
    private Long id_department;

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

    public Long getId_department() {
        return id_department;
    }

    public void setId_department(Long id_department) {
        this.id_department = id_department;
    }




    public DepartmentHead(long id) {
        this.id = id;
    }

    public DepartmentHead(Long id, String fio, Long id_department) {

        this.id = id;
        this.id_department = id_department;
        this.fio = fio;
    }

    @Override
    public String toString() {
        return "DepartmentHead{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", id_department=" + id_department +
                '}';
    }
}
