package com.ruanhang.springbootBasic.testRedis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.ruanhang.springbootBasic.entity.UserEntity;
import com.ruanhang.springbootBasic.enums.UserSexEnum;
import com.ruanhang.springbootBasic.service.RedisService;

/** 
 * @author  阮航  
 * @date 创建时间：2017年12月1日 下午2:49:08 
 * @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
//	private RedisService redisService;
	
	@Test
	public void testString(){
		 redisTemplate.opsForValue().set("ruanhang", "hello");
	        Assert.assertEquals("hello", redisTemplate.opsForValue().get("ruanhang"));
	    }
	
	//有问题，需要支持对象序列化
	@Test
	public void testPOJO(){
		UserEntity userEntity = new UserEntity("ruanhang", "123456", UserSexEnum.MAN);
		ValueOperations<String, UserEntity> operations=redisTemplate.opsForValue();
		operations.set("com.rh", userEntity);
		UserEntity userEntity2 = operations.get("com.rh");
		System.out.println("UserEntity2: " + userEntity2.toString());
	}
	
	//失效性处理自动删除缓存对象
	@Test
	public void testExpire() throws InterruptedException {
		UserEntity user=new UserEntity("wangshuai", "123456", UserSexEnum.MAN);
	    ValueOperations<String, UserEntity> operations=redisTemplate.opsForValue();
	    operations.set("expire", user,100,TimeUnit.MILLISECONDS);
	    Thread.sleep(1000);
	    boolean exists=redisTemplate.hasKey("expire");
	    if(exists){
	        System.out.println("exists is true");
	    }else{
	        System.out.println("exists is false");
	    }
	}
	
	//删除缓存
	@Test
	public void testDeleteKey(){
		ValueOperations<String, UserEntity> operations = redisTemplate.opsForValue();
		UserEntity userEntity = new UserEntity("wangdadiao", "1212", UserSexEnum.WOMAN);
		operations.set("deleteKey", userEntity);
	}
	
	//hash（哈希）
	@Test
	public void testHash() {
	    HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
	    hash.put("hash","you","you");
	    String value=(String) hash.get("hash","you");
	    System.out.println("hash value :"+value);
	}
	
	//list(应用场景消息队列)  贼多应用场景  王大神  求封装，求示例，求讲解  貌似变得有点慢了，内存开销有点大啊
	@Test
	public void testList() {
	    ListOperations<String, String> list = redisTemplate.opsForList();
	    list.leftPush("list","wang");
	    list.leftPush("list","da");
	    list.leftPush("list","shen");
	    String value=(String)list.leftPop("list");
	    System.out.println("list value :"+value.toString());
	    
	    List<String> values=list.range("list",0,2);
	    for (String v:values){
	        System.out.println("list range :"+v);
	    }
	}
	
	//set 判断某个成员是否在一个 set 集合内的重要接口   具有排除重复的功能 不是自动排序
	@Test
	public void testSet() {
	    String key="set";
	    SetOperations<String, String> set = redisTemplate.opsForSet();
	    set.add(key,"wang");
	    set.add(key,"da");
	    set.add(key,"da");
	    set.add(key,"shen");
	    Set<String> values=set.members(key);
	    for (String v:values){
	        System.out.println("set value :"+v);
	    }
	}
	
	//zset 自动排序，优先级score
	@Test
	public void testZset(){
	    String key="zset";
	    redisTemplate.delete(key);
	    ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
	    zset.add(key,"wang",1);
	    zset.add(key,"da",6);
	    zset.add(key,"shen",4);
	    zset.add(key,"Fly",3);

	    Set<String> zsets=zset.range(key,0,3);
	    for (String v:zsets){
	        System.out.println("zset value :"+v);
	    }

	    Set<String> zsetB=zset.rangeByScore(key,0,3);
	    for (String v:zsetB){
	        System.out.println("zsetB value :"+v);
	    }
	}
	
//	@Test
//	public void TestPOJO(){
//		UserEntity user=new UserEntity("wangshuai", "123456", UserSexEnum.MAN);
//		redisService.setObject("rh", user);
//		Object object = redisService.getObject("rh");
//		System.out.println(object.toString());
//	}
}
