package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.controller;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
public class User implements Serializable {
    private long userId;
    private String name;
    private String lastName;
    private String email;
    private BigDecimal balance;
    private String encryptedPassword;
    private boolean active;
    private Set<String> roles = new HashSet<>();
    public long getUserId() {
        return userId;
    }
}
