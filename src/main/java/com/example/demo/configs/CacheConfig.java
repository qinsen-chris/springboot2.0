/**
 * @(#)CacheConfig 1.0-SNAPSHOT 2018/6/6 14:34
 * <p>
 * Copyright © 2018 红太阳金控.  All rights reserved.
 */
package com.example.demo.configs;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;


/**
 * 缓存配置
 *
 * @author zhanghao
 * @version $$ Revision:1.0-SNAPSHOT, $$ Date: 2018/6/6 14:34 $$
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class CacheConfig {

    /**************************************************** redis cache config ****************************************************************/
/*    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration defaultRedisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheConfiguration redisCacheConfiguration = defaultRedisCacheConfiguration
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }*/

    /**************************************************** ehcache cache config ****************************************************************/
    @Bean
    @Primary
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerUtils.buildCacheManager();
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    /**
     * 缓存名称 配置
     */
    public class CacheName {
        /**
         * 当前系统缓存公共前缀
         */
        public static final String CURR_SYS_CACHE_PREFIX = "demo1:";

        /**
         * 用户模块缓存前缀
         */
        public static final String USER = CURR_SYS_CACHE_PREFIX + "user:";

        /**
         * 资讯模块缓存前缀
         */
        public static final String NEWS = CURR_SYS_CACHE_PREFIX + "news:";

        /**
         * 系统模块缓存前缀
         */
        public static final String SYS = CURR_SYS_CACHE_PREFIX + "sys:";
    }
}
