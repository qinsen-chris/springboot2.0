package com.example.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.dao.BillDao;
import com.example.demo.domain.UserBill;
import com.example.demo.entity.Bill;
import com.example.demo.entity.TUser;
import com.example.demo.mapper.TUserMapper;
import com.example.demo.service.ITUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private BillDao billDao;

    @Override
    @Cacheable(cacheNames = "queryListUser")
    public Page<UserBill> queryListUser( TUser user) {
        Page<UserBill> page = new Page<>(user.getCurr(),user.getNum());
        List<UserBill>  list = this.baseMapper.queryListUserBill(page,user);
        return page.setRecords(list);
    }

    //@Transactional(rollbackFor=Exception.class)
    //@Transactional(noRollbackFor=Exception.class)
    @Transactional
    @Override
    public void testTransactional() {
        Bill bill = new Bill();
        bill.setProductName("test");
        bill.setAmount(Double.valueOf(10000.88));
        bill.setNum(Integer.valueOf(2));
        billDao.save(bill);

        TUser user = new TUser();
        user.setUserName("test");
        user.setTelPhone("133333333333333333333");
        user.setAge(20);
        this.baseMapper.insert(user);
    }


    @Override
    @Cacheable(cacheNames = "users",key="'user_'+#username", unless = "#result == null")
    public TUser queryUserByUsername(String username) {

        System.err.println("没有走缓存！"+username);
        TUser user = this.baseMapper.queryUserByUsername(username);
        return user;
    }
}
