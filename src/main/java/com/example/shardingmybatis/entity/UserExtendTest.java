package com.example.shardingmybatis.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author: 聂裴涵
 * @date: 2023/10/17  10:06
 */
@TableName("user_extend")
@Getter
@Setter
@ToString
public class UserExtendTest {
    @TableId
    private Long id;

    private String username;
    private String orderNumber;
    private String phone;
    private Date crtTime;
    public static UserExtendTest from(UserExtend userExtend) {
        UserExtendTest test = new UserExtendTest();
        test.setId(userExtend.getId());
        test.setUsername(userExtend.getUsername());
        test.setOrderNumber(userExtend.getOrderNumber());
        test.setPhone(userExtend.getPhone());
        test.setCrtTime(userExtend.getCrtTime());
        return test;
    }
}
