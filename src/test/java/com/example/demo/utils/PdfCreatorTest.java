package com.example.demo.utils;

import org.junit.Test;

import java.util.HashMap;

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
        String fileName = "pdf-ferrmarker.pdf";

        String pdf = rootPath+fileName;
        String template = rootPath+"sxghtml.ftl";

        HashMap<String, Object> pars = new HashMap<String, Object>();
        pars.put("name", "张三");
        pars.put("age", 30);
        pars.put("contractNo","444ttttttttttttttggg");

        boolean success = PdfCreator.CreateByFreemarker(template, pars, pdf);
    }

}