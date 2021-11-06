package com.jthink.shop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.constants.Taxonomy;
import com.jthink.cms.entity.Term;
import com.jthink.cms.entity.JthinkTree;
import com.jthink.cms.service.TermService;
import com.jthink.controller.BaseController;
import com.jthink.permission.utils.TreeUtils;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/system/shop/category")
public class ProductCategoryController extends BaseController {
	@Autowired
	private TermService termsService;

	@RequestMapping({ "", "/index" })
	public String categoryIndex() {
		return "shop/category/category";
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Term> getAllTerms(String termName) {
		Example example=new Example(Term.class);
		example.createCriteria().andEqualTo("taxonomy", Taxonomy.productCategory).andLike("name", termName);
		List<Term> terms = termsService.selectByExample(example);
		return terms;
	}

	@RequestMapping("/getCategory")
	@ResponseBody
	public ResponseBo getCategory(Integer termId) {
		Term term = termsService.selectByKey(termId);
		return ResponseBo.ok(term);
	}

	@PostMapping("/add")
	@ResponseBody
	@SaCheckPermission(value = {"category:add","superadmin"},mode=SaMode.OR)
	public ResponseBo addCategory(Term term) {
		if(StringUtils.isBlank(term.getSlug())||StringUtils.isBlank(term.getName())) {
			return ResponseBo.error("请输入分类和简称");
		}
		term.setCreateTime(new Date());
		term.setTaxonomy(Taxonomy.productCategory);
		termsService.save(term);
		return ResponseBo.ok("新增分类成功");
	}

	@PostMapping("/update")
	@ResponseBody
	@SaCheckPermission(value = {"category:update","superadmin"},mode=SaMode.OR)
	public ResponseBo updateCategory(Term term) {
		term.setUpdateTime(new Date());
		termsService.updateNotNull(term);
		return ResponseBo.ok("更新分类成功");
	}

	@RequestMapping("/treeList")
	@ResponseBody
	public ResponseBo getTermTreeList() {
		List<JthinkTree<Term>> tree = new ArrayList<>();
		Example example = new Example(Term.class);
		example.createCriteria().andEqualTo("taxonomy", Taxonomy.productCategory);
		List<Term> terms = termsService.selectByExample(example);
		if (null != terms) {
			buildTrees(tree, terms);
			JthinkTree<Term> termTree = TreeUtils.buildTree(tree);
			List<JthinkTree<Term>> nodes=new ArrayList<>();
			nodes.add(termTree);
			return ResponseBo.ok(nodes);
		}
		return ResponseBo.ok();
	}

	private void buildTrees(List<JthinkTree<Term>> tree, List<Term> terms) {
		terms.forEach(term -> {
			JthinkTree<Term> treeNode = new JthinkTree<>();
			treeNode.setTitle(term.getName());
			treeNode.setTaxonomy(term.getTaxonomy());
			Integer parentId = term.getParentId();
			treeNode.setId(term.getId().toString());
			if (null != parentId) {
				treeNode.setParentId(parentId.toString());
			}
			tree.add(treeNode);
		});
	}

	@RequestMapping("/delete")
	@ResponseBody
	@SaCheckPermission(value = {"category:delete","superadmin"},mode=SaMode.OR)  
	public ResponseBo deleteCategory(String termIds) {
		List<String> ids = Arrays.asList(termIds.split(","));
		termsService.batchDelete(ids, "id", Term.class);
		return ResponseBo.ok();
	}
}
