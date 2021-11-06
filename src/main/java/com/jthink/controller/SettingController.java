package com.jthink.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.common.entity.Joption;
import com.jthink.common.service.JoptionService;
import com.jthink.directive.ThymeleafVariUtil;
import com.jthink.utils.ResponseBo;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/system/setting")
public class SettingController {
	@Autowired
	private JoptionService joptionService;
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;

	@RequestMapping({ "", "/index" })
	public String index(Model ui) {
		String path = "";
		try {
			path = ResourceUtils.getURL("classpath:").getPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String cmsPath = path + "templates/blog";
		File templatePath = new File(cmsPath);
		String[] list = templatePath.list();
		ui.addAttribute("templates", list);
		return "setting/setting";
	}

	@RequestMapping("/options")
	@ResponseBody
	public ResponseBo settingList() {
		Example example = new Example(Joption.class);
		example.createCriteria().andEqualTo("blogId", 0);
		List<Joption> options = joptionService.selectByExample(example);
		return ResponseBo.ok(options);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResponseBo updateSetting(@RequestBody List<Joption> options) {
		for (Joption option : options) {
			Example example = new Example(Joption.class);
			example.createCriteria().andEqualTo("blogId", 0).andEqualTo("optionName", option.getOptionName());
			joptionService.updateNotNullByExample(option, example);
		}
		// 刷新全局配置
		thymeleafVariUtil.configViewVar();
		return ResponseBo.ok();
	}
}
