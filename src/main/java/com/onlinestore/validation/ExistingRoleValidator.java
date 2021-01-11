package com.onlinestore.validation;

import com.onlinestore.annotation.ExistingRole;
import com.onlinestore.exception.NotFoundException;
import com.onlinestore.user.role.UserRole;
import com.onlinestore.user.role.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExistingRoleValidator implements ConstraintValidator<ExistingRole, Long> {

    private final UserRoleRepository userRoleRepository;

    @Override
    public boolean isValid(Long userRoleId, ConstraintValidatorContext constraintValidatorContext) {
        if (userRoleId == null) {
            return false;
        }
        return userRoleWithIdExist(userRoleId);
    }

    public boolean userRoleWithIdExist(Long id) {
        return findOptionalUserRoleById(id).isPresent();
    }

    public Optional<UserRole> findOptionalUserRoleById(Long id) {
        return Optional.of(userRoleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role with " + id + " not exist")));
    }
}
