package com.example.demo.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.domain.UserBill;
import com.example.demo.entity.TUser;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qinsen
 * @since 2018-08-13
 */
public interface ITUserService extends IService<TUser> {


    Page<UserBill> queryListUser(TUser user );

    void testTransactional();

    TUser queryUserByUsername(String username);
}
