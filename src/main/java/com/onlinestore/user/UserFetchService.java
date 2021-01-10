package com.onlinestore.user;

import com.onlinestore.exception.NotFoundComponentException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Component
@RequiredArgsConstructor
public class UserFetchService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User fetchUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundComponentException("Bad id: " + id));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll() .stream()
                .map(p -> userMapper.mapToUserDto(p))
                .collect(Collectors.toList());
    }
}
