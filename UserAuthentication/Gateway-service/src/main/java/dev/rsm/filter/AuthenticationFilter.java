package dev.rsm.filter;

import dev.rsm.exception.AuthenticationException;
import dev.rsm.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;
    public AuthenticationFilter() {
        super();
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new AuthenticationException("Missing authorization header.", HttpStatus.UNAUTHORIZED, 301);
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (!(authHeader != null && authHeader.startsWith("Bearer "))) {
                    log.error("Invalid access with no jwt token");
                    throw new AuthenticationException("Unauthorized access to with no jwt token.", HttpStatus.UNAUTHORIZED, 302);
                }
                authHeader = authHeader.substring(7);

                try {
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    log.error("Invalid access with jwt token: {}", authHeader);
                    throw new AuthenticationException("Unauthorized access to with wrong jwt token.", HttpStatus.UNAUTHORIZED, 303);
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }
}
