package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by qs on 2018/8/9
 * email:qinsen@chinaredsun.com
 */
@RestController
public class TestIndexController {
    @Autowired
    private IBillService billService;

    @RequestMapping("/test")
    public String index(){
        /*try {
            throw new  Exception("index test!");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return "test";
    }

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
}
