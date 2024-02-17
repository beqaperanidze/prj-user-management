package ge.halykbank.pum;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@SecurityScheme(scheme = "basic", type = SecuritySchemeType.HTTP, name = "api")
public class PrjUserManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrjUserManagementApplication.class, args);
    }
}
