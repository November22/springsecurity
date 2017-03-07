package utils;

import org.springframework.security.crypto.password.PasswordEncoder;


public class MyPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return null;
	}
	
	//在SecurityConfig中使用这个方法的时候，会自己把用户输入的密码和从数据库中查找出来的密码传入
	/**
	 * rawPassword:用户输入的密码
	 * encodedPassword：数据库的密码
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		String checkPwd = rawPassword.toString()+"6";
		return encodedPassword.equals(checkPwd);
	}

}
