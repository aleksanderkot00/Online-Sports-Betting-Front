package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NotAuthorizeRequestException extends RuntimeException {
}
