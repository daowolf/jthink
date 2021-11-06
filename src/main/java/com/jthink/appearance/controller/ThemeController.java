package com.jthink.appearance.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.common.entity.Joption;
import com.jthink.common.service.JoptionService;
import com.jthink.controller.BaseController;
import com.jthink.directive.ThymeleafVariUtil;
import com.jthink.utils.PropertiesUtil;
import com.jthink.utils.ResponseBo;

import tk.mybatis.mapper.entity.Example;
@Controller
@RequestMapping("/system/appearance")
public class ThemeController extends BaseController {
	@Autowired
	private JoptionService joptionService;
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;
	/**
	 * 模板主题列表
	 */
	@RequestMapping("/themeList")
	public String themeList(Model ui) {
		try {
			String path = ResourceUtils.getURL("classpath:").getPath();
			String cmsPath = path + "templates/blog";
			File templatePath = new File(cmsPath);
			File[] files = templatePath.listFiles();
			List<Properties> templates = new ArrayList<>();
			for (File themFolder : files) {
				String propes = "templates/blog/" + themFolder.getName() + "/template.properties";
				PropertiesUtil prop = new PropertiesUtil(propes);
				templates.add(prop.getProperties());
			}
			String theme = joptionService.getOptionValue("site_theme", 0);
			ui.addAttribute("templates", templates);
			ui.addAttribute("theme", theme);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "theme/list";
	}

	/***
	 * 更新模板
	 * 
	 * @param themeId 要启用的模板的id
	 */
	@RequestMapping("/theme/update")
	@ResponseBody
	public ResponseBo updateTheme(String themeId, HttpServletRequest request, HttpServletResponse response) {
		Joption option = new Joption();
		option.setBlogId(0);
		option.setOptionName("site_theme");
		option.setOptionValue(themeId);
		Example example = new Example(Joption.class);
		example.createCriteria().andEqualTo("blogId", 0).andEqualTo("optionName", option.getOptionName());
		joptionService.updateNotNullByExample(option, example);
		// 刷新全局配置
		thymeleafVariUtil.configViewVar();
		return ResponseBo.ok();
	}
}
