package ru.iot.client.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Identifiable {

    @Column(name = "login", nullable = false)
    @NotEmpty
    private String login;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "last_name", nullable = false)
    @NotEmpty
    private String lastName;

    @Column(name = "first_name", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    @Size(min = 6, max = 18, message = "Неверный формат номера телефона")
    private String phoneNumber;

}
