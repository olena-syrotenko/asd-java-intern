package team.asd.config;

import com.antkorwin.xsync.XSync;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XSyncConfig {
	@Bean
	public XSync<Integer> intXSync(){
		return new XSync<>();
	}

	@Bean
	public XSync<String> stringXSync(){
		return new XSync<>();
	}
}
