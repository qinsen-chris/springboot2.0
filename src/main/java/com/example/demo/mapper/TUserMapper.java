package com.example.demo.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.domain.UserBill;
import com.example.demo.entity.TUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qinsen
 * @since 2018-08-13
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {

    List<UserBill> queryListUserBill(Pagination page, @Param("user") TUser user );

    TUser queryUserByUsername(@Param("userName") String userName );
}
