package com.jthink.shop.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.member.entity.Member;
import com.jthink.shop.mapper.MemberMapper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MemberService extends BaseService<Member> {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberMapper memberMapper;

	public Member findByMemberBynameOrEmail(String username, String email) {
		Example example = new Example(Member.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("username", username);
		criteria.orEqualTo("email", email);
		Member member = memberMapper.selectOneByExample(example);
		return member;
	}

	public List<Member> getMemberList(Member member) {
		Example example = new Example(Member.class);
		example.createCriteria().andEqualTo("username", member.getUsername()).andEqualTo("email", member.getEmail())
				.andEqualTo("phone", member.getPhone());
		return memberMapper.selectByExample(example);
	}

	public Long getMemberIdByName(String username) {
		return memberMapper.getMemberIdByName(username);
	}

	public boolean addMember(Member member) {
		int count = this.memberMapper.insertUseGeneratedKeys(member);
		log.warn("更新用户角色为空");
		return count > 0;
	}

	public boolean updateMember(Member member) {
		int count = this.updateNotNull(member);
		return count > 0;
	}

	public int deleteMembersByIds(String memberIds) {
		List<String> ids = Arrays.asList(memberIds.split(","));
		return this.batchDelete(ids, "id", Member.class);
	}
}
