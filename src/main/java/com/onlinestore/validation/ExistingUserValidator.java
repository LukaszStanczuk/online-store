package com.onlinestore.validation;

import com.onlinestore.annotation.ExistingUser;
import com.onlinestore.exception.NotFoundException;
import com.onlinestore.user.User;
import com.onlinestore.user.UserRepository;
import com.onlinestore.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExistingUserValidator implements ConstraintValidator<ExistingUser, Long> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext constraintValidatorContext) {
        if (!(userId instanceof Long)) {
            return false;
        }
        if ( userId == null){
            return false;
        }
        return userWithIdExist(userId);
//        return userWithIdExist(userId);
    }

    public boolean userWithIdExist(Long userId) {
        return findOptionalUserById(userId).isPresent();
    }

    public Optional<User> findOptionalUserById(Long userId) {
        return Optional.of(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with " + userId + " not exist")));
    }
}
