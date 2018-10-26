package com.example.demo.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by qs on 2018/9/10
 * email:qinsen@chinaredsun.com
 */
public class PdfCreatorTest {
    /**
     * 把sxghtml.ftl 模板文件拷到模板路径下
     * @throws Exception
     */
    @Test
    public void createByFreemarker() throws Exception {

        String rootPath = "D:/projectTest/temp/";
        String fileName = "应收账款转让确认函.pdf";

        String pdf = rootPath+fileName;
        String template = rootPath+"应收账款转让确认函.ftl";

        HashMap<String, Object> pars = new HashMap<String, Object>();
        pars.put("name", "张三");
        pars.put("age", 30);
        pars.put("contractNo","444ttttttttttttttggg");

        List<Animals> animals  = new ArrayList<>();
        Animals a1 = new Animals();
        a1.setName("a1");
        a1.setPrice("100");
        Animals a2 = new Animals();
        a2.setName("a2");
        a2.setPrice("200");
        animals.add(a1);
        animals.add(a2);
        pars.put("animals",animals);

        Animals a3 = new Animals();
        a3.setName("a3");
        a3.setPrice("300");
        pars.put("a3",a3);

        boolean success = PdfCreator.CreateByFreemarker(template, pars, pdf);
    }

}