package com.jthink.permission.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jthink.JthinkApplication;
import com.jthink.permission.entity.JthinkUser;
import com.jthink.permission.service.UserService;
import com.jthink.security.util.LoginUtil;

@SpringBootTest(classes = JthinkApplication.class)
public class UserTest {
	@Autowired
	private UserService userService;
	//@Test
	public void queryUserByName() {
		JthinkUser user = userService.findByUsernameOrEmail("admin","admin@123.com");
		System.out.println("查找用户:"+user.getUsername());
		
	}
	//@Test
	public void testGetToken() {
		System.out.println("这们用用户token="+"token");
	}
	@Test
	public void testPassword() {
		String pwd = LoginUtil.encodePwd("123456");
		System.out.println("密码:"+pwd);
	}

}
