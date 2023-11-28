package com.example.shardingmybatis.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author: 聂裴涵
 * @date: 2023/10/17  10:05
 */
@Getter
@Setter
@ToString
@TableName("user_entity")
public class UserEntityTest {
    @TableId
    private Long id;
    private String username;
    private String orderNumber;
    private String phone;
    private Date crtTime;
    private Long userExtendId;
    public static UserEntityTest from(UserEntity userEntity) {
        UserEntityTest test = new UserEntityTest();
        test.setId(userEntity.getId());
        test.setUsername(userEntity.getUsername());
        test.setOrderNumber(userEntity.getOrderNumber());
        test.setPhone(userEntity.getPhone());
        test.setCrtTime(userEntity.getCrtTime());
        test.setUserExtendId(userEntity.getUserExtendId());
        return test;
    }
}
