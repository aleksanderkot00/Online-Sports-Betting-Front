package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SlipNotFoundException extends RuntimeException {
}
