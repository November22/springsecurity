package utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 创建属于自己的用户信息存储对象，不使用实体类的对象，方便解耦
 * @author iths
 *
 */

public class MyUserDetails implements UserDetails{
	
	private static final long serialVersionUID = -5896459318065548072L;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	private String uid;
	
	public MyUserDetails() {}
	
	public MyUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities, String uid) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.setUid(uid);
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
