package com.jthink.appearance.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.constants.Taxonomy;
import com.jthink.cms.entity.Jlink;
import com.jthink.cms.entity.Term;
import com.jthink.cms.entity.TermRelationship;
import com.jthink.cms.service.JlinkService;
import com.jthink.cms.service.TermRelationshipService;
import com.jthink.cms.service.TermService;
import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/system/appearance/linkcategory")
public class LinkCategoryController extends BaseController {
	@Autowired
	private TermService termsService;
	@Autowired
	private JlinkService jlinkService;
	@Autowired
	private TermRelationshipService termRelationshipService;
	@RequestMapping({ "", "/index" })
	public String categoryIndex() {
		return "cms/linkcategory/linkcategory";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object>  getAllTerms(QueryRequest request,String termName) {
		Example example = new Example(Term.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("taxonomy", Taxonomy.linkType);
		if (StringUtils.isNotBlank(termName)) {
			criteria.andEqualTo("name", termName);
		}
		Map<String, Object> termMap = this.selectByPageNumSize(request,
				() -> termsService.selectByExample(example));
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
	@SaCheckPermission(value = {"linkcat:add","superadmin"},mode=SaMode.OR)
	public ResponseBo addCategory(Term term) {
		term.setCreateTime(new Date());
		term.setTaxonomy(Taxonomy.linkType);
		termsService.save(term);
		return ResponseBo.ok("新增分类成功");
	}

	@PostMapping("/update")
	@ResponseBody
	@SaCheckPermission(value = {"linkcat:update","superadmin"},mode=SaMode.OR)
	public ResponseBo updateCategory(Term term) {
		term.setUpdateTime(new Date());
		termsService.updateNotNull(term);
		return ResponseBo.ok("更新分类成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	@SaCheckPermission(value = {"linkcat:delete","superadmin"},mode=SaMode.OR)
	public ResponseBo deleteCategory(String termIds) {
		List<String> ids = Arrays.asList(termIds.split(","));
		termsService.batchDelete(ids, "id", Term.class);
		Example relationExample = new Example(TermRelationship.class);
		relationExample.createCriteria().andIn("termId", ids);
		List<TermRelationship> relations = termRelationshipService.selectByExample(relationExample);
		List<Integer> linkIds = relations.stream().map(TermRelationship::getObjectId).collect(Collectors.toList());
		Example example = new Example(Jlink.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", linkIds);
		jlinkService.deleteByExample(example);
		termRelationshipService.deleteByExample(relationExample);
		return ResponseBo.ok();
	}
}
