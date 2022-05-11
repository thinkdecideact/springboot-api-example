package com.tdar.demo.module.store.mapper;

import com.tdar.demo.module.store.dto.params.StoreQueryParam;
import com.tdar.demo.module.store.entity.Store;
import com.tdar.demo.module.store.dto.result.StoreResult;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StoreMapper {
    // List<Map<String, Object>> getMany(int offset, int count);
    List<Map<String, Object>> getMany(@Param("paramCondition") Map<String, Object> paramCondition);

    List<StoreResult> getManyByParam(@Param("paramCondition") StoreQueryParam paramCondition);

    StoreResult getOneById(Long id);

    HashMap<String, Object> getOne(@Param("pk") Long id);

    Store getEntityById(Long id);

    /**
     * @param store Store
     * @return int
     */
    int insert(@Param("store") Store store);

    int delete(Long id);

    int update(Store store);

    int getRowCount();

}

