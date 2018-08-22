package com.example.demo.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.domain.UserBill;
import com.example.demo.entity.Bill;
import com.example.demo.entity.TUser;
import com.example.demo.service.IBillService;
import com.example.demo.service.ITUserService;
import com.example.demo.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qinsen
 * @since 2018-08-13
 */
@RestController
public class TUserController {
    @Autowired
    private ITUserService userService;
    @Autowired
    private IBillService billService;
    @Autowired
    private RedisCacheService redisCacheService;

    /**
     * mybatis方式
     * @param productName
     * @param amount
     * @param num
     * @return
     */
    @RequestMapping("/addBill")
    public Map addBill(String productName,String amount,int num){
        Map r = new HashMap<String, Object>();
        System.out.println(productName);
        Bill bill = new Bill();
        bill.setProductName(productName);
        bill.setAmount(Double.valueOf(amount));
        bill.setNum(Integer.valueOf(num));
        billService.saveBill(bill);

        r.put("code", 0);
        r.put("msg", "success");
        return r;
    }

    /**
     * mybatis plus 方式
     * @param userName
     * @param telPhone
     * @param age
     * @return
     */
    @RequestMapping("/addUser")
    public Map addUser(String userName,String telPhone,int age){
        TUser user = new TUser();
        user.setUserName(userName);
        user.setTelPhone(telPhone);
        user.setAge(age);
        userService.insert(user);

        Map r = new HashMap<String, Object>();
        r.put("code", 0);
        r.put("msg", "success");
        return r;
    }

    /**
     * 分页查询 自定义sql
     * @param userName
     * @param curr
     * @param num
     * @return
     */
    @RequestMapping("/queryUserBillList")
    public Page<UserBill> queryUserBillList(String userName,int curr,int num){
        TUser user = new TUser();
        user.setUserName("zhangsan");
        user.setCurr(0);
        user.setNum(10);
        Page<UserBill> list = userService.queryListUser(user);
        System.out.println(list);
        return  list;
    }

    /**
     * 测试事务
     */
    @RequestMapping("/testTransactional")
    public void testTransactional(){
        userService.testTransactional();
    }


    /**
     * 测试 Encache 缓存
     * @param userName
     * @return
     */
    @RequestMapping("/queryUserByUsername")
    public Map queryUserByUsername(String userName){
        TUser user = userService.queryUserByUsername(userName);
        System.out.println(user);

        Map r = new HashMap<String, Object>();
        r.put("code", 0);
        r.put("msg", "success");
        return r;
    }

    /**
     * 测试 redis 缓存
     * @param userName
     * @return
     */
    @RequestMapping("/queryUserByUsername2")
    public Map queryUserByUsername2(String userName){
        TUser userCache = redisCacheService.getDemoUser(userName);
        if(userCache != null){
            Map r = new HashMap<String, Object>();
            r.put("code", 0);
            r.put("msg", "缓存成功！");
            return r;
        }
        TUser user = userService.queryUserByUsername(userName);
        System.out.println(user);
        redisCacheService.setDemo1User(userName,user);
        Map r = new HashMap<String, Object>();
        r.put("code", 0);
        r.put("msg", "success");
        return r;
    }
}

