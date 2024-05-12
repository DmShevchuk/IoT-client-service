package ru.iot.client.utils;

import com.sun.security.auth.UserPrincipal;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.iot.client.auth.UserDetailsImpl;

import java.util.UUID;

@UtilityClass
public class AuthUtils {

    public static UserDetailsImpl getUserPrincipal() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            return (UserDetailsImpl) authentication.getPrincipal();
        }
        throw new AccessDeniedException("Пользователь не аутентифицирован!");
    }

    public static UUID getCurrentUserId() {
        val userPrincipal = getUserPrincipal();
        return userPrincipal.getId();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
