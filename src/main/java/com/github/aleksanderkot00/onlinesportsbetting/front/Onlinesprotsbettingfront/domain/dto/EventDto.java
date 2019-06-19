package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EventDto {
    private long eventId;
    private LocalDateTime dateTime;
    private String categoryName;
    private String teamOneName;
    private String teamTwoName;
    private BigDecimal teamOneScore;
    private BigDecimal teamTwoScore;

    @Override
    public String toString() {
        return  dateTime +
                ", categoryName='" + categoryName + '\'' +
                ", teamOneName='" + teamOneName + '\'' +
                ", teamTwoName='" + teamTwoName + '\'' +
                ", teamOneScore=" + teamOneScore +'\'' +
                ", teamTwoScore=" + teamTwoScore ;
    }
}
