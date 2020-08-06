package com.example.personalHealth;

import java.util.Date;

public class bpUI {
    private Long id;
    private Date recordDate;
    private String morning;
    private String afternoon;
    private String night;

    protected bpUI(Long id,Date recordDate, String morning, String afternoon, String night){
        this.id=id;
        this.recordDate = recordDate;
        this.morning = morning;
        this.afternoon = afternoon;
        this.night= night;
    }

    public Long getId(){
        return id;
    }

    public Date getRecordDate(){
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

    public void setId(Long id){
        this.id=id;
    }

    public void setRecordDate(Date recordDate){
        this.recordDate= recordDate;
    }

    public void setMorning(String morning){
        this.morning=morning;
    }

    public void setAfternoon(String afternoon){
        this.afternoon=afternoon;
    }

    public void setNight(String night){
        this.night=night;
    }

}
