package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * Create by qs on 2018/8/13
 * email:qinsen@chinaredsun.com
 */
@Data
public class BaseEntry {
    @TableField(exist = false)
    private int curr;
    @TableField(exist = false)
    private int num;
    @TableField(exist = false)
    private int version;
}
