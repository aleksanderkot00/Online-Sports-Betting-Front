package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class ValueDto {

    @JsonProperty("value")
    private BigDecimal value;
}
