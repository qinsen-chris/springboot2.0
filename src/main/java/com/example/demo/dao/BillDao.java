package com.example.demo.dao;

import com.example.demo.entity.Bill;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by chenmy on 2018/6/20.
 */
@Mapper
public interface BillDao {
    void save(Bill bill);

    Bill queryObject(Object id);
}
