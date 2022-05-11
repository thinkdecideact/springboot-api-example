package com.tdar.demo.module.store.dto.params;

import lombok.Data;

@Data
public class StoreQueryParam {
    private int pageIndex;
    private int rowCountPerPage;
    private int startIndex;

    private Long id;
    private String name;
    private String address;

    public void calcStartIndex(){
        startIndex = pageIndex * rowCountPerPage;
    }
}
