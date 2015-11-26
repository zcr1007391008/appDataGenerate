package com.zcr.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;

import com.test.sevices.RedisCacheUtil;


public class SpringDataRedisTestMain {  
	  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
       /* ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");  
        RedisTemplate redisTemplate = (RedisTemplate)context.getBean("jedisTemplate");  
        //其中key采取了StringRedisSerializer  
        //其中value采取JdkSerializationRedisSerializer  
        ValueOperations<String,String> a = redisTemplate.opsForValue();
        a.set("who","xiaozheng");
        ValueOperations<String, User> valueOper = redisTemplate.opsForValue();  
        User u1 = new User("zhangsan",12);  
        User u2 = new User("lisi",25);  
        valueOper.set("u:u1", u1);  
        valueOper.set("u:u2", u2);  
        System.out.println(valueOper.get("u:u1").getName());  
        System.out.println(valueOper.get("u:u2").getName()); 
        System.out.println(valueOper.get("u:u1").getAge());*/
    	RedisCacheUtil<User> redisCache = new RedisCacheUtil<User>();
    	User u1 = new User("zhangsan",12);  
        User u2 = new User("lisi",25);  
         
        ValueOperations<String,User> test  = redisCache.setCacheObject("u1",u1);
    	test = redisCache.setCacheObject("u2",u2);
    	
    	System.out.println("u1 :" + ((User) redisCache.getCacheObject("u1")).getAge() +"," + ((User) redisCache.getCacheObject("u1")).getName());
    	System.out.println("u2 :" + ((User) redisCache.getCacheObject("u2")).getAge() +"," + ((User) redisCache.getCacheObject("u1")).getName());
    	
    	
    	RedisCacheUtil<String> redisCacheStr = new RedisCacheUtil<String>();
    	
    	ValueOperations<String,String> aa ;
    	
    	aa = redisCacheStr.setCacheObject("uu1","uu1");
    	
    	System.out.println(redisCacheStr.getCacheObject("uu1")  instanceof String);
    	System.out.println("str 测试:" +  redisCacheStr.getCacheObject("uu1"));
    	
    	
    	System.out.println("------------TestList--------------");
    	List<User> userList = new ArrayList<User>();
    	User userList1 = new User("xiaoming",11);
    	User userList2 = new User("小溪",22);
    	
    	userList.add(userList1);
    	userList.add(userList2);
    	
    	ListOperations<String,User> listOperations = redisCache.setCacheList("userList",userList);
    	
    	List<User> getUserList = redisCache.getCacheList("userList");
    	for(int i = 0 ; i < getUserList.size() ; i ++)
    	{
    		System.out.println(getUserList.get(i));
    	}
    
    	System.out.println("----------------\n");
    	
    	System.out.println("-------------------TestSet----------");
    	Set<User> set = new HashSet<User>();
    	User userList3 = new User("xiaomi1111ng",11);
    	User userList42 = new User("小溪11",22);
    	set.add(userList3);
    	set.add(userList42);
    	RedisCacheUtil<User> util = new RedisCacheUtil<User>();
    	BoundSetOperations op = util.setCacheSet("myset",set);
    	Set<User> getSet = util.getCacheSet("myset");
    	for(User u: getSet)
    	{
    		System.out.println(u);
    	}
    	
    	
    	System.out.println("\n-------------------------测试获得Map-----------");
    	Map<String,User> map  = new HashMap<String,User>();
    	
    	User userMap1 = new User("换换",11);
    	User userMap2 = new User("洗洗",22);
    	
    	map.put("userMap1",userMap1);
    	map.put("userMap2",userMap2);
    	RedisCacheUtil<User> mapUtil = new RedisCacheUtil<User>();
    	HashOperations<String,String,User> hashOperation = mapUtil.setCacheMap("mapCache",map);
    	Map<String,User> getmap =  mapUtil.getCacheMap("mapCache");
    	for(Map.Entry<String,User> entry : getmap.entrySet())
    	{
    		System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
    	}
    	
    	
    }  
      
/*    *//** 
     * 如果使用jdk序列化方式，bean必须实现Serializable，且提供getter/setter方法 
     * @author qing 
     * 
     *//*  
    static class User implements Serializable{  
          
        *//** 
         *  
         *//*  
        private static final long serialVersionUID = -3766780183428993793L;  
        private String name;  
        private Date created;  
        private int age;  
        public User(){}  
        public User(String name,int age){  
            this.name = name;  
            this.age = age;  
            this.created = new Date();  
        }  
        public String getName() {  
            return name;  
        }  
        public void setName(String name) {  
            this.name = name;  
        }  
        public Date getCreated() {  
            return created;  
        }  
        public void setCreated(Date created) {  
            this.created = created;  
        }  
        public int getAge() {  
            return age;  
        }  
        public void setAge(int age) {  
            this.age = age;  
        }  
          
    }  */
  
}  
