package app.domain;

import java.math.BigDecimal;

public class Service implements Item {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long id_kind;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId_kind() {
        return id_kind;
    }


    public Service(long id) {
        this.id = id;
    }
    public Service(Long id, String name, BigDecimal price, Long id_kind) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.id_kind = id_kind;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", id_kind=" + id_kind +
                '}';
    }
}
