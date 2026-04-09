package com.shopflow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI shopFlowOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ShopFlow API")
                        .description("E-commerce backend — User, Product & Order management")
                        .version("1.0.0"));
    }
}
