package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.EventClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.EventDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("events")
public class Events extends VerticalLayout {

    private Grid<EventDto> grid = new Grid<>(EventDto.class);
    private EventClient eventClient = new EventClient();

    Button menu = new Button("Menu");
    public Events() {
        add(menu);
        grid.setColumns("eventId", "categoryName", "dateTime", "teamOneName","teamTwoName","teamOneScore", "teamTwoScore");
        add(grid);
        setSizeFull();
        refresh();
        menu.addClickListener(e -> menu.getUI().ifPresent(ui -> ui.navigate("")));
    }
    private void refresh() {
        grid.setItems(eventClient.getEvents());
    }
}
