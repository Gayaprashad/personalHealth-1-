package com.example.personalHealth;

import org.springframework.dao.DataAccessException;

import javax.persistence.*;
import java.util.Date;

@Entity
public class bp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    Date recordDate;

    @Column(columnDefinition ="varchar(255) default ''")
    private String morning;

    @Column(columnDefinition = "varchar(255) default ''")
    private String afternoon;

    @Column(columnDefinition = "varchar(255) default ''")
    private String night;

    public bp(){}

    public bp(Date recordDate, String morning, String afternoon, String night){
        this.recordDate = recordDate;
        this.morning= morning;
        this.afternoon= afternoon;
        this.night = night;
    }

    public bp(Long id,Date recordDate, String morning, String afternoon, String night){
        this.id=id;
        this.recordDate = recordDate;
        this.morning= morning;
        this.afternoon= afternoon;
        this.night = night;
    }

    public Long getId(){
        return this.id;
    }
    public Date getRecordDate() {
        return recordDate;
    }

    public String getMorning(){
        return morning;
    }

    public String getAfternoon(){
        return afternoon;
    }

    public String getNight(){
        return night;
    }

    public void setMorning(String value){
        this.morning = value;
    }

    public void setAfternoon(String value){
        this.afternoon = value;
    }

    public void setNight(String value){
        this.night = value;
    }
}
