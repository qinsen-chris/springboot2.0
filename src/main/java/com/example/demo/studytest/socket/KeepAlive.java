package com.example.demo.studytest.socket;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: qinsen
 * @CreateDate: 2019/5/23 10:45
 * @Version: 1.0
 */
public class KeepAlive implements Serializable {

    private static final long serialVersionUID = -4834591092043053048L;

    /* 覆盖该方法，仅用于测试使用。
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t维持连接包";
    }
}
