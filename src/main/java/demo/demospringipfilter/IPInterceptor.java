package demo.demospringipfilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IPInterceptor implements HandlerInterceptor {
	
	@Autowired
	private AppConfig appConfig;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String ipAddress = IPUtils.getRealIP(request);

		
		log.info("USER IP ADDRESS IS =>" + ipAddress);
		
		if(appConfig.isEnabled() == false)
			return true;
		
		if (!StringUtils.isNotBlank(ipAddress))
			return false;
		
		boolean found = appConfig.getAllowedIp().stream().anyMatch(s -> s.trim().equals(ipAddress.trim()));
		
		if(found == false) {
			log.info("ip address not in allowed ip list");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		
		log.info("ip address found in allowed ip list");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
