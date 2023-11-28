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
 * @date: 2023/10/11  18:32
 */
public class CrtTimeKeyShardingValueHandler implements KeyShardingValueHandler {
    @Override
    public Collection<String> handle(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
//        Collection<Comparable<?>> crtTimeList = complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("crt_time");
        Collection<Comparable<?>> crtTimeList = DateKeysSharding.getValueByKeyIgnoreCase("crt_time",complexKeysShardingValue.getColumnNameAndShardingValuesMap());
        if (CollectionUtils.isEmpty(crtTimeList)){
            return availableTargetNames;
        }
        Set<String> shardingResult = new HashSet<>();
        for (Comparable<?> temp : crtTimeList) {
            Date crtTime = (Date) temp;
            String availableTargetNameByDate = DateKeysSharding.getAvailableTargetNameByDate(availableTargetNames, crtTime.getTime());
            shardingResult.add(availableTargetNameByDate);
        }
        if (CollectionUtils.isEmpty(shardingResult)) {
            return availableTargetNames;
        }
        return shardingResult;
    }
}
