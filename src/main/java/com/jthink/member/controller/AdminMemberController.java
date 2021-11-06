package com.jthink.member.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.member.entity.Member;
import com.jthink.security.util.LoginUtil;
import com.jthink.shop.service.MemberService;
import com.jthink.utils.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台会员管理控制器
 * 
 */
@Controller
@RequestMapping("/system/member")
public class AdminMemberController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberService memberService;
	private static final String ON = "on";

	@RequestMapping({ "", "/index" })
	public String index() {
		return "admin_member/member";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> roleList(QueryRequest request, Member member) {
		return super.selectByPageNumSize(request, () -> this.memberService.getMemberList(member));
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResponseBo add(Member member) {
		try {
			if (ON.equalsIgnoreCase(member.getStatus()))
				member.setStatus(Member.STATUS_VALID);
			else
				member.setStatus(Member.STATUS_LOCK);
			member.setPassword(LoginUtil.encodePwd(member.getPassword()));
			member.setCreateTime(new Date());
			this.memberService.addMember(member);

			return ResponseBo.ok("新增会员成功！");
		} catch (Exception e) {
			log.error("新增会员失败", e);
			return ResponseBo.error("新增会员失败，请联系网站管理员！");
		}
	}

	@SaCheckPermission(value = { "sys:user:add", "superadmin" }, mode = SaMode.OR)
	@RequestMapping("/update")
	@ResponseBody
	public ResponseBo update(Member member) {
		try {
			if (ON.equalsIgnoreCase(member.getStatus()))
				member.setStatus(Member.STATUS_VALID);
			else
				member.setStatus(Member.STATUS_LOCK);
			member.setPassword(null);
			member.setUsername(null);
			member.setUpdateTime(new Date());
			this.memberService.updateMember(member);
			return ResponseBo.ok("修改会员成功！");
		} catch (Exception e) {
			log.error("修改会员失败", e);
			return ResponseBo.error("修改会员失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo delete(String memberIds) {
		try {
			// 删除会员
			this.memberService.deleteMembersByIds(memberIds);
			return ResponseBo.ok("删除成功！");
		} catch (Exception e) {
			log.error("删除会员失败", e);
			return ResponseBo.error("删除失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/getMember")
	@ResponseBody
	public ResponseBo getMember(Long memberId) {
		try {
			Member member = this.memberService.selectByKey(memberId);
			Map<String, Object> data = new HashMap<String, Object>();
			member.setPassword(null);
			data.put("member", member);
			return ResponseBo.ok(data);
		} catch (Exception e) {
			log.error("获取会员失败", e);
			return ResponseBo.error("获取会员失败，请联系网站管理员！");
		}
	}
}
