package com.example.shardingmybatis.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @author: 聂裴涵
 * @date: 2023/10/10  14:38
 */
@Getter
@Setter
@ToString
@TableName("user_entity_sharding")
public class UserEntity extends Entity{
    @TableId(type = IdType.INPUT)
    private Long id;
    private String username;
    private String orderNumber;
    private String phone;
    private Date crtTime;
    private Long userExtendId;

}
