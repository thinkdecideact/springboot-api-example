package com.tdar.demo.module.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Store implements Serializable {
    private Long id;
    private String name;
    private String address;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date ctime;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date mtime;

    private int priority;
    private String comment;
    private int isActive;
    private int isDel;

    public Store() {
        super();
    }

    public Store(String name, String address, Date ctime, Date mtime, int isActive) {
        super();
        this.name = name;
        this.address = address;
        this.ctime = ctime;
        this.mtime = mtime;
        this.isActive = isActive;
    }
}
