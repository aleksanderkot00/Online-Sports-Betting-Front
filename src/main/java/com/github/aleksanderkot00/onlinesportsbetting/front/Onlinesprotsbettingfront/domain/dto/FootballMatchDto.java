package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FootballMatchDto {

    @JsonProperty("country_name")
    private String country;

    @JsonProperty("league_name")
    private String league;

    @JsonProperty("match_date")
    private LocalDate date;

    @JsonProperty("match_hometeam_name")
    private String teamOneName;

    @JsonProperty("match_hometeam_score")
    private String teamOneScore;

    @JsonProperty("match_awayteam_name")
    private String teamTwoName;

    @JsonProperty("match_awayteam_score")
    private String teamTwoScore;
}
