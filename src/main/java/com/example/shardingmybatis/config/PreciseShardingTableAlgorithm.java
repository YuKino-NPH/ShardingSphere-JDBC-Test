package com.example.shardingmybatis.config;


import com.example.shardingmybatis.utils.DateKeysSharding;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Properties;

/**
 * @author: 聂裴涵
 * @date: 2023/10/10  09:48
 */
@Slf4j
@Component(value = "preciseShardingTableAlgorithm")
public class PreciseShardingTableAlgorithm implements StandardShardingAlgorithm<Date> {
    private Properties prop=new Properties();

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
        return DateKeysSharding.getAvailableTargetNameByDate(availableTargetNames, preciseShardingValue.getValue().getTime());
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> rangeShardingValue) {
       return DateKeysSharding.culTablesBySqlAndDate(rangeShardingValue.getValueRange(),availableTargetNames);
//        System.out.println(valueRange);
//        List<String> shardingResult = new ArrayList<>();
//        for (String availableTargetName : availableTargetNames) {
//            Date dateByTableName = DateKeysSharding.getDateByTableName(availableTargetName);
//            Calendar instance = Calendar.getInstance();
//            instance.setTime(dateByTableName);
//            instance.add(Calendar.MONTH, 3);
//            Date addThreeMonth = instance.getTime();
//            Range<Date> tableRange = Range.open(dateByTableName, addThreeMonth);
//            try {
//                Range<Date> intersection = valueRange.intersection(tableRange);
//                shardingResult.add(availableTargetName);
//            } catch (Exception e) {
//                System.out.println(valueRange+":不存在交集:"+tableRange);
//            }
//        }
    }


    @Override
    public String getType() {
        return "DATE_SPI";
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
