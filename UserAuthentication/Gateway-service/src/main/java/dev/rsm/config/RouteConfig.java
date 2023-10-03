package dev.rsm.config;

import dev.rsm.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    private final AuthenticationFilter authenticationFilter;

    public RouteConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("user-route", r -> r
                        .path("/user/**")
                        .filters(f -> f
                                .filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .addRequestHeader("REQUEST-FOR", "user"))
                        .uri("lb://USER-SERVICE"))
                .route("forgot-password-route", r -> r
                        .path("/forgot/**")
                        .filters(f -> f
                                .filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .addRequestHeader("REQUEST-FOR", "forgot"))
                        .uri("lb://USER-SERVICE"))

                .route("auth-route", r -> r
                        .path("/auth/**")
                        .filters(f -> f
                                .addRequestHeader("REQUEST-FOR", "auth"))
                        .uri("lb://AUTH-SERVICE"))
                .build();
    }
}
