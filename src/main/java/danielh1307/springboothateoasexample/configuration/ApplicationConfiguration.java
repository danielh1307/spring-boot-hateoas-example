package danielh1307.springboothateoasexample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

import javax.servlet.Filter;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Filter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }
}
