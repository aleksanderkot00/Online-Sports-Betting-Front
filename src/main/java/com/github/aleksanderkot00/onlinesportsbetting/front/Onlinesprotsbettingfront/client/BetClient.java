package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.configuration.BackendConfig;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.BetDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception.BetNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class BetClient {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<BetDto> getBets() {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "bets").build().encode().toUri();
        BetDto[] response = restTemplate.getForObject(url, BetDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new BetDto[0]));
    }

    public BetDto getBet(long betId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "bets/" + betId).build().encode().toUri();
        BetDto response = restTemplate.getForObject(url, BetDto.class);
        return ofNullable(response).orElseThrow(BetNotFoundException::new);
    }

    public BetDto addBet(BetDto betDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "bets").build().encode().toUri();
        BetDto response = restTemplate.postForObject(url, betDto, BetDto.class);
        return ofNullable(response).orElseThrow(BetNotFoundException::new);
    }

    public BetDto changeActivity(long betId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "bets/" + betId).build().encode().toUri();
        BetDto response = restTemplate.patchForObject(url, null, BetDto.class);
        return ofNullable(response).orElseThrow(BetNotFoundException::new);
    }

    public void deleteBet(long betId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "bets/" + betId).build().encode().toUri();
        restTemplate.delete(url);
    }
}

