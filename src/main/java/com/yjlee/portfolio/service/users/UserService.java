package com.yjlee.portfolio.service.users;

import com.yjlee.portfolio.config.auth.dto.OAuthAttributes;
import com.yjlee.portfolio.domain.users.User;
import com.yjlee.portfolio.domain.users.UserRepository;
import com.yjlee.portfolio.web.dto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public LoginUserDto saveOrUpdate(LoginUserDto loginUserDto) {
        User user = userRepository.findByEmail(loginUserDto.getEmail())
                .map(entity -> entity.update(loginUserDto.getName(), loginUserDto.getImageUrl()))
                .orElse(loginUserDto.toEntity());

        userRepository.save(user);

        return loginUserDto;
    }
}
