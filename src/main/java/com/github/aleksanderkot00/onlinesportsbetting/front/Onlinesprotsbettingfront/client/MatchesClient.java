package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.configuration.BackendConfig;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.FootballMatchDto;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class MatchesClient {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<FootballMatchDto> getMatches(int leagueId, int days) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "matches/league/" + leagueId + "/days/" + days)
                .build().encode().toUri();
        FootballMatchDto[] response = restTemplate.getForObject(url, FootballMatchDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new FootballMatchDto[0]));
    }

}
