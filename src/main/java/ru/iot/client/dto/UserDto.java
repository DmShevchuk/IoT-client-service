package ru.iot.client.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;

    @NotEmpty
    private String login;

    private String password;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String firstName;

    private String middleName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phoneNumber;

}
