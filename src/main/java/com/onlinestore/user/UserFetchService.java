package com.onlinestore.user;

import com.onlinestore.exception.NotFoundComponentException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class UserFetchService {

    private final UserRepository userRepository;

    public User fetchUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundComponentException("Bad id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
