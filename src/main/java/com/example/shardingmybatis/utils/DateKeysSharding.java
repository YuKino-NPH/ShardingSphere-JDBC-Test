package com.example.shardingmybatis.utils;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: 聂裴涵
 * @date: 2023/10/11  10:23
 */
public class DateKeysSharding {
    public static String getAvailableTargetNameByDate(Collection<String> availableTargetNames, long dateTime) {
        for (String availableTargetName : availableTargetNames) {
            Date date = getDateByTableName(availableTargetName);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(Calendar.MONTH, 3);
            Date time = instance.getTime();
            // 不加等号，表示10月1日的单，放到10月的表里
            if (dateTime < time.getTime()&& dateTime>=date.getTime()) {
                return availableTargetName;
            }
        }
        return null;
    }

    public static String getAvailableTargetNameByDate(Collection<String> availableTargetNames, String dateTime) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = yyyyMMdd.parse(dateTime);
            return getAvailableTargetNameByDate(availableTargetNames, date.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDateByTableName(String tableName) {
        String[] split = tableName.split("_");
        String time = split[split.length - 1];
        SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
        Date date;
        try {
            date = yyyyMM.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static Collection<String> culTablesBySqlAndDate(Range<Date> valueRange, Collection<String> availableTargetNames) {
        if (valueRange == null) {
            return Collections.emptyList();
        }
        System.out.println(valueRange);
        List<String> shardingResult = new ArrayList<>();
        for (String availableTargetName : availableTargetNames) {
            Date dateByTableName = DateKeysSharding.getDateByTableName(availableTargetName);
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateByTableName);
            instance.add(Calendar.MONTH, 3);
            Date addThreeMonth = instance.getTime();
            Range<Date> tableRange = Range.open(dateByTableName, addThreeMonth);
            try {
                Range<Date> intersection = valueRange.intersection(tableRange);
                shardingResult.add(availableTargetName);
            } catch (Exception e) {
//                System.out.println(valueRange + ":不存在交集:" + tableRange);
            }
        }
        return shardingResult;
    }

    public static Collection<String> culTablesBySql(Range<Comparable<?>> valueRange, Collection<String> availableTargetNames) {
        if (valueRange == null) {
            return availableTargetNames;
        }
        System.out.println(valueRange);
        List<String> shardingResult = new ArrayList<>();
        for (String availableTargetName : availableTargetNames) {
            Date dateByTableName = DateKeysSharding.getDateByTableName(availableTargetName);
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateByTableName);
            instance.add(Calendar.MONTH, 3);
            Date addThreeMonth = instance.getTime();
            Range<Comparable<?>> tableRange = Range.open(dateByTableName, addThreeMonth);
            try {
                Range<Comparable<?>> intersection = valueRange.intersection(tableRange);
                shardingResult.add(availableTargetName);
            } catch (Exception e) {
//                System.out.println(valueRange + ":不存在交集:" + tableRange);
            }
        }
        if (CollectionUtils.isEmpty(shardingResult)) {
            return availableTargetNames;
        }
        return shardingResult;
    }

    public static Range<Comparable<?>> createRange(Comparable<?> lowerEndpoint, Comparable<?> upperEndpoint, Range<Comparable<?>> range) {
        if (lowerEndpoint == null && upperEndpoint == null) {
            return null;
        }
        if (upperEndpoint == null) {
            return Range.downTo(lowerEndpoint, range.hasLowerBound() ? range.lowerBoundType() : BoundType.OPEN);
        }
        if (lowerEndpoint == null) {
            return Range.upTo(upperEndpoint, range.hasUpperBound() ? range.upperBoundType() : BoundType.OPEN);
        }
        return Range.range(lowerEndpoint, range.hasLowerBound() ? range.lowerBoundType() : BoundType.OPEN,
                upperEndpoint, range.hasUpperBound() ? range.upperBoundType() : BoundType.OPEN);
    }

    public static Date getDateById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        long dateTimeId = Long.parseLong(id.substring(0, 13));
        return new Date(dateTimeId);
    }

    public static Date getDateByOrderNumber(String orderNumber) {
        if (StringUtils.isBlank(orderNumber)) {
            return null;
        }
        String dateTime = orderNumber.substring(4, 12);
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        try {
            return yyyyMMdd.parse(dateTime);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T getValueByKeyIgnoreCase(String key, Map<String, T> map){
        T val = map.get(key);
        if (val!=null){
            return val;
        }
        val=map.get(key.toLowerCase());
        if (val!=null){
            return val;
        }
        val=map.get(key.toUpperCase());
        return val;
    }

}
