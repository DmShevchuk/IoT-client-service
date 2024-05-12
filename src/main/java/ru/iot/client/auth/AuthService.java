package ru.iot.client.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.iot.client.dto.AuthDto;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailServiceImpl userDetailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final SecurityConfig securityConfig;

    public HttpHeaders signIn(AuthDto authDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authDto.getUsername(),
                authDto.getPassword()
        ));

        var user = userDetailService.loadUserByUsername(authDto.getUsername());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", securityConfig.getPrefix() + jwtService.generateToken(user));

        return responseHeaders;
    }
}