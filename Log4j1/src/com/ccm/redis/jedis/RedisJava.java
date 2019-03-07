package com.ccm.redis.jedis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisJava {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	  //	redis连接
	  /*//Connecting to Redis server on localhost 
      Jedis jedis = new Jedis("localhost"); 
      System.out.println("Connection to server sucessfully"); 
      //check whether server is running or not 
      System.out.println("Server is running: "+jedis.ping());*/
      
      
      //	Redis Java字符串示例
     /* //Connecting to Redis server on localhost 
      Jedis jedis = new Jedis("localhost"); 
      System.out.println("Connection to server sucessfully"); 
      //set the data in redis string 
      jedis.set("tutorial-name", "Redis tutorial"); 
      // Get the stored data and print it 
      System.out.println("Stored string in redis:: "+ jedis.get("tutorial-name")); */
		
		
		// 	reids集合示例
		//Connecting to Redis server on localhost 
	      Jedis jedis = new Jedis("localhost"); 
	      System.out.println("Connection to server sucessfully"); 
	      //store data in redis list 
	      jedis.lpush("tutorial-list", "Redis"); 
	      jedis.lpush("tutorial-list", "Mongodb"); 
	      jedis.lpush("tutorial-list", "Mysql"); 
	      // Get the stored data and print it 
	      List<String> list = jedis.lrange("tutorial-list", 0 ,5); 
	      for(int i = 0; i<list.size(); i++) { 
	         System.out.println("Stored string in redis:: "+list.get(i)); 
	      } 
		
		//	 	reids遍历键示例
	  /*//Connecting to Redis server on localhost 
      Jedis jedis = new Jedis("localhost"); 
      System.out.println("Connection to server sucessfully"); 
      //store data in redis list 
      // Get the stored data and print it 
      Set<String> list = jedis.keys("*"); 
      System.out.println(list.toString());*/
	}

}
