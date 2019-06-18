package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.configuration.BackendConfig;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.SlipDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.ValueDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception.SlipNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class SlipClient {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<SlipDto> getUserSlips(long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId + "/slips/").build().encode().toUri();
        SlipDto[] response = restTemplate.getForObject(url, SlipDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new SlipDto[0]));    }

    public SlipDto getCartSlip(long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT  + "users/" + userId + "/cart").build().encode().toUri();
        SlipDto response = restTemplate.getForObject(url, SlipDto.class);
        return ofNullable(response).orElseThrow(SlipNotFoundException::new);
    }

    public SlipDto addBetToCart(long userId, long betId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId + "/cart/bets/" + betId).build().encode().toUri();
        SlipDto response = restTemplate.patchForObject(url, null, SlipDto.class);
        return ofNullable(response).orElseThrow(SlipNotFoundException::new);
    }

    public SlipDto removeBetFromCart(long userId, long betId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId + "/cart/bets/" + betId).build().encode().toUri();
        SlipDto response = restTemplate.exchange(url, HttpMethod.DELETE, null,SlipDto.class).getBody();
        return ofNullable(response).orElseThrow(SlipNotFoundException::new);
    }

    public SlipDto orderCart(long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId + "/cart").build().encode().toUri();
        SlipDto response = restTemplate.exchange(url, HttpMethod.PUT, null,SlipDto.class).getBody();
        return ofNullable(response).orElseThrow(SlipNotFoundException::new);
    }

    public SlipDto emptyCart(long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId + "/cart/empty").build().encode().toUri();
        SlipDto response = restTemplate.exchange(url, HttpMethod.PUT, null,SlipDto.class).getBody();
        return ofNullable(response).orElseThrow(SlipNotFoundException::new);
    }

    public SlipDto setCartStake(long userId, ValueDto value) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId + "/cart/stake").build().encode().toUri();
        SlipDto response = restTemplate.patchForObject(url, value, SlipDto.class);
        return ofNullable(response).orElseThrow(SlipNotFoundException::new);
    }
}
