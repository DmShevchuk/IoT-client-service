package ru.iot.client.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iot.client.auth.AuthService;
import ru.iot.client.dto.AuthDto;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody @Valid AuthDto authDto) {
        return ResponseEntity.ok()
                .headers(authService.signIn(authDto))
                .build();
    }
}
