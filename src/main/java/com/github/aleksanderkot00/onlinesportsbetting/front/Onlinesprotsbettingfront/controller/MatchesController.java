package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.controller;


import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.MatchesClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.FootballMatchDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches/league")
public class MatchesController {

    private final MatchesClient matchesClient = new MatchesClient();

    @GetMapping("{leagueId}/days/{days}")
    public List<FootballMatchDto> getMatches(@PathVariable int leagueId, @PathVariable int days) {
        return matchesClient.getMatches(leagueId, days);
    }
}
