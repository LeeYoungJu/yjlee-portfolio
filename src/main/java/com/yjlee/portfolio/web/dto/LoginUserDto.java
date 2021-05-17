package com.yjlee.portfolio.web.dto;

import com.yjlee.portfolio.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginUserDto {

    private String name;
    private String email;
    private String imageUrl;

    @Builder
    public LoginUserDto(String name, String email, String imageUrl) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(imageUrl)
                .build();
    }
}
