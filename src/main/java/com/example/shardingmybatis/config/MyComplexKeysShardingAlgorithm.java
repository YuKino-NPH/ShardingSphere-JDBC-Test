package com.example.shardingmybatis.config;


import com.example.shardingmybatis.handler.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: 聂裴涵
 * @date: 2023/10/10  14:42
 */
@Component("myComplexKeysShardingAlgorithm")
public class MyComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> , ApplicationContextAware {
    private Properties prop=new Properties();
    private static List<KeyShardingRangeHandler> rangeHandlerList = new ArrayList<>();
    private static List<KeyShardingValueHandler> valueHandlerList = new ArrayList<>();
    private  ApplicationContext applicationContext;

    static {
        // 对于等于的处理
        valueHandlerList.add(new CrtTimeKeyShardingValueHandler());
        valueHandlerList.add(new IDKeyShardingValueHandler());
        valueHandlerList.add(new OrderNumberKeyShardingValueHandler());
        // 对于大于大于等区间的处理
        rangeHandlerList.add(new CrtTimeKeyShardingRangeHandler());
        rangeHandlerList.add(new IDKeyShardingRangeHandler());
        rangeHandlerList.add(new OrderNumberKeyShardingRangeHandler());
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
        Collection<String> valueSharding = doValueSharding(availableTargetNames, complexKeysShardingValue);
        Collection<String> rangeSharding = doRangeSharding(valueSharding, complexKeysShardingValue);
        if (CollectionUtils.isEmpty(rangeSharding)) {
            return availableTargetNames;
        }
        return rangeSharding;
    }

    private Collection<String> doValueSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
        if (CollectionUtils.isEmpty(valueHandlerList)) {
            return availableTargetNames;
        }
        if (complexKeysShardingValue.getColumnNameAndShardingValuesMap().size() == 0) {
            return availableTargetNames;
        }
        Set<String> tableSet = new HashSet<>(availableTargetNames);
        for (KeyShardingValueHandler keyShardingHandler : valueHandlerList) {
            tableSet.retainAll(keyShardingHandler.handle(tableSet, complexKeysShardingValue));
            if (tableSet.size() == 1) {
                break;
            }
        }
        if (CollectionUtils.isEmpty(tableSet)) {
            return availableTargetNames;
        }
        return tableSet;
    }

    private Collection<String> doRangeSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
        if (CollectionUtils.isEmpty(rangeHandlerList)) {
            return availableTargetNames;
        }
        if (complexKeysShardingValue.getColumnNameAndRangeValuesMap().size() == 0) {
            return availableTargetNames;
        }
        Set<String> tableSet = new HashSet<>(availableTargetNames);
        for (KeyShardingRangeHandler keyShardingHandler : rangeHandlerList) {
            tableSet.retainAll(keyShardingHandler.handle(tableSet, complexKeysShardingValue));
        }
        if (CollectionUtils.isEmpty(tableSet)) {
            return availableTargetNames;
        }
        return tableSet;
    }

    @Override
    public String getType() {
        return "myComplexKeysShardingAlgorithm";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


//    @Override
    public Properties getProps() {
        return this.prop;
    }

    @Override
    public void init(Properties properties) {
        if (properties==null){
            properties=new Properties();
        }
        this.prop=properties;
    }
}
