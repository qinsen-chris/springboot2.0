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

    @Test
    public void createByFreemarker_entexamine() throws Exception {
        String rootPath = "D:/projectTest/temp/entexamine/";
        String template = rootPath+"ent_examine.ftl";
        HashMap<String, Object> pars = new HashMap<String, Object>();

        String fileName = "ent_examine.pdf";
        String pdf = rootPath+fileName;
        PdfCreator.CreateByFreemarker(template, pars, pdf);
    }

    @Test
    public void createByFreemarker_contConfirm() throws Exception {
        String rootPath = "D:/projectTest/temp/";
        String template = rootPath+"contConfirm1.ftl";
        HashMap<String, Object> pars = new HashMap<String, Object>();

        String fileName = "contConfirm.pdf";
        String pdf = rootPath+fileName;

        List<Animals> animals  = new ArrayList<>();
        Animals a1 = new Animals();
        a1.setName("a1");
        a1.setPrice("100");
        Animals a2 = new Animals();
        a2.setName("a2");
        a2.setPrice("200");
//        animals.add(a1);
//        animals.add(a2);
        pars.put("debtLists",animals);
        pars.put("ticketLists",animals);
        pars.put("contBean", BeanUtil.null2String(a1));
        pars.put("baseContBean", BeanUtil.null2String(a1));
        PdfCreator.CreateByFreemarker(template, pars, pdf);
    }

    @Test
    public void tests(){
        String path1 = PdfCreator.class.getResource("/templates/contConfirm1.ftl").getPath();
        //getResource("/") 在编译环境没有问题，但是在jar包中就会返回Null
        String path2 = this.getClass().getResource("/").getPath();

        String ss = "D:/github-program/springboot2.0/target/classes/templates/contConfirm1.ftl";
        ss = ss.substring(0,ss.lastIndexOf("/")+1);
        System.out.println(ss);
        System.out.println(path1);
        System.out.println(path2);
    }

}