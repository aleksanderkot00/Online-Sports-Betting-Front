package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.controller;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.BalanceClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.BalanceDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.ValueDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class BalanceController {

    private final BalanceClient balanceClient = new BalanceClient();

    @GetMapping("/{userId}/balance")
    public BalanceDto getBalance(@PathVariable long userId) {
        return balanceClient.getBalance(userId);
    }

    @PatchMapping("/{userId}/payment")
    public void makePayment(@PathVariable long userId, @RequestBody ValueDto valueDto) {
        balanceClient.makePayment(userId, valueDto);
    }
}
