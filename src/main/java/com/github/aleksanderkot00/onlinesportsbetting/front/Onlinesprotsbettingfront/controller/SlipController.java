package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.controller;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.SlipClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.SlipDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.ValueDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class SlipController {

    private final SlipClient slipClient = new SlipClient();

    @GetMapping("/{userId}/slips")
    public List<SlipDto> getSlips(@PathVariable long userId) {
        return slipClient.getUserSlips(userId);
    }

    @GetMapping("/{userId}/cart")
    public SlipDto getCartSlip(@PathVariable long userId) {
        return slipClient.getCartSlip(userId);
    }

    @PatchMapping("/{userId}/cart/bets/{betId}")
    public SlipDto addBetToCart(@PathVariable long userId, @PathVariable long betId) {
        return slipClient.addBetToCart(userId, betId);
    }

    @DeleteMapping("/{userId}/cart/bets/{betId}")
    public SlipDto removeBetFromCart(@PathVariable long userId, @PathVariable long betId) {
        return slipClient.removeBetFromCart(userId, betId);
    }

    @PutMapping("/{userId}/cart")
    public SlipDto orderCart(@PathVariable long userId) {
        return slipClient.orderCart(userId);
    }

    @PutMapping("/{userId}/cart/empty")
    public SlipDto emptyCart(@PathVariable long userId) {
        return slipClient.emptyCart(userId);
    }

    @PatchMapping("/{userId}/cart/stake")
    public SlipDto setCartStake(@PathVariable long userId, @RequestBody ValueDto value) {
        return slipClient.setCartStake(userId, value);
    }
}
