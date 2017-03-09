package utils;

import java.util.ArrayList;
import java.util.List;

import mapper.UserMapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import domain.Role;
import domain.User;

/**
 * 
 * @author 黄森 
 * @date : 2017年3月7日 下午3:16:01
 *
 * 定义UserDetailsService
 */
public class MyUserDetailsService implements UserDetailsService{
	
	//没有在spring的应用上下文注册，不能使用@AutoWired
	private UserMapper mapper;
	public MyUserDetailsService(UserMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		MyUserDetails userDetails = null ;
		
		User user = mapper.findUserByUsername(username);
		
		if(user != null && !StringUtils.isEmpty(user.getId())){
			List<Role> roles = mapper.queryRoleByUid(user.getId());
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			for (Role role : roles) {
				list.add(new SimpleGrantedAuthority(role.getrName()));
			}
			
			userDetails = new MyUserDetails(user.getuName(), user.getuPassword(), list, user.getId());
			return userDetails;
		}
		
		throw new UsernameNotFoundException(" User: "+username+" not found ");
	}
}
