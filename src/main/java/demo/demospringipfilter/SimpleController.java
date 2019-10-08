package demo.demospringipfilter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class SimpleController {

	@RequestMapping(name="/")
	public String hello(HttpServletRequest request) {
		
		HttpServletRequest request2 = 
		        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
		                .getRequest();
		
		return "hello world!: " + IPUtils.getRealIP(request) + ":" + IPUtils.getRealIP(request2);
	}
}
