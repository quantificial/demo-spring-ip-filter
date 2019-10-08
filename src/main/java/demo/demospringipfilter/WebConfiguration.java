package demo.demospringipfilter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {
	
	@Value("${app.ip-filter.allowed-ip-addresses}") 
	private String[] allowedIpAddresses;
	
	@Autowired
	private AppConfig appConfig;

	@Bean
	public HandlerInterceptor getMyInterceptor() {
		return new IPInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		for(int i=0; i< allowedIpAddresses.length;i++)
			log.info("allowed ip: " + allowedIpAddresses[i]);
		
		for(String s : appConfig.getAllowedIp()) {
			log.info("allowed ip in appConfig: " + s);
		}
		
		registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
	}
}
