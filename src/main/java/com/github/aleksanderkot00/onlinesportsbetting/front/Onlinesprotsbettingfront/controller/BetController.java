package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.controller;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.BetClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.BetDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bets")
public class BetController {

    private final BetClient betClient = new BetClient();
    @GetMapping
    public List<BetDto> getBets() {
        return betClient.getBets();
    }

    @GetMapping("/{betId}")
    public BetDto getBet(@PathVariable long betId) {
        return betClient.getBet(betId);
    }

    @PostMapping
    public BetDto addBet(@RequestBody BetDto betDto) {
        return betClient.addBet(betDto);
    }

    @PatchMapping("/{betId}")
    public BetDto changeActivity(@PathVariable long betId) {
        return betClient.changeActivity(betId);
    }

    @DeleteMapping("/{betId}")
    public void deleteBet(@PathVariable long betId) {
        betClient.deleteBet(betId);
    }
}
