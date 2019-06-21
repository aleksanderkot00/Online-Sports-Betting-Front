package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.CategoryClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.EventDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.util.Set;

@Route(value = "categories")
public class Category extends VerticalLayout implements HasUrlParameter<Long> {

    private CategoryClient categoryClient = new CategoryClient();
    private Grid<EventDto> grid = new Grid<>(EventDto.class);

    Button menu = new Button("Menu");

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        add(menu);
        add(grid);
        setSizeFull();
        Set<EventDto> events =  categoryClient.getCategory(parameter).getEvents();
        grid.setColumns("eventId", "categoryName", "dateTime", "teamOneName", "teamTwoName", "teamOneScore", "teamTwoScore");
        menu.addClickListener(e -> menu.getUI().ifPresent(ui -> ui.navigate("")));

        grid.setItems(events);
    }
}
