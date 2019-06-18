package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BalanceDto {
    private LocalDate rateDate;
    private BigDecimal plnBalance;
    private BigDecimal eurBalance;
    private BigDecimal usdBalance;
    private BigDecimal gbpBalance;
}
