package com.jthink.controller;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jthink.cms.entity.JthinkImage;
import com.jthink.cms.service.JthinkImageService;
import com.jthink.common.service.FileManager;

import cn.dev33.satoken.annotation.SaCheckLogin;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
	@Autowired
	private JthinkImageService jthinkImageService;
	private final FileManager fileManager;

	FileUploadController(FileManager fileManager) {
		this.fileManager = fileManager;
	}
    @SaCheckLogin
	@PostMapping("/image")
	public JSONObject image(@RequestParam(name = "upload") CommonsMultipartFile file,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		if (file == null || file.isEmpty()) {
			json.put("uploaded", false);
			json.put("url", "");
			json.put("info", "上传失败");
			return json;
		}
		if (StringUtils.isBlank(file.getOriginalFilename()) || !isAllow(file.getOriginalFilename())) {
			json.put("uploaded", false);
			json.put("url", "");
			json.put("info", "上传失败");
			return json;
		}
		String url="";
		try {
			url = fileManager.upload(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (StringUtils.isBlank(url)) {
			json.put("uploaded", false);
			json.put("url", "");
			json.put("info", "上传失败");
			return json;
		} else {
			json.put("uploaded", true);
			json.put("url", url);
			json.put("info", "上传成功");
			return json;
		}
	}
    @SaCheckLogin
	@PostMapping("/postImage")
	public JSONObject postImage(@RequestParam(name = "file") CommonsMultipartFile file,String name,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		if (file == null || file.isEmpty()) {
			json.put("class", "failure");
			json.put("picurl", "");
			json.put("info", "上传失败");
			return json;
		}
		if (StringUtils.isBlank(file.getOriginalFilename()) || !isAllow(file.getOriginalFilename())) {
			json.put("class", "failure");
			json.put("picurl", "");
			json.put("info", "上传失败");
			return json;
		}
		String url="";
		JthinkImage image=new JthinkImage();
		try {
			url = fileManager.upload(file);
			image.setName(name);
			image.setCreateTime(new Date());
			image.setSrc(url);
			jthinkImageService.save(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (StringUtils.isBlank(url)) {
			json.put("class", "failure");
			json.put("picurl", "");
			json.put("info", "上传失败");
			return json;
		} else {
			json.put("class", "success");
			json.put("picurl", url);
			json.put("id", image.getId());
			json.put("info", "上传成功");
			return json;
		}
	}
	private boolean isAllow(String fileName) {
		String[] allowFiles = { ".gif", ".png", ".jpg", ".jpeg", ".bpm", ".svg" };
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		List<String> suffixList = Arrays.stream(allowFiles).collect(Collectors.toList());
		return suffixList.contains(suffix);
	}
}
