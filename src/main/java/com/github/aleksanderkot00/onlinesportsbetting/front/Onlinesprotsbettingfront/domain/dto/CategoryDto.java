package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CategoryDto {
    private long categoryId;
    private String name;
    private Set<EventDto> events;
}
