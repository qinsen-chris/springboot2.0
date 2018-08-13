package com.example.demo.service.impl;

import com.example.demo.entity.TUser;
import com.example.demo.mapper.TUserMapper;
import com.example.demo.service.ITUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qinsen
 * @since 2018-08-13
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
