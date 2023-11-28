package com.example.shardingmybatis.config;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Properties;

/**
 * @author: 聂裴涵
 * @date: 2023/10/30  10:15
 */
@Component("myDefaultDataSourceShardingAlgorithm")
public class MyDefaultDataSourceShardingAlgorithm implements HintShardingAlgorithm<Comparable<?>> {
    private Properties prop=new Properties();
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Comparable<?>> hintShardingValue) {
        return null;
    }


    @Override
    public String getType() {
        return "myDefaultDataSourceShardingAlgorithm";
    }

//    @Override
//    public Properties getProps() {
//        return this.prop;
//    }

    @Override
    public void init(Properties properties) {
        if (properties==null){
            properties=new Properties();
        }
        this.prop=properties;
    }
}
