package ge.halykbank.pum;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, name = "api")
public class PrjUserManagementApplication {

	public static void main(String[] args) {SpringApplication.run(PrjUserManagementApplication.class, args);}

}
