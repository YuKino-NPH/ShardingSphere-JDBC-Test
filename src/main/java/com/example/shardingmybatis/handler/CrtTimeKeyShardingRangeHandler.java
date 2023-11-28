package com.example.shardingmybatis.handler;


import com.example.shardingmybatis.utils.DateKeysSharding;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

/**
 * @author: 聂裴涵
 * @date: 2023/10/11  10:34
 */
public class CrtTimeKeyShardingRangeHandler implements KeyShardingRangeHandler {
    @Override
    public Collection<String> handle(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
//        Map<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
//        Collection<Comparable<?>> crtTimes= columnNameAndShardingValuesMap.get("crt_time");
//        if (CollectionUtils.isEmpty(crtTimes)){
//            return Collections.emptyList();
//        }
        try{
//            Date crtTime = (Date) crtTimes.iterator().next();
//            long dateTimeId = crtTime.getTime();
//            return DateKeysSharding.culTablesBySql(complexKeysShardingValue.getColumnNameAndRangeValuesMap().get("crt_time"),availableTargetNames);
            return DateKeysSharding.culTablesBySql(DateKeysSharding.getValueByKeyIgnoreCase("crt_time",complexKeysShardingValue.getColumnNameAndRangeValuesMap()),availableTargetNames);
        }catch (Exception e){
            return availableTargetNames;
        }
    }
}
