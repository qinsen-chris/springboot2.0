package com.example.demo.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

//import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述: </br> 〈功能详细描述〉
 *
 * @class BeanUtil
 * @auth lei_bo
 * @date 2017/7/13
 * @see [相关类/方法](可选)
 * @since 1.0.0
 */
@Slf4j
public final class BeanUtil {

  public static void map2Bean(Map<String, Object> map, Object obj) throws Exception {

    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor property : propertyDescriptors) {
        String key = property.getName();
        if (map.containsKey(key)) {
          Object value = map.get(key);
          // 得到property对应的setter方法
          Method setter = property.getWriteMethod();
          setter.invoke(obj, value);
        }
      }
    } catch (Exception e) {
      throw e;
    }
    return;

  }

  // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
  public static Map<String, Object> bean2Map(Object obj) throws Exception {

    if (obj == null) {
      return null;
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor property : propertyDescriptors) {
        String key = property.getName();
        // 过滤class属性
        if (!key.equals("class")) {
          // 得到property对应的getter方法
          Method getter = property.getReadMethod();
          Object value = getter.invoke(obj);
          map.put(key, value);
        }
      }
    } catch (Exception e) {
      System.out.println("transBean2Map Error " + e);
      throw e;
    }
    return map;
  }
  
  /*public static void bean2bean(Object orig, Object dest) throws Exception {
    if (orig==null) {
      dest = null;
      return;
    }
    try {
      PropertyUtils.copyProperties(dest, orig);
    } catch (Exception e) {
      log.error("对象转换异常", e);
      throw e;
    }
  } */
  
  /**
   * 把 VO 中所有属性为 null 的转为 ""
   *
   */
  public static Object null2String(Object obj) {
      if (obj != null) {
          Class<? extends Object> classz = obj.getClass();
          // 获取所有该对象的属性值
          Field fields[] = classz.getDeclaredFields();
          // 遍历属性值，取得所有属性为 null 值的
          for (Field field : fields) {
              try {
                  Type t = field.getGenericType();
                  if (StringUtils.indexOf(t.toString(), "String") != -1) {
                      Method m = classz.getMethod("get" + change(field.getName()));
                      Object name = m.invoke(obj);// 调用该字段的get方法
                      if (name == null) {
                          Method mtd = classz.getMethod("set" + change(field.getName()),
                                  new Class[] { String.class });// 取得所需类的方法对象
                          mtd.invoke(obj, new Object[] { "" });// 执行相应赋值方法
                      }
                  }
              } catch (Exception e) {
                  log.error("对象属性转换异常",e);
              }
          }
      }
      return obj;
  }

  /**
   * @param src
   *            源字符串
   * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
   */
  private static String change(String src) {
      if (src != null) {
          StringBuffer sb = new StringBuffer(src);
          sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
          return sb.toString();
      } else {
          return null;
      }
  }
}
