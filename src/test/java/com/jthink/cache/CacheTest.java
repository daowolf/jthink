package com.jthink.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jthink.JthinkApplication;
import com.jthink.common.config.cache.JthinkCache;

@SpringBootTest(classes = JthinkApplication.class)
public class CacheTest {
	@Autowired
	private JthinkCache jthinkCache;
    @Test
	public void testExpireTime() throws InterruptedException {
      jthinkCache.set("key","wangtao",3);
      System.out.println("key1:"+jthinkCache.getKey("key"));
      Thread.sleep(5000);
      System.out.println("key1:"+jthinkCache.getKey("key"));
	}
}
