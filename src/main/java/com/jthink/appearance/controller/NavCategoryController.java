package com.jthink.appearance.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.constants.Taxonomy;
import com.jthink.cms.entity.Term;
import com.jthink.cms.service.TermService;
import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/system/appearance/navcategory")
public class NavCategoryController extends BaseController {
	@Autowired
	private TermService termsService;

	@RequestMapping({ "", "/index" })
	public String categoryIndex() {
		return "cms/navcategory/navcategory";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> getAllTerms(QueryRequest request, String termName) {
		Example example = new Example(Term.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("taxonomy", Taxonomy.navMenuType);
		if (StringUtils.isNotBlank(termName)) {
			criteria.andEqualTo("name", termName);
		}
		Map<String, Object> termMap = this.selectByPageNumSize(request, () -> termsService.selectByExample(example));
		return termMap;
	}

	@RequestMapping("/getCategory")
	@ResponseBody
	public ResponseBo getCategory(Integer termId) {
		Term term = termsService.selectByKey(termId);
		return ResponseBo.ok(term);
	}

	@PostMapping("/add")
	@ResponseBody
	@SaCheckPermission(value = {"navcat:add","superadmin"},mode=SaMode.OR)
	public ResponseBo addCategory(Term term) {
		term.setCreateTime(new Date());
		term.setTaxonomy(Taxonomy.navMenuType);
		termsService.save(term);
		return ResponseBo.ok("新增导航成功");
	}

	@PostMapping("/update")
	@ResponseBody
	@SaCheckPermission(value = {"navcat:update","superadmin"},mode=SaMode.OR)
	public ResponseBo updateCategory(Term term) {
		term.setUpdateTime(new Date());
		termsService.updateNotNull(term);
		return ResponseBo.ok("更新导航成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	@SaCheckPermission(value = {"navcat:delete","superadmin"},mode=SaMode.OR)
	public ResponseBo deleteCategory(String termIds) {
		List<String> ids = Arrays.asList(termIds.split(","));
		termsService.batchDelete(ids, "id", Term.class);
		return ResponseBo.ok();
	}
}
