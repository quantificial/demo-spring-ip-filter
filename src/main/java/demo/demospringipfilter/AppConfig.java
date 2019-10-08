package demo.demospringipfilter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="app.ip-filter")
@Data
public class AppConfig {
	
	private boolean enabled;
	
	// inject the yaml list into java list
	private final List<String> allowedIp = new ArrayList<String>();

}
