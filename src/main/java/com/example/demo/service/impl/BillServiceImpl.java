package com.example.demo.service.impl;

import com.example.demo.dao.BillDao;
import com.example.demo.entity.Bill;
import com.example.demo.service.IBillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BillServiceImpl implements IBillService{

	@Autowired
	private BillDao billDao;

	@Override
	public List<Bill> findBillList() {
		return null;
	}

	@Override
	public void saveBill(Bill bill) {
		try {
			billDao.save(bill);
		}catch (Exception e){
			log.error("保存bill失败！",e.getMessage());
		}

	}

	@Override
	public Bill queryObject(Object id) {
		return billDao.queryObject(id);
	}
}
