package com.example.demo;


import com.example.demo.entity.TUser;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Slf4j
public class CacheTest {

    public static void main(String[] args) throws IOException {

        // 1. 创建缓存管理器
        //CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");
        CacheManager cacheManager = new CacheManager(new ClassPathResource("ehcache.xml").getInputStream());
        //创建Spring的CacheManager
        EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager();
        //设置底层的CacheManager
        cacheCacheManager.setCacheManager(cacheManager);
        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("users");
        // 3. 创建元素
        Element element = new Element("key1", "value1");
        // 4. 将元素添加到缓存
        cache.put(element);
        // 5. 获取缓存
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());

        // 6. 删除元素
        cache.remove("key1");

        TUser dog = new TUser();
        dog.setUserName("taidi");
        Element element2 = new Element("taidi", dog);
        cache.put(element2);
        Element value2 = cache.get("taidi");
        TUser dog2 = (TUser) value2.getObjectValue();
        System.out.println(dog2);
        System.out.println(cache.getSize());

        // 7. 刷新缓存
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();
    }


    @Test
    public void operation() throws IOException{
       // CacheManager manager = CacheManager.newInstance("src/test/resources/ehcache/ehcache.xml");
        CacheManager manager = new CacheManager(new ClassPathResource("ehcache.xml").getInputStream());
        // 获得Cache的引用
        Cache cache = manager.getCache("users");

        // 将一个Element添加到Cache
        cache.put(new Element("key1", "value1"));

        // 获取Element，Element类支持序列化，所以下面两种方法都可以用
        Element element1 = cache.get("key1");
        // 获取非序列化的值
        log.debug("key:{}, value:{}", element1.getObjectKey(), element1.getObjectValue());
        // 获取序列化的值
        log.debug("key:{}, value:{}", element1.getKey(), element1.getValue());

        // 更新Cache中的Element
        cache.put(new Element("key1", "value2"));
        Element element2 = cache.get("key1");
        log.debug("key:{}, value:{}", element2.getObjectKey(), element2.getObjectValue());

        // 获取Cache的元素数
        log.debug("cache size:{}", cache.getSize());

        // 获取MemoryStore的元素数
        log.debug("MemoryStoreSize:{}", cache.getMemoryStoreSize());

        // 获取DiskStore的元素数
        log.debug("DiskStoreSize:{}", cache.getDiskStoreSize());

        // 移除Element
        cache.remove("key1");
        log.debug("cache size:{}", cache.getSize());

        // 关闭当前CacheManager对象
        manager.shutdown();

        // 关闭CacheManager单例实例
        CacheManager.getInstance().shutdown();
    }
}