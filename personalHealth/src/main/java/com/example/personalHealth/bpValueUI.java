package com.example.personalHealth;

import java.util.Date;

public class bpValueUI {

    private Date recordDate;
    private String value;
    private String man;

    protected bpValueUI(Date recordDate, String Value, String man){
        this.recordDate= recordDate;
        this.value= value;
        this.man = man;
    }

    public Date getrecordDate(){
        return this.recordDate;
    }

    public String getValue(){
        return this.value;
    }

    public String getMan(){
        return this.man;
    }
}
