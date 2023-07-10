package br.com.compassuol.pb.challenge.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("users", r -> r
                        .path("/users")
                        .uri("http://localhost:8082/)"))
                .route("products", r -> r
                        .path("/products")
                        .uri("http://localhost:8183/)"))
                .route("oauth", r -> r
                        .path("/oauth")
                        .uri("http://localhost:8284/)"))
                .route("email", r -> r
                        .path("/sending-email")
                        .uri("http://localhost:8385/)"))
                .route("products", r -> r
                        .path("/products/**")
                        .filters(f -> f.rewritePath("/products/(?<params>.*)", "/?page=1&linesPerPage=5&direction=DESC&orderBy=name"))
                        .uri("http://localhost:8183/"))

                .build();
    }
}
