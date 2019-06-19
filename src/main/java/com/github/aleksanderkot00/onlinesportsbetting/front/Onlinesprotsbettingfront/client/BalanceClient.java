package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.configuration.BackendConfig;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.BalanceDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.ValueDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception.ExchangeRatesNotAvailableException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;

public class BalanceClient {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public BalanceDto getBalance(long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId + "/balance").build().encode().toUri();
        BalanceDto response = restTemplate.getForObject(url, BalanceDto.class);
        return ofNullable(response).orElseThrow(ExchangeRatesNotAvailableException::new);
    }

    public void makePayment(long userId, ValueDto valueDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/"+ userId + "/payment").build().encode().toUri();
        restTemplate.patchForObject(url, valueDto, ResponseEntity.class);
    }
}
