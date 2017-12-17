package cz.sobotik.ipblocker.core.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cz.sobotik.ipblocker.core.LimitTrafficInterceptor;
import cz.sobotik.ipblocker.core.rest.converter.StringToInetAddressConverter;

@Configuration
@EnableAspectJAutoProxy
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * Bean to handle traffic and block domains which exceed limits
	 */
	@Bean
	public LimitTrafficInterceptor limitTraffic() {
		return new LimitTrafficInterceptor();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addConverter(new StringToInetAddressConverter());
	}
}
