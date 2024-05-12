package ru.iot.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iot.client.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);
}
