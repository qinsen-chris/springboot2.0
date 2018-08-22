package com.example.demo.service;

import com.example.demo.entity.TUser;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.RedisUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by ytf on 2017/12/9.
 */
@Component
public class RedisCacheService {

    @Autowired
    private RedisUtils redisUtils;

    //测试 user 缓存
    private static final String DEMO1_USER = "demo1_bill";

    public void setDemo1User(String key, Object value){
        redisUtils.set(DEMO1_USER+key,value,setTime());
    }

    public TUser getDemoUser(String key){
        String result = redisUtils.get(DEMO1_USER+key);
        Gson gson = new Gson();
        TUser user = gson.fromJson(result,TUser.class);
        return user;
    }

    //参与活动人次 KEY
    private static final String ACTIVITY_JOINTIMES_KEY_PREX = "activity_join_time_";
    /**
     * 获取某活动参与人数
     * @param id
     * @return
     */
    public int getActivityJoinTimes(Long id){
        String key = ACTIVITY_JOINTIMES_KEY_PREX+String.valueOf(id);
        Integer joinTimes = redisUtils.get(key,Integer.class);
        return joinTimes==null?0:joinTimes;
    }

    /**
     * 记录活动参与人次
     */
    public void addActivityJoinTimes(Long id){
        String key = ACTIVITY_JOINTIMES_KEY_PREX+String.valueOf(id);
        Integer joinTimes = redisUtils.get(key,Integer.class);
        if(joinTimes!=null){
            redisUtils.set(key,++joinTimes);
        }
        else {
            redisUtils.set(key,0);
        }
    }

    /**
     * @Discription: 保存获取到的题目
     * @Author lzj
     * @Date  19:48 2018/1/29
     * @Version 1.0
     */
    public void setProblems(String key, Object value){
        redisUtils.set(key, value, setTime());
    }

    /**
     * @Discription: 从缓存中获取题目
     * @Author lzj
     * @Date  10:21 2018/1/30
     * @Version 1.0
     */
    public List<Integer> getProblems(String key){
        String str = redisUtils.get(key);
        Gson gson = new Gson();
        Type jsonType = new TypeToken<List<Integer>>() {}.getType();
        return gson.fromJson(str,jsonType);
    }

    /**
     * @Discription: 将当前用户的取题记录保存至缓存中
     * @Author lzj
     * @Date  19:48 2018/1/29
     * @Version 1.0
     */
    public void setUserGetProblemRecord(String key, int count){
    	key = key +"_"+ DateUtil.format(new Date(), "yyyyMMdd");
        redisUtils.set(key, count, setTime());
    }

    public Integer getUserGetProblemRecord(String key){
    	key = key +"_"+ DateUtil.format(new Date(), "yyyyMMdd");
        return redisUtils.get(key, Integer.class);
    }

    /**
     * @Discription: 将取出来的5道题目保存到缓存中
     * @Author lzj
     * @Date  15:03 2018/1/30
     * @Version 1.0
     */
/*    public void setChoicedProblems(String key, List<QuestionAndOptionVO> questionAndOptionVOList){
        redisUtils.set(key, questionAndOptionVOList, setTime());
    }*/

    /**
     * @Discription: 将缓存中的5道题目取出来
     * @Author lzj
     * @Date  15:03 2018/1/30
     * @Version 1.0
     */
/*    public List<QuestionAndOptionVO> getChoicedProblems(String key){
        String str = redisUtils.get(key);
        Gson gson = new Gson();
        Type jsonType = new TypeToken<List<QuestionAndOptionVO>>() {}.getType();
        return gson.fromJson(str,jsonType);
    }*/

    /**
     * @Discription: 缓存时间设置
     * @Author lzj
     * @Date  20:15 2018/1/30
     * @Version 1.0
     */
    public Long setTime(){
        long endTime = DateUtil.getDayEnd(new Date()).getTime();
        return (endTime - System.currentTimeMillis())/1000;
    }
    
    /**
     * 缓存存储回合id
     * @param key
     * @param value
     */
    public void setRoundId(String key, String value){
        redisUtils.set("activity_round_key_"+key, value, setTime());
    }

    /**
     * @Discription: 从缓存中获取当前回合id
     * @Author lzj
     * @Date  10:21 2018/1/30
     * @Version 1.0
     */
    public String getRoundId(String key){
    	return redisUtils.get("activity_round_key_"+key, String.class);
    }
    
    /**
     * 统计设备终端参与活动次数
     * @param activityId 活动id
     * @param equipId 设备唯一id
     * @param currentDay 是否按天统计
     */
    public void setActivityJoinTimes(long activityId, String equipId, boolean currentDay){
    	StringBuilder key = new StringBuilder();
    	key.append("activity_jointimes_");
    	key.append(activityId);
    	if (StringUtils.isNotBlank(equipId)) {
    		key.append("_"+equipId);
    	}
    	if (currentDay) {
    		key.append("_"+DateUtil.format(new Date(), "yyyyMMdd"));
    	}
    	Integer joinTimes = redisUtils.get(key.toString(), Integer.class);
        if (joinTimes == null) {
        	joinTimes = 0;
        }
        if (currentDay){//当日有效
        	redisUtils.set(key.toString(), ++joinTimes, setTime());
        } else {
        	redisUtils.set(key.toString(), ++joinTimes);
        }
    }
    
    /**
     * 获取设备终端参与活动次数
     * @param activityId 活动id
     * @param equipId 设备唯一id
     * @param currentDay 是否按天统计
     */
    public int getActivityJoinTimes(long activityId, String equipId, boolean currentDay){
    	StringBuilder key = new StringBuilder();
    	key.append("activity_jointimes_");
    	key.append(activityId);
    	if (StringUtils.isNotBlank(equipId)) {
    		key.append("_"+equipId);
    	}
    	if (currentDay) {
    		key.append("_"+DateUtil.format(new Date(), "yyyyMMdd"));
    	}
    	
        Integer joinTimes = redisUtils.get(key.toString(), Integer.class);
        if (joinTimes == null) {
        	joinTimes = 0;
        }
        
        return joinTimes;
    }
}
