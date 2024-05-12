package ru.iot.client.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iot.client.dto.UserDto;
import ru.iot.client.service.UserService;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public UUID create(@RequestBody @Valid UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping
    public UUID update(@RequestBody @Valid UserDto userDto) throws AccessDeniedException {
        return userService.update(userDto);
    }

    @GetMapping("/current")
    public UserDto getCurrent() {
        return userService.getCurrent();
    }
}
