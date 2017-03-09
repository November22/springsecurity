package config;

import net.sf.ehcache.CacheManager;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;



@Configuration
@Import(MyBatisConfig.class)//将mybatisconfig类导入，然后注册到spring应用上下文中
public class RootConfig {
	@Bean
	public CacheManager cacheManager(){
		return new CacheManager();
	}
	
	
	/**
	 * 注册实现CacheManager的bean
	 * @param cacheManager
	 * @return
	 */
	@Bean
	public EhCacheCacheManager cacheCacheManager(CacheManager cacheManager){
		return new EhCacheCacheManager(cacheManager);
	}
}
