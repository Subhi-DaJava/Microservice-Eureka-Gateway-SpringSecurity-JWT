package com.uyghur.java.gatewayservice.auth_filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/auth/save-user",
            "/auth/validate-token",
            "/eureka"
    );
    //"/auth/save-user",

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
