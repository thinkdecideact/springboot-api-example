package com.tdar.demo.module.store.dto.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StoreResult implements Serializable {
    private Long id;
    private String name;
    private String address;
    private Date ctime;
    private Date mtime;
    private int isActive;
}
