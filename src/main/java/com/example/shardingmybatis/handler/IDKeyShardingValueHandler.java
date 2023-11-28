package com.example.shardingmybatis.handler;


import com.example.shardingmybatis.utils.DateKeysSharding;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: 聂裴涵
 * @date: 2023/10/11  18:52
 */
public class IDKeyShardingValueHandler implements KeyShardingValueHandler {
    @Override
    public Collection<String> handle(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
//        Collection<Comparable<?>> idList = complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("id");
        Collection<Comparable<?>> idList = DateKeysSharding.getValueByKeyIgnoreCase("id",complexKeysShardingValue.getColumnNameAndShardingValuesMap());
        if (CollectionUtils.isEmpty(idList)) {
            return availableTargetNames;
        }
        Set<String> shardingResult = new HashSet<>();
        for (Comparable<?> temp : idList) {
            String id = String.valueOf(temp);
            Date dateById = DateKeysSharding.getDateById(id);
            if (dateById == null) {
                continue;
            }
            shardingResult.add(DateKeysSharding.getAvailableTargetNameByDate(availableTargetNames, dateById.getTime()));
        }
        if (CollectionUtils.isEmpty(shardingResult)) {
            return availableTargetNames;
        }
        return shardingResult;
    }
}
