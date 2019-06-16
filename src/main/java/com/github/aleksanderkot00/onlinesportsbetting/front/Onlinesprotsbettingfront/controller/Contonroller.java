package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static java.util.Optional.ofNullable;


@RestController
@RequestMapping("users")
public class Contonroller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<User> getUsers() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/users").build().encode().toUri();
        User[] response = restTemplate.getForObject(url, User[].class);
        return Arrays.asList(ofNullable(response).orElse(new User[0]));
    }

    private HttpHeaders createHttpHeaders(String user, String password)
    {
        String notEncoded = user + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(notEncoded.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedAuth);
        return headers;
    }
}
