package app.domain;

public class KindOfService implements Item{
    private Long id;
    private String name;
    private Long id_department;


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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KindOfService(long id) {
        this.id = id;
    }

    public  KindOfService(Long id, Long id_department, String name) {
        this.id = id;
        this.id_department = id_department;
        this.name = name;
    }

    @Override
    public String toString() {
        return "KindOfService{" +
                "id=" + id +
                ", id_department=" + id_department +
                ", name='" + name + '\'' +
                '}';
    }
}
