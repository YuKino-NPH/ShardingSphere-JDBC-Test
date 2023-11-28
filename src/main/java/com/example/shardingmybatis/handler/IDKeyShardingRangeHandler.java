package com.example.shardingmybatis.handler;


import com.example.shardingmybatis.utils.DateKeysSharding;
import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Date;

/**
 * @author: 聂裴涵
 * @date: 2023/10/11  09:20
 */
public class IDKeyShardingRangeHandler implements KeyShardingRangeHandler {
    @Override
    public Collection<String> handle(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
        try {
//            Range<Comparable<?>> idRange = complexKeysShardingValue.getColumnNameAndRangeValuesMap().get("id");
            Range<Comparable<?>> idRange = DateKeysSharding.getValueByKeyIgnoreCase("id",complexKeysShardingValue.getColumnNameAndRangeValuesMap());
            if (idRange == null) {
                return availableTargetNames;
            }
            String lowerEndpoint = null;
            String upperEndpoint = null;
            if (idRange.hasLowerBound()) {
                lowerEndpoint = String.valueOf(idRange.lowerEndpoint());
            }
            if (idRange.hasUpperBound()) {
                upperEndpoint = String.valueOf(idRange.upperEndpoint());
            }
            Date lowerDate = DateKeysSharding.getDateById(lowerEndpoint);
            Date upperDate = DateKeysSharding.getDateById(upperEndpoint);
            Range<Comparable<?>> comparableRange = DateKeysSharding.createRange(lowerDate, upperDate,idRange);
            return DateKeysSharding.culTablesBySql(comparableRange,availableTargetNames);
        } catch (Exception e) {
            return availableTargetNames;
        }
    }

}
