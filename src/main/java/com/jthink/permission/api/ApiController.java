package com.jthink.permission.api;

import com.jthink.permission.dto.UserDTO;
import com.jthink.permission.service.UserService;
import com.jthink.utils.ResponseBo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * api登录入口，控制移动端权限校验
 */
@RestController
@RequestMapping("/authapi")
public class ApiController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "api接口登录", notes = "登录成功返回token")
	@ApiImplicitParam(name = "user", value = "登录用户实体", required = true, dataType = "UserDTO")
	@PostMapping("/authration")
	public ResponseBo login(@RequestBody UserDTO user) {
		return ResponseBo.ok();
	}

	@GetMapping("/data")
	public String data() {
		return "This is data.";
	}
}