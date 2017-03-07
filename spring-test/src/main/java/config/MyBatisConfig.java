package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSourceFactory;

@Configuration
@MapperScan("mapper")  //扫描mapper包
public class MyBatisConfig {

	/**
	 * 创建数据源
	 * @throws Exception 
	 */
	@Bean
	public DataSource dataSource() throws Exception{
		Properties props = new Properties();
		props.put("driverClassName", "com.mysql.jdbc.Driver");
		props.put("url", "jdbc:mysql://localhost:3306/user_role?useUnicode=true&characterEncoding=utf8");
		props.put("username", "root");
		props.put("password", "123456");
		return DruidDataSourceFactory.createDataSource(props);
	}
	
	/**
	 * 根据数据源创建SqlSessionFactory
	 * @param ds
	 * @return
	 * @throws Exception 
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);
		
		return fb.getObject();
	}
}