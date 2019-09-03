package danielh1307.springboothateoasexample.configuration;


import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {

        TypeResolver typeResolver = new TypeResolver();

        // List<Link> wird zu Map<String, Link>
        AlternateTypeRule alternateTypeRule = AlternateTypeRules.newRule(
                typeResolver.resolve(List.class, typeResolver.resolve(Link.class)),
                typeResolver.resolve(Map.class, typeResolver.resolve(String.class), typeResolver.resolve(Link.class)));

        // TODO: müsste man nicht aus Link noch rel entfernen? Kann aber vielleicht drinbleiben, weil er einfach nicht zurückgegeben wird.

        return new Docket(SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any()) // here we could narrow down the packages
                .paths(ant("/greeting/**")) // here we can narrow down the path
                .build()
                .apiInfo(getApiInfo())
                .alternateTypeRules(alternateTypeRule);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "HATEOAS demo",
                "Small demo for HATEOAS, SpringFox, model generation ...",
                "0.0.1-SNAPSHOT",
                "TERMS OF SERVICE URL",
                new Contact("Daniel Hamm", "URL", "mail@mail.com"),
                "No license required",
                "",
                emptyList()
        );
    }
}
