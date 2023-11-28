package com.example.shardingmybatis.config;

import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * @author: 聂裴涵
 * @date: 2023/11/7  14:10
 */
@Component("myShardingIdGenerate")
public class MyShardingIdGenerate implements KeyGenerateAlgorithm {
    private Properties prop=new Properties();
    @Override
    public Comparable<?> generateKey() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        String uuid = UUID.randomUUID().toString();
        int uuidHashcode = uuid.replace("-", "").hashCode();
        if (uuidHashcode<0){
            uuidHashcode=-uuidHashcode;
        }
        return Long.parseLong(date+String.valueOf(uuidHashcode).substring(0,10));
    }



    @Override
    public String getType() {
        return "MY_SHARDING_ID_GENERATE";
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
