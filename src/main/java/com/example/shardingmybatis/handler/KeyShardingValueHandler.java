package com.example.shardingmybatis.handler;

import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

/**
 * @author: 聂裴涵
 * @date: 2023/10/11  18:31
 */
public interface KeyShardingValueHandler {
    Collection<String> handle(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue);
}
