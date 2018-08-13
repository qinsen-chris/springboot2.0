package com.example.demo.configs.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.example.demo.utils.DateUtil;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * Create by qs on 2018/8/13
 * email:qinsen@chinaredsun.com
 */
public class MyMetaObjectHandler extends MetaObjectHandler {
    /**
     * <p>
     * 插入元对象字段填充
     * </p>
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("*************************");
        System.out.println("insert fill");
        System.out.println("*************************");

        // 测试下划线
/*        Object testType = getFieldValByName("testType", metaObject);//mybatis-plus版本2.0.9+
        System.out.println("testType=" + testType);
        if (testType == null) {
            setFieldValByName("testType", 3, metaObject);//mybatis-plus版本2.0.9+
        }*/

        //setFieldValByName("version", 0, metaObject);
        setFieldValByName("createDate", DateUtil.currentDate(), metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     * Created with IntelliJ IDEA.
     * Author:  Wu Yujie
     * Email:  coffee377@dingtalk.com
     * Time:  2017/04/16 15:03
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
       /* MyLoginUser loginUser = MyLoginUser.get();

        if (loginUser != null) {
            setFieldValByName("modifiedUser", loginUser.getUserId(), metaObject);
        }*/
        setFieldValByName("modifiedDate",DateUtil.currentDate(), metaObject);
    }
}
