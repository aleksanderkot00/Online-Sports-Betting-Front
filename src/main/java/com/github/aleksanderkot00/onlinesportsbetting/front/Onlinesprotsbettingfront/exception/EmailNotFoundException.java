package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {
    public EmailNotFoundException() {
        super("Email not found!");
    }
}
