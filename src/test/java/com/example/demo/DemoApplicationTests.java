package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		// 获取题目(5题),
		List<Integer> generalKnowledgeIdList  = new ArrayList();
		generalKnowledgeIdList.add(10);
		generalKnowledgeIdList.add(11);
		generalKnowledgeIdList.add(12);
		generalKnowledgeIdList.add(13);
		generalKnowledgeIdList.add(14);
		generalKnowledgeIdList.add(15);
		generalKnowledgeIdList.add(16);
		List<Integer> IdList = new ArrayList();

		for(int i = 0; i < 4; i++){
			int GeneralKnowledgeIdSize = generalKnowledgeIdList.size();
			int orderNum = (int)Math.floor(Math.random()*GeneralKnowledgeIdSize);
			Integer id = generalKnowledgeIdList.remove(orderNum);
			IdList.add(id);
		}
		System.out.println(IdList.toString());

		double dou = Math.random();
		System.out.println(dou);
		int orderNum = (int)Math.floor(dou*90);
		System.out.println(orderNum);
	}

}
