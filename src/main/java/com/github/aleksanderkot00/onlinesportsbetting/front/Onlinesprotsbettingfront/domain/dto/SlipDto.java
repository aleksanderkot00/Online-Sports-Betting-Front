package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.SlipState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class SlipDto {
    private Set<BetDto> bets;
    private BigDecimal stake;
    private SlipState state;
    private BigDecimal totalOdds;
}
