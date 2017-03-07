package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import domain.Role;
import domain.User;

public interface UserMapper {
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	@Select("select u.id id , u.u_name uName ,u.u_password uPassword from user u where u.u_name = #{username} ")
	public User findUserByUsername(@Param("username")String username);
	
	/**
	 * 根据用户的ID查找对应的角色
	 * @param user_id
	 * @return
	 */
	@Select("select r.id id , r.r_name rName from user_role ur , role r where ur.role_id = r.id and ur.user_id = #{uid}")
	public List<Role> queryRoleByUid(@Param("uid")String user_id);
}
