package com.example.demo.service;

import com.example.demo.entity.Bill;

import java.util.List;


public interface IBillService {

	List<Bill> findBillList();

	void saveBill(Bill bill);

	Bill queryObject(Object id);

}
