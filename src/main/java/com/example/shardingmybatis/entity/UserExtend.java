package com.example.shardingmybatis.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @author: 聂裴涵
 * @date: 2023/10/12  10:36
 */
@Data
@TableName("user_extend_sharding")
public class UserExtend {
    @TableId(type = IdType.INPUT)
    private Long id;

    private String username;
    private String orderNumber;
    private String phone;
    private Date crtTime;
}
