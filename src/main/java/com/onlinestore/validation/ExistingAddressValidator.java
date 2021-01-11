package com.onlinestore.validation;

import com.onlinestore.annotation.ExistingAddress;
import com.onlinestore.exception.NotFoundException;
import com.onlinestore.user.adresses.Address;
import com.onlinestore.user.adresses.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExistingAddressValidator implements ConstraintValidator<ExistingAddress, Long> {

    private final AddressRepository addressRepository;

    @Override
    public boolean isValid(Long addressId, ConstraintValidatorContext constraintValidatorContext) {
        if (addressId == null) {
            return false;
        }
        return addressWithIdExist(addressId);
    }

    public boolean addressWithIdExist(Long id) {
        return findOptionalAddressById(id).isPresent();
    }

    public Optional<Address> findOptionalAddressById(Long id) {
        return Optional.of(addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Addres with id: " + id + " not exist")));
    }
}
