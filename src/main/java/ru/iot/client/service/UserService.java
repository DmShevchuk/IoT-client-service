package ru.iot.client.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.iot.client.domain.User;
import ru.iot.client.dto.UserDto;
import ru.iot.client.repository.UserRepository;
import ru.iot.client.utils.AuthUtils;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UUID create(UserDto userDto) {
        var user = mapToUser(new User(), userDto);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user).getId();
    }

    public UUID update(UserDto userDto) throws AccessDeniedException {
        if (!AuthUtils.getCurrentUserId().equals(userDto.getId())) {
            throw new AccessDeniedException("Невозможно редактировать пользователя!");
        }
        var user = userRepository.findById(userDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        mapToUser(user, userDto);
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }
        return userRepository.save(user).getId();
    }

    public UserDto getCurrent() {
        var user = userRepository.findById(AuthUtils.getCurrentUserId())
                .orElseThrow(EntityNotFoundException::new);
        return mapToUserDto(user);
    }

    private User mapToUser(User user, UserDto userDto) {
        user.setLogin(userDto.getLogin());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    private UserDto mapToUserDto(User user) {
        var userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
}
