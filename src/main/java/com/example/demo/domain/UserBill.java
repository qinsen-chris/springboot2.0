package com.example.demo.domain;

import lombok.Data;
import org.springframework.context.annotation.Bean;

/**
 * Create by qs on 2018/8/13
 * email:qinsen@chinaredsun.com
 */
@Data
public class UserBill {
    private String userName;
    private String telPhone;

    private String productName;// 商品名称
    private Integer num;// 商品数量
    private Double amount;//金额

}
