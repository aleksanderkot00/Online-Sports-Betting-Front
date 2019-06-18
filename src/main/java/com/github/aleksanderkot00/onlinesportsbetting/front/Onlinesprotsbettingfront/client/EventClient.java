package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.configuration.BackendConfig;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.EventDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception.EventNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class EventClient {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<EventDto> getEvents() {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "events").build().encode().toUri();
        EventDto[] response = restTemplate.getForObject(url, EventDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new EventDto[0]));
    }

    public EventDto getEvent(long eventId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "events/" + eventId).build().encode().toUri();
        EventDto response = restTemplate.getForObject(url, EventDto.class);
        return ofNullable(response).orElseThrow(EventNotFoundException::new);
    }

    public EventDto addEvent(EventDto eventDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "events").build().encode().toUri();
        EventDto response = restTemplate.postForObject(url, eventDto, EventDto.class);
        return ofNullable(response).orElseThrow(EventNotFoundException::new);
    }

    public EventDto editEvent(long eventId, EventDto eventDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "events/" + eventId).build().encode().toUri();
        EventDto response = restTemplate.patchForObject(url, eventDto, EventDto.class);
        return ofNullable(response).orElseThrow(EventNotFoundException::new);
    }

    public void deleteEvent(long eventId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "events/" + eventId).build().encode().toUri();
        restTemplate.delete(url);
    }
}
