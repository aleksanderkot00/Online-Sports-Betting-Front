package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.configuration.BackendConfig;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserDetailsDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserRegistrationDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception.UserNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class UserClient {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());


    public List<UserDto> getUsers() {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/").build().encode().toUri();
        UserDto[] response = restTemplate.getForObject(url, UserDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new UserDto[0]));    }

    public UserDto getUser(long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId).build().encode().toUri();
        UserDto response = restTemplate.getForObject(url, UserDto.class);
        return ofNullable(response).orElseThrow(UserNotFoundException::new);
    }

    public UserDto addUser(UserRegistrationDto registrationDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users").build().encode().toUri();
        UserDto response = restTemplate.postForObject(url, registrationDto, UserDto.class);
        return ofNullable(response).orElseThrow(UserNotFoundException::new);
    }

    public UserDto editUser(long userId, UserRegistrationDto registrationDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId).build().encode().toUri();
        UserDto response = restTemplate.patchForObject(url, registrationDto, UserDto.class);
        return ofNullable(response).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId).build().encode().toUri();
        restTemplate.delete(url);
    }

    public List<UserDetailsDto> getUsersDetails() {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/details").build().encode().toUri();
        UserDetailsDto[] response = restTemplate.getForObject(url, UserDetailsDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new UserDetailsDto[0]));
    }

    public long getUserIdByEmail(String email) {
        return getUsersDetails().stream()
                .filter(userDetailsDto -> userDetailsDto.getEmail().equals(email))
                .findFirst().orElseThrow(UserNotFoundException::new).getUserId();
    }
}
