package com.jthink.permission.mapper;

import com.jthink.common.config.TkMapper;
import com.jthink.permission.entity.AuthPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AuthPermissionMapper extends TkMapper<AuthPermission> {
	@Results(id = "permsMap", value = { @Result(property = "id", column = "id"),
			@Result(property = "pid", column = "pid"), @Result(property = "name", column = "name"),
			@Result(property = "url", column = "url"), @Result(property = "perms", column = "perms"),
			@Result(property = "icon", column = "icon"), @Result(property = "orderNum", column = "order_num"),
			@Result(property = "createTime", column = "create_time"),
			@Result(property = "updateTime", column = "update_time") })

	@Select("SELECT ap.* FROM jk_permissions ap left join jk_role_permission rp on ap.id=rp.permission_id where rp.role_id=#{roleId} order by ap.order_num desc")
	public List<AuthPermission> getRolePermissions(Integer roleId);

	@SelectProvider(type = AuthPermissionProvider.class, method = "getUnionPermission")
	@ResultMap("permsMap")
	public List<AuthPermission> getUnionPermission(@Param("roleIds") List<Integer> roleIds);

	@UpdateProvider(type = AuthPermissionProvider.class, method = "changeToTop")
	public void changeToTop(@Param("permIds") List<String> permIds);

	/**
	 * 按类型查询 0菜单 1按钮
	 */
	@SelectProvider(type = AuthPermissionProvider.class, method = "getUnionPermissionByType")
	@ResultMap("permsMap")
	public List<AuthPermission> getUnionPermissionByType(@Param("roleIds") List<Integer> roleIds,
			@Param("type") String type);

	@SelectProvider(type = AuthPermissionProvider.class, method = "getAllUnionPermissionByType")
	@ResultMap("permsMap")
	public List<AuthPermission> getAllUnionPermissionByType(@Param("type") String type);

	class AuthPermissionProvider {
		public static String changeToTop(final List<Integer> permIds) {
			StringBuilder sql = new StringBuilder(128);
			// 注意sql外面要用<script></script>包起来
			sql.append("<script>update jk_permissions set pid = 0 where pid  in <foreach item='item' collection='permIds' open='(' separator=',' close=')'>#{item}</foreach></script>");
			return sql.toString();
		}

		public static String getUnionPermission(final List<Integer> roleIds) {
			StringBuilder sql = new StringBuilder(128);
			// 注意sql外面要用<script></script>包起来
			sql.append(
					"<script>SELECT distinct ap.* FROM jk_permissions ap left join jk_role_permission rp on ap.id=rp.permission_id where 1=1 ");
			sql.append(
					"and rp.role_id in <foreach item='item' collection='roleIds' open='(' separator=',' close=')'>#{item}</foreach> ");
			sql.append(" order by ap.order_num desc");
			sql.append("</script>");
			return sql.toString();
		}

		public static String getUnionPermissionByType(final List<Integer> roleIds, final String type) {
			StringBuilder sql = new StringBuilder(128);
			// 注意sql外面要用<script></script>包起来
			sql.append(
					"<script>SELECT distinct ap.* FROM jk_permissions ap left join jk_role_permission rp on ap.id=rp.permission_id where is_show=1 and ap.type &lt;= #{type}");
			sql.append(
					" and rp.role_id in <foreach item='item' collection='roleIds' open='(' separator=',' close=')'>#{item}</foreach> ");
			sql.append(" order by ap.order_num desc");
			sql.append("</script>");
			return sql.toString();
		}

		public static String getAllUnionPermissionByType(final String type) {
			StringBuilder sql = new StringBuilder(128);
			// 注意sql外面要用<script></script>包起来
			sql.append(
					"<script>SELECT distinct ap.* FROM jk_permissions ap left join jk_role_permission rp on ap.id=rp.permission_id where is_show=1 and ap.type &lt;= #{type}");
			sql.append(" order by ap.order_num desc");
			sql.append("</script>");
			return sql.toString();
		}
	}
}
