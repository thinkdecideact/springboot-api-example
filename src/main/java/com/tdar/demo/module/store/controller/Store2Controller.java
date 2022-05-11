package com.tdar.demo.module.store.controller;

import com.tdar.demo.module.store.dto.params.StoreQueryParam;
import com.tdar.demo.module.store.dto.result.StoreResult;
import com.tdar.demo.module.store.entity.Store;
import com.tdar.demo.module.store.mapper.StoreMapper;
import com.tdar.demo.util.CommonUtil;
import com.tdar.demo.base.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/store")
public class Store2Controller {
    @Autowired(required = false)
    private StoreMapper storeMapper;

    /**
     * Get a list of records
     *
     * curl -X GET 'http://127.0.0.1:8080/api/v2/store/getList' -H 'Content-Type: application/json' -d '{"pageIndex": 0, "rowCountPerPage": 3}'
     *
     */
    @RequestMapping(path="/getList", method={RequestMethod.GET})
    public R getList(@RequestBody StoreQueryParam storeQueryParam) {
        storeQueryParam.calcStartIndex();
        List<StoreResult> storeList = storeMapper.getManyByParam(storeQueryParam);
        int rowCount = storeMapper.getRowCount();
        int pageCount = CommonUtil.getPageCount(rowCount, storeQueryParam.getRowCountPerPage());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("pageIndex", storeQueryParam.getPageIndex());
        responseData.put("pageCount", pageCount);
        responseData.put("rowCount", rowCount);
        responseData.put("rowCountPerPage", storeQueryParam.getRowCountPerPage());
        responseData.put("rows", storeList);
        return R.success(responseData);
    }

    /**
     * Get one record
     *
     * curl -X GET 'http://127.0.0.1:8080/api/v2/store/getDetail' -H 'Content-Type: application/json' -d '{"id": 15}'
     *
     */
    @RequestMapping(path="/getDetail", method={RequestMethod.GET})
    public R getDetail(@RequestBody Map<String, String> allParams) {
        StoreResult storeResult = storeMapper.getOneById(Long.valueOf(allParams.get("id")));
        if (storeResult == null) {
            R.fail("Failed to find data");
        }
        return R.success(storeResult);
    }


    /**
     * Create a record
     *
     * curl -X POST 'http://127.0.0.1:8080/api/v2/store/create' -H 'Content-Type: application/json' -d '{"id": 173, "name" : "abc", "address" : "123"}'
     *
     *
     */
    @RequestMapping(path="/create", method={RequestMethod.POST})
    public R create(@RequestBody Store store) {
        // TODO: Need to check the correctness of the input data
        store.setCtime(new Date());
        store.setMtime(new Date());
        storeMapper.insert(store);
        return R.success(store.getId());
    }

    /**
     * Update fields of a record
     *
     *
     * curl -X POST 'http://127.0.0.1:8080/api/v2/store/update' -H 'Content-Type: application/json' -d '{"id": 140, "name" : "abc", "address" : "123"}'
     *
     */
    @RequestMapping(path="/update", method={RequestMethod.POST})
    public R update(@RequestBody Store store) {
        // TODO: Need to check the correctness of the input data
        store.setMtime(new Date());
        return R.success(storeMapper.update(store));
    }

    /**
     * Delete a record
     *
     * curl -X POST 'http://127.0.0.1:8080/api/v2/store/delete' -H 'Content-Type: application/json' -d '{"id": 141}'
     *
     */
    @RequestMapping(path="/delete", method={RequestMethod.POST})
    public R delete(@RequestBody Map<String, String> allParams) {
        // TODO: Need to check the correctness of the input data
        return R.success(storeMapper.delete(Long.valueOf(allParams.get("id"))));
    }
}
