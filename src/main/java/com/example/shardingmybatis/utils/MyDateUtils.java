package com.example.shardingmybatis.utils;

import org.apache.commons.lang3.RandomUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  16:28
 */
public class MyDateUtils {
    public static Date getRandomDate(Date date){
        Calendar calendar = Calendar.getInstance();
        long now = calendar.getTimeInMillis();
        long before = date.getTime();
        Random random = new Random();


        //产生long类型指定范围随机数
        long randomDate = before + RandomUtils.nextLong(0,now-before);
        return new Date(randomDate);
    }
}
