package com.example.shardingmybatis.handler;

import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

/**
 * @author 聂裴涵
 */
public interface KeyShardingRangeHandler {
    Collection<String> handle(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue);
}
