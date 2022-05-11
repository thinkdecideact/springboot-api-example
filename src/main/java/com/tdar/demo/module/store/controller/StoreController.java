package com.tdar.demo.module.store.controller;

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
@RequestMapping("/api/store")
public class StoreController {
    @Autowired(required = false)
    private StoreMapper storeMapper;

    /**
     * Get a list of records
     *
     * curl -X GET 'http://127.0.0.1:8080/api/store/getList?pageIndex=0&rowCountPerPage=2'
     *
     */
    @RequestMapping(path="/getList", method={RequestMethod.GET})
    public R getList(@RequestParam Map<String, String> allParams) {
        int pageIndex = Integer.parseInt(allParams.get("pageIndex"));
        int rowCountPerPage = Integer.parseInt(allParams.get("rowCountPerPage"));
        int startIndex = pageIndex * rowCountPerPage;

        Map<String, Object> queryData = new HashMap<>();
        queryData.put("pageIndex", pageIndex);
        queryData.put("rowCountPerPage", rowCountPerPage);
        queryData.put("startIndex", startIndex);

        List<Map<String, Object>> storeList = storeMapper.getMany(queryData);
        int rowCount = storeMapper.getRowCount();
        int pageCount = CommonUtil.getPageCount(rowCount, rowCountPerPage);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("pageIndex", pageIndex);
        responseData.put("pageCount", pageCount);
        responseData.put("rowCount", rowCount);
        responseData.put("rowCountPerPage", rowCountPerPage);
        responseData.put("rows", storeList);
        return R.success(responseData);
    }

    /**
     * Get one record
     *
     * curl -X GET 'http://127.0.0.1:8080/api/store/getDetail?id=2'
     *
     */
    @RequestMapping(path="/getDetail", method={RequestMethod.GET})
    public R getDetail(@RequestParam Long id) {
        HashMap<String, Object> storeMap = storeMapper.getOne(id);
        if (storeMap == null) {
            return R.fail("Failed to find data");
        }
        return R.success(storeMap);
    }

    /**
     * Create a record
     *
     * curl -X POST -F "name=abc" -F "address=123" "http://127.0.0.1:8080/api/store/create"
     *
     * Notice the difference between @RequestBody and @RequestParam
     */
    @RequestMapping(path="/create", method={RequestMethod.POST})
    public R create(@RequestParam Map<String, String> allParams) {
        String name = allParams.get("name");
        String address = allParams.get("address");

        Store storeInstance = new Store(name, address, new Date(), new Date(), 1);
        int affectedRows = storeMapper.insert(storeInstance);
        if(affectedRows > 0) {
            // use storeInstance.getId() to get the pk of a record
            return R.success("created successfully", storeInstance.getId());
        }
        return R.fail("Failed to create");
    }

    /**
     * Update fields of a record
     *
     * curl -X POST -F "id=147" -F "name=abc" -F "address=123" "http://127.0.0.1:8080/api/store/update"
     *
     */
    @RequestMapping(path="/update", method={RequestMethod.POST})
    public R update(@RequestParam Map<String, String> allParams) {
        Long id = Long.valueOf(allParams.get("id"));
        String name = allParams.get("name");
        String address = allParams.get("address");

        Store storeInstance = storeMapper.getEntityById(id);
        if (storeInstance == null) {
            return R.fail("Failed to find data");
        }
        storeInstance.setName(name);
        storeInstance.setAddress(address);
        storeInstance.setMtime(new Date());
        int affectedRows = storeMapper.update(storeInstance);
        if(affectedRows > 0) {
            return R.success("Updated successfully");
        }
        return R.fail("Failed to update");
    }

    /**
     * Delete a record
     *
     * curl -X POST -F 'id=147' 'http://127.0.0.1:8080/api/store/delete'
     *
     */
    @RequestMapping(path="/delete", method={RequestMethod.POST})
    public R delete(@RequestParam Long id) {
        Store storeInstance = storeMapper.getEntityById(id);
        if (storeInstance == null) {
            return R.fail("Failed to find data");
        }
        int affectedRows = storeMapper.delete(storeInstance.getId());
        if(affectedRows > 0) {
            return R.success("Deleted successfully");
        }
        return R.fail("Failed to delete");
    }
}
