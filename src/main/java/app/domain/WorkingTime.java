package app.domain;

import java.util.Date;

public class WorkingTime implements Item {
    private Long id;
    private Date beginDate;
    private Date endDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public WorkingTime(long id) {
        this.id = id;
    }
    public WorkingTime(Long id, Date beginDate, Date endDate) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "WorkingTime{" +
                "id=" + id +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
