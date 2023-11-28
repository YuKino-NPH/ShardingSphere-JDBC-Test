package com.example.shardingmybatis.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.shardingmybatis.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:08
 */
@Mapper
public interface UserEntityMapper extends BaseMapper<UserEntity> {
    void saveBatch(List<UserEntity> userEntityList);
    UserEntity selectByOrderNumberJoinExtend(String orderNumber);
    List<UserEntity> selectByCrtTimeLimit(Date crtTime);
    List<UserEntity> selectByCrtTimeJoinLimit(Date crtTime);
    List<UserEntity> selectByCrtTimeAndPhoneJoinLimit(@Param("crtTime") Date crtTime, @Param("phone") String phone);
    List<UserEntity> selectByCrtTimeLikePhone(@Param("crtTime") Date crtTime, @Param("phone") String phone);
    UserEntity selectByCrtTime(@Param("crtTime") Date crtTime, @Param("orderNumber") String orderNumber);
}
