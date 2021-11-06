package com.jthink.shop.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jthink.common.config.TkMapper;
import com.jthink.member.entity.Member;

public interface MemberMapper extends TkMapper<Member> {
	@Select("select id from jk_member where username =#{username}")
	public Long getMemberIdByName(String username);
	@Update("update jk_member set password=#{password} where id=#{userId}")
	public int updatePassword(String password,Long userId);

}
