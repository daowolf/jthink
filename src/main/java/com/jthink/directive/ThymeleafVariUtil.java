package com.jthink.directive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.jthink.common.entity.Joption;
import com.jthink.common.service.JoptionService;

import tk.mybatis.mapper.entity.Example;

@Component
public class ThymeleafVariUtil {
	@Resource(name = "thymeleafViewResolver")
	private ThymeleafViewResolver thymeleafViewResolver;
	@Autowired
	private JoptionService joptionService;

	public void configViewVar() {
		Example example = new Example(Joption.class);
		example.createCriteria().andEqualTo("blogId", 0);
		List<Joption> options = joptionService.selectByExample(example);
		Map<String, Object> vars = new HashMap<>();
		for (Joption option : options) {
			vars.put(option.getOptionName(), option.getOptionValue());
		}
		thymeleafViewResolver.setStaticVariables(vars);
	}
	public String getSysVar(String key) {
		Object sysValue=thymeleafViewResolver.getStaticVariables().get(key);
		if(null!=sysValue) {
			return sysValue.toString();
		}
		return null;
	}
}
