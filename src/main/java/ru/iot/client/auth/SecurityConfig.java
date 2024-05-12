package ru.iot.client.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SecurityConfig {

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.token-type:JWT}")
    private String tokenType;

    @Value("${security.auth.url:/authorization/login}")
    private String authUrl;

    @Value("${security.auth.expire-time}")
    private Long authExpireTime;
}

