package com.jthink.security.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jthink.common.config.cache.JthinkCache;
import com.jthink.security.code.ImageCode;
import com.jthink.security.code.ValidateCodeGenerator;
import com.jthink.security.util.JthinkConstant;

@RestController
public class ValidateCodeController {
	@Autowired
	private ValidateCodeGenerator imageCodeGenerator;
	@Autowired
	private JthinkCache jthinkCache;

	@GetMapping("/image/code")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = UUID.randomUUID().toString();
		ImageCode imageCode = (ImageCode) imageCodeGenerator.createCode();
		response.addHeader(JthinkConstant.KEY_IMAGE_CODE, uuid);
		// 一分钟有效期
		jthinkCache.set(uuid, imageCode.getCode(), 60);
		BufferedImage image = imageCode.getImage();
		imageCode.setImage(null);
		response.setContentType("image/jpeg");
		ImageIO.write(image, "jpeg", response.getOutputStream());
	}
}
