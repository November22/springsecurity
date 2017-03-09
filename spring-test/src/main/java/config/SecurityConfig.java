package config;

import javax.sql.DataSource;

import mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import utils.MyPasswordEncoder;
import utils.MyUserDetailsService;
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
	
	@Autowired
	private UserMapper mapper;
	
	/**
	 * 重载，配置user-details服务
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		//###########配置基于内存的用户认证##############
		/*auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER").and()
				.withUser("admin").password("admin").roles("USER","ADMIN");*/
		
		//###################基于数据库认证用户######################
		//必须是三个字段，最后一个为用户的状态，如果没有改column。直接写true，不能空着
		/*String usersByUsernameQuery = "select user.u_name username , `user`.u_password password , true from user where `user`.u_name = ?";
		String authorities = "SELECT user.u_name username ,role.r_name ROLE_USER "
				+ "FROM `user_role`, USER, role "
				+ "WHERE USER .u_name = ? AND `user`.id = user_role.user_id and user_role.role_id = role.id";
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
				.usersByUsernameQuery(usersByUsernameQuery)
				.authoritiesByUsernameQuery(authorities)
				.passwordEncoder(new MyPasswordEncoder());*/
		
		//####################自定义UserDetails###################
		//可以使用.passwordEncoder(new MyPasswordEncoder())方法来完成自己的密码认证
		auth
			.userDetailsService(new MyUserDetailsService(mapper));
	}
	
	/**
	 * 重载，配置如何通过拦截器保护请求
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//#########对所有请求拦截##########
		//如果什么配置都没有，默认是如下配置,就是super.configure(http);
		/*http
			.authorizeRequests()
				.anyRequest().authenticated()  //要求所有进入应用http请求都要进行认证
				.and()
			.formLogin().and()  //基于表单登录
			.httpBasic(); */   //HTTP Basic方式认证,就是用户名密码认证
		
		//###########一些简单的路径拦截配置#############
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
//				.antMatchers("/view").hasAuthority("wahaha")//需要制定的权限才能访问
//				.antMatchers("/view").hasRole("iths") //这里不用 ROLE_ 作为开头，但是数据库中存储的数据必须以 ROLE_
				.antMatchers("/toLogin").permitAll()
				.antMatchers("/view").hasRole("iths")
				.anyRequest().authenticated()
					.and()
				.formLogin().loginPage("/toLogin").loginProcessingUrl("/login")
					.and()
				.logout()
				.logoutSuccessUrl("/")
				.logoutUrl("/signout")
					.and()
				.httpBasic()
					.and()
				.rememberMe() //启用remember-me
				.tokenValiditySeconds(2419200)//cookie过期时间
				.key("securityKey");
		
		/*
		 * remember-me：存储在cookie中token包含用户名、密码、过期时间和一个私钥，
		 * 私钥默认是SpringSecured，自定义为securityKey
		 * 在前台添加remember-me的label标签
		 * 
		 * */
	}
	
	/**
	 * 重载，配置Spring Security的Filter链
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}
