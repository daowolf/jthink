package com.jthink.cms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jthink.JthinkApplication;
@SpringBootTest(classes = JthinkApplication.class)
public class UrlTest {
	@Test
	public void testuRL() {
		String refer = "http://localhost:8081/category/21";
		String[] arrs = refer.split("/");
		for(String str:arrs) {
			System.out.println(str);	
		}
		
		
	}

}
