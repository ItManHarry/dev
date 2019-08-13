package com.ch.sys.biz.system.configuration.mvc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.ch.sys.biz.system.configuration.inteceptors.LoggerInteceptor;
import com.ch.sys.biz.system.configuration.inteceptors.TokenInterceptor;
/**
 * System Intercepttors
 * @author 20112004
 *
 */
@Configuration
public class ServerMvcConfigration extends WebMvcConfigurationSupport {

	@Value("${system.image.path}")
	private String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		System.out.println("Image path 2222 : " + imagePath);
		//拦截器是按照顺序来执行的,先执行前面的拦截器
		
		/**
		 * 系统登录拦截器
		 */
		registry.addInterceptor(new TokenInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/system/register","/system/toLogin","/system/login")
			;
		/**
		 * 系统日志拦截器
		 */
		registry.addInterceptor(new LoggerInteceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/system/register","/system/toLogin");
		super.addInterceptors(registry);
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		 //将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版 
        registry.addResourceHandler("/templates/**.js").addResourceLocations("classpath:/templates/"); 
        registry.addResourceHandler("/templates/**.css").addResourceLocations("classpath:/templates/");
        //其他的静态资源
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");		
		registry.addResourceHandler("/images/**").addResourceLocations("file:"+imagePath);
		super.addResourceHandlers(registry);
	}		
}