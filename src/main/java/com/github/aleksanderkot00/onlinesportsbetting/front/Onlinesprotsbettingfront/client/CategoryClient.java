package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.configuration.BackendConfig;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.CategoryDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.NameDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.SlipDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception.CategoryNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class CategoryClient {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<CategoryDto> getCategories() {
    URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "categories").build().encode().toUri();
    CategoryDto[] response = restTemplate.getForObject(url, CategoryDto[].class);
    return Arrays.asList(ofNullable(response).orElse(new CategoryDto[0]));
}

    public CategoryDto getCategory(long categoryId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "categories/" + categoryId).build().encode().toUri();
        CategoryDto response = restTemplate.getForObject(url, CategoryDto.class);
        return ofNullable(response).orElseThrow(CategoryNotFoundException::new);
    }

    public CategoryDto addCategory(NameDto name) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "categories").build().encode().toUri();
        CategoryDto response = restTemplate.postForObject(url, name, CategoryDto.class);
        return ofNullable(response).orElseThrow(CategoryNotFoundException::new);
    }

    public CategoryDto changeName(long categoryId, NameDto name) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "categories/" + categoryId).build().encode().toUri();
        CategoryDto response = restTemplate.patchForObject(url, name, CategoryDto.class);
        return ofNullable(response).orElseThrow(CategoryNotFoundException::new);
    }

    public void deleteCategory(long categoryId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "categories/" + categoryId).build().encode().toUri();
        restTemplate.delete(url);
    }

    public CategoryDto addEventToCategory(long categoryId, long eventId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "categories/" + categoryId + "/events/" + eventId)
                .build().encode().toUri();
        CategoryDto response = restTemplate.exchange(url, HttpMethod.PATCH, null, CategoryDto.class).getBody();
        return ofNullable(response).orElseThrow(CategoryNotFoundException::new);
    }
}