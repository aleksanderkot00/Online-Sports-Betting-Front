package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDetailsDto {
    private long userId;
    private String email;
    private String encryptedPassword;
    private boolean active;
    private Set<String> roles;
}
