package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.controller;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.EventClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.EventDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {

    private final EventClient eventClient = new EventClient();
    @GetMapping
    public List<EventDto> getEvents() {
        return eventClient.getEvents();
    }

    @GetMapping("/{eventId}")
    public EventDto getEvent(@PathVariable long eventId) {
        return eventClient.getEvent(eventId);
    }

    @PostMapping
    public EventDto addEvent(@RequestBody EventDto eventDto) {
        return eventClient.addEvent(eventDto);
    }

    @PatchMapping("/{eventId}")
    public EventDto editEvent(@PathVariable long eventId, @RequestBody EventDto eventDto) {
        return eventClient.editEvent(eventId, eventDto);
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable long eventId) {
        eventClient.deleteEvent(eventId);
    }
}
