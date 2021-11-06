package com.jthink.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer.";
	public static final long TTL = 2 * 60 * 60 * 1000;
	public static final int TTL_COOKIE = 2 * 60 * 60;
	private static final String SECRET_KEY = "https://www.jianshu.com/u/1b5928185b73";
	private static final String AUTHORITIES = "authorities";

	/**
	 * 生成 token
	 *
	 * @param username
	 * @return
	 */
	public static String generateToken(String username) {
		return generateToken(username, new ArrayList<>());
	}

	/**
	 * 生成 token
	 *
	 * @param username
	 * @param authorities
	 * @return
	 */
	public static String generateToken(String username, List<String> authorities) {
		return Jwts.builder().setSubject(username) // 主题
				.claim(AUTHORITIES, authorities).setIssuedAt(new Date()) // 发布时间
				.setExpiration(new Date(System.currentTimeMillis() + TTL)) // 到期时间
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 签名
				.compact();
	}

	/**
	 * 生成 token
	 *
	 * @param claims
	 * @return
	 */
	private static String generateToken(Claims claims) {
		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + TTL)) // 到期时间
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 签名
				.compact();
	}

	/**
	 * 解析 token
	 *
	 * @param token
	 * @return
	 */
	public static Claims parseToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	/**
	 * 获取 username
	 *
	 * @param token
	 * @return
	 */
	public static String getUsername(String token) {
		return parseToken(token).getSubject();
	}

	/**
	 * 获取 username
	 *
	 * @param claims
	 * @return
	 */
	public static String getUsername(Claims claims) {
		return claims.getSubject();
	}

	/**
	 * 是否过期
	 *
	 * @param token
	 * @return
	 */
	public static boolean isExpiration(String token) {
		return parseToken(token).getExpiration().before(new Date());
	}

	/**
	 * 是否过期
	 *
	 * @param claims
	 * @return
	 */
	public static boolean isExpiration(Claims claims) {
		return claims.getExpiration().before(new Date());
	}

	/**
	 * 获取角色
	 *
	 * @param token
	 * @return
	 */
	public static List<String> getAuthorities(String token) {
		return parseToken(token).get(AUTHORITIES, List.class);
	}

	/**
	 * 获取角色
	 *
	 * @param claims
	 * @return
	 */
	public static List<String> getAuthorities(Claims claims) {
		return claims.get(AUTHORITIES, List.class);
	}

	/**
	 * 刷新 token
	 *
	 * @param token
	 * @return
	 */
	public static String refreshToken(String token) {
		return generateToken(parseToken(token));
	}

	public static void main(String[] args) {
//        String token = generateToken("你好", new String[]{"PRO", "FREE", "ENT"});
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm8iLCJyb2xlcyI6WyJGUkVFIiwiUFJPIl0sImlhdCI6MTU5ODg2NTQ1MCwiZXhwIjoxNTk5NDcwMjUwfQ.mkxdZmc2Gm017e14rz2AXOVEnf7VF_e9xK6qUDiZ_sQ";
//        System.out.println(token);
//        System.out.println(isExpiration(token));
//        System.out.println(getUsername(token));
//        System.out.println(getAuthorities(token));

		token = TOKEN_PREFIX + token;
		System.out.println(token);
		System.out.println(TOKEN_PREFIX);
		System.out.println(TOKEN_PREFIX + token.substring(TOKEN_PREFIX.length()));

	}

}