package com.example.shardingmybatis.utils;


import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author: 聂裴涵
 * @date: 2023/10/10  14:29
 */
public class DateUuidGenerator {

    //    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
//        Serializable generate = super.generate(session, obj);
//        String uuid = String.valueOf(generate);
//        int uuidHashcode = uuid.replace("-", "").hashCode();
//        if (uuidHashcode<0){
//            uuidHashcode=-uuidHashcode;
//        }
//        return Long.parseLong(new Date().getTime()+String.valueOf(uuidHashcode).substring(0,5));
//    }
    static int count = 1;

    public static Long generateId(String date) {
        String uuid = UUID.randomUUID().toString();

        String replace = uuid.replace("-", "");
        char[] charArray = replace.toCharArray();
        for (int i = 0; i < 12; i++) {
            if (charArray[i] < 48 || charArray[i] > 57) {
                charArray[i] = (char) RandomUtils.nextInt(48, 58);
            }
        }
        uuid = StringUtils.valueOf(charArray);
        String s = StringUtils.leftPad(String.valueOf(count), 8, "0");
        return Long.parseLong(date + uuid.substring(0, 3)+s);
    }

    public static Long generateId(Date date) {
        String uuid = UUID.randomUUID().toString();

        String replace = uuid.replace("-", "");
        char[] charArray = replace.toCharArray();
        for (int i = 0; i < 6; i++) {
            if (charArray[i] < 48 || charArray[i] > 57) {
                charArray[i] = (char) RandomUtils.nextInt(48, 58);
            }
        }
        uuid = StringUtils.valueOf(charArray);
        return Long.parseLong(date.getTime() + uuid.substring(0, 5));
    }
}
