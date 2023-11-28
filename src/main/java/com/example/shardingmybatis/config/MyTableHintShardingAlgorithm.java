package com.example.shardingmybatis.config;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Properties;

/**
 * @author: 聂裴涵
 * @date: 2023/10/30  09:34
 */
@Component("myTableHintShardingAlgorithm")
public class MyTableHintShardingAlgorithm implements HintShardingAlgorithm<Comparable<?>> {
    private Properties prop=new Properties();
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Comparable<?>> hintShardingValue) {
        collection.forEach(System.out::println);
        System.out.println(hintShardingValue.getColumnName());
        hintShardingValue.getValues().forEach(System.out::println);
        return collection;
    }


    @Override
    public String getType() {
        return "myTableHintShardingAlgorithm";
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
