package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MyBatisConfig.class)//将mybatisconfig类导入，然后注册到spring应用上下文中
public class RootConfig {

}
