package com.uyghur.java.gatewayservice.auth_filter;

import com.uyghur.java.gatewayservice.exception.MissingAuthorizationHeaderException;
import com.uyghur.java.gatewayservice.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtService jwtService;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        log.debug("GatewayFilter method starts here in AuthFilter");
        return ((exchange, chain) -> {

            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new MissingAuthorizationHeaderException("Authorization is missing in the request Header!");
                }
                String authenticationHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);

                if (authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
                    authenticationHeader = authenticationHeader.substring(7);
                    log.info("Exchange and Chain done! authenticationHeaders:{%s} in AuthFilter, in GatewayService".formatted(authenticationHeader));
                }
                try {
                    //restTemplate.getForObject("http://AUTHENTICATION-SERVICE/validate-token?token" + authenticationHeader, String.class);
                    jwtService.validateToken(authenticationHeader);

                } catch (Exception e) {
                    e.printStackTrace();
                    log.debug("Unauthorized!!");
                    throw new MissingAuthorizationHeaderException("Unauthorized to this source !");
                }

            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
