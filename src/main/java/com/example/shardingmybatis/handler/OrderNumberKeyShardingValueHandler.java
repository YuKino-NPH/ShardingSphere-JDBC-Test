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
 * @date: 2023/10/11  18:55
 */
public class OrderNumberKeyShardingValueHandler implements KeyShardingValueHandler{
    @Override
    public Collection<String> handle(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
//        Collection<Comparable<?>> orderNumberList = complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("order_number");
        Collection<Comparable<?>> orderNumberList = DateKeysSharding.getValueByKeyIgnoreCase("order_number",complexKeysShardingValue.getColumnNameAndShardingValuesMap());
        if (CollectionUtils.isEmpty(orderNumberList)){
            return availableTargetNames;
        }
        Set<String> shardingResult=new HashSet<>();
        for (Comparable<?> temp : orderNumberList) {
            String orderNumber=String.valueOf(temp);
            Date dateByOrderNumber = DateKeysSharding.getDateByOrderNumber(orderNumber);
            if (dateByOrderNumber==null){
                continue;
            }
            shardingResult.add(DateKeysSharding.getAvailableTargetNameByDate(availableTargetNames,dateByOrderNumber.getTime()));
        }
        if (CollectionUtils.isEmpty(shardingResult)) {
            return availableTargetNames;
        }
        return shardingResult;
    }
}
