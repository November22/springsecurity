package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * 
 * @author 黄森 
 * @date : 2017年3月7日 上午9:31:06
 *	Security的安全配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * 重载，配置user-details服务
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		//配置基于内存的用户认证
		/*auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER").and()
				.withUser("admin").password("admin").roles("USER","ADMIN");*/
		//必须是三个字段，最后一个为用户的状态，如果没有改column。直接写true，不能空着
		String usersByUsernameQuery = "select user.u_name username , `user`.u_password password , true from user where `user`.u_name = ?";
		String authorities = "SELECT user.u_name username ,role.r_name ROLE_USER "
				+ "FROM `user_role`, USER, role "
				+ "WHERE USER .u_name = ? AND `user`.id = user_role.user_id and user_role.role_id = role.id";
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
				.usersByUsernameQuery(usersByUsernameQuery)
				.authoritiesByUsernameQuery(authorities);
	}
	
	/**
	 * 重载，配置如何通过拦截器保护请求
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//如果什么配置都没有，默认是如下配置,就是super.configure(http);
		http
			.authorizeRequests()
				.anyRequest().authenticated()  //要求所有进入应用http请求都要进行认证
				.and()
			.formLogin().and()  //基于表单登录
			.httpBasic();    //HTTP Basic方式认证
		
	}
	
	/**
	 * 重载，配置Spring Security的Filter链
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
}
