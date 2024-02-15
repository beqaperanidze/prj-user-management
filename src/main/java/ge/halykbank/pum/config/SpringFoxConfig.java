//package ge.halykbank.pum.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.BasicAuth;
//import springfox.documentation.service.SecurityScheme;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class SpringFoxConfig {
//    private List<springfox.documentation.service.SecurityScheme> basicScheme() {
//        List<springfox.documentation.service.SecurityScheme> schemeList = new ArrayList<>();
//        schemeList.add((SecurityScheme) new BasicAuth("basicAuth"));
//        return schemeList;
//    }
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(basicScheme());
//    }
//}