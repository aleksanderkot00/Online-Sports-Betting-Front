package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    @Autowired
    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserDetailsDto> getUsers() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/users/details").build().encode().toUri();
        UserDetailsDto[] response = restTemplate.getForObject(url, UserDetailsDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new UserDetailsDto[0]));
    }
}
