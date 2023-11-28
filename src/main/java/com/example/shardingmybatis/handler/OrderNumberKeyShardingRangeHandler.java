package com.example.shardingmybatis.handler;


import com.example.shardingmybatis.utils.DateKeysSharding;
import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Date;

/**
 * @author: 聂裴涵
 * @date: 2023/10/11  10:38
 */
public class OrderNumberKeyShardingRangeHandler implements KeyShardingRangeHandler {
    @Override
    public Collection<String> handle(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
//        Map<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
//        Collection<Comparable<?>> orderNumbers = columnNameAndShardingValuesMap.get("order_number");
//        if (CollectionUtils.isEmpty(orderNumbers)){
//            return Collections.emptyList();
//        }
        try{
//            String orderNumber =String.valueOf(orderNumbers.iterator().next());
//            String dateTime = orderNumber.substring(4, 12);
//            return Collections.singleton(getAvailableTargetNameByDate(availableTargetNames, dateTime));
//            Range<Comparable<?>> orderNumberRange = complexKeysShardingValue.getColumnNameAndRangeValuesMap().get("order_number");
            Range<Comparable<?>> orderNumberRange = DateKeysSharding.getValueByKeyIgnoreCase("order_number",complexKeysShardingValue.getColumnNameAndRangeValuesMap());
            if (orderNumberRange == null) {
                return availableTargetNames;
            }
            String lowerEndpoint = null;
            String upperEndpoint = null;
            if (orderNumberRange.hasLowerBound()) {
                lowerEndpoint = String.valueOf(orderNumberRange.lowerEndpoint());
            }
            if (orderNumberRange.hasUpperBound()) {
                upperEndpoint = String.valueOf(orderNumberRange.upperEndpoint());
            }
            Date lowerDate = DateKeysSharding.getDateByOrderNumber(lowerEndpoint);
            Date upperDate = DateKeysSharding.getDateByOrderNumber(upperEndpoint);
            Range<Comparable<?>> comparableRange = DateKeysSharding.createRange(lowerDate, upperDate,orderNumberRange);
            return DateKeysSharding.culTablesBySql(comparableRange,availableTargetNames);
        }catch (Exception e){
            return availableTargetNames;
        }
    }
}
