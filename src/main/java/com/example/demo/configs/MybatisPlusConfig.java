/**
 * @(#)MybatisPlusConfig 1.0-SNAPSHOT 2018/6/12 10:40
 * <p>
 * Copyright © 2018 红太阳金控.  All rights reserved.
 */
package com.example.demo.configs;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by qs on 2018/8/13
 * email:qinsen@chinaredsun.com
 */
@Configuration
@MapperScan({"com.example.demo.mapper","com.example.demo.dao"})
public class MybatisPlusConfig {
    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
