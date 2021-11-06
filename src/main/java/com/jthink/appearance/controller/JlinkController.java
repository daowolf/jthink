package com.jthink.appearance.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.entity.Jlink;
import com.jthink.cms.entity.Term;
import com.jthink.cms.entity.TermRelationship;
import com.jthink.cms.service.JlinkService;
import com.jthink.cms.service.TermRelationshipService;
import com.jthink.cms.service.TermService;
import com.jthink.controller.BaseController;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/system/appearance/jlink")
public class JlinkController extends BaseController {
	@Autowired
	private JlinkService jlinkService;
	@Autowired
	private TermRelationshipService termRelationshipService;
	@Autowired
	private TermService termService;

	@RequestMapping({ "", "/index" })
	public String linkIndex(String termId, Model model) {
		Term term = termService.selectByKey(termId);
		model.addAttribute("term", term);
		return "cms/jlink/jlink";
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Jlink> postList(Jlink jlink, Integer termId) {
		Example relationExample = new Example(TermRelationship.class);
		relationExample.createCriteria().andEqualTo("termId", termId);
		List<TermRelationship> relations = termRelationshipService.selectByExample(relationExample);
		List<Integer> linkIds = relations.stream().map(TermRelationship::getObjectId).collect(Collectors.toList());
		Example example = new Example(Jlink.class);
		Criteria criteria = example.createCriteria();
		if (null != linkIds && linkIds.size() > 0) {
			criteria.andIn("id", linkIds);
		} else {
			return null;
		}

		if (StringUtils.isNotBlank(jlink.getLinkName())) {
			criteria.andLike("linkName", jlink.getLinkName());
		}
		List<Jlink> lists = jlinkService.selectByExample(example);
		return lists;
	}

	@RequestMapping("/getLinkById")
	@ResponseBody
	public ResponseBo getLinkById(Integer jlinkId) {
		Jlink jlink = jlinkService.selectByKey(jlinkId);
		return ResponseBo.ok(jlink);
	}

	@RequestMapping("/add")
	@ResponseBody
	@SaCheckPermission(value = {"link:add","superadmin"},mode=SaMode.OR)
	public ResponseBo addLink(Jlink jlink, Integer termId, Integer termOrder) {
		jlink.setCreateTime(new Date());
		jlinkService.save(jlink);

		// 保存关联关系
		TermRelationship termRelationship = new TermRelationship();
		termRelationship.setObjectId(jlink.getId());
		termRelationship.setTermId(termId);
		termRelationship.setTermOrder(termOrder);

		termRelationshipService.save(termRelationship);

		return ResponseBo.ok();
	}

	@RequestMapping("/update")
	@ResponseBody
	@SaCheckPermission(value = {"link:update","superadmin"},mode=SaMode.OR)
	public ResponseBo updateLink(Jlink jlink, Integer taxonomyId, Integer termOrder) {
		jlink.setUpdateTime(new Date());
		jlinkService.updateNotNull(jlink);
		return ResponseBo.ok();
	}

	@RequestMapping("/delete")
	@ResponseBody
	@SaCheckPermission(value = {"link:delete","superadmin"},mode=SaMode.OR)
	public ResponseBo deleteLinks(String linkIds) {
		List<String> ids = Arrays.asList(linkIds.split(","));
		jlinkService.batchDelete(ids, "id", Jlink.class);
		return ResponseBo.ok();
	}
}
