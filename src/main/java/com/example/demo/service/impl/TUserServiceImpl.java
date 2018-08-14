package com.example.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.domain.UserBill;
import com.example.demo.entity.TUser;
import com.example.demo.mapper.TUserMapper;
import com.example.demo.service.ITUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public Page<UserBill> queryListUser( TUser user) {
        Page<UserBill> page = new Page<>(user.getCurr(),user.getNum());
        List<UserBill>  list = this.baseMapper.queryListUserBill(page,user);
        return page.setRecords(list);
    }
}
