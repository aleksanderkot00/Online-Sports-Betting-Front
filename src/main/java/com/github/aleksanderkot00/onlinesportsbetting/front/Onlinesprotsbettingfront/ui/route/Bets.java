package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.BetClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.EventClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.SlipClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.UserClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.BetDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.EventDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception.EventNotFoundException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;


@Route("bets")
public class Bets extends VerticalLayout {

    private Grid<BetDto> grid = new Grid<>(BetDto.class);
    private BetClient betClient = new BetClient();
    private EventClient eventClient = new EventClient();
    private UserClient userClient = new UserClient();
    private SlipClient slipClient = new SlipClient();
    private final long userId = userClient.getUserIdByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    private List<EventDto> events = eventClient.getEvents();

    Button menu = new Button("Menu");

    public Bets() {
        add(menu);
        grid.setColumns("type", "odds", "active");
        add(grid);
        setSizeFull();
        refresh();
    }
    private void refresh() {
        grid.setItems(betClient.getBets());
        grid.addColumn(betDto -> getEventById(betDto.getEventId()).getDateTime());
        grid.addColumn(betDto -> getEventById(betDto.getEventId()).getTeamOneName());
        grid.addColumn(betDto -> getEventById(betDto.getEventId()).getTeamTwoName());
        grid.addComponentColumn(betDto -> {
            Button button = new Button("Add To Slip");
            if (betDto.isActive()) {
                button.addClickListener(click -> {
                    slipClient.addBetToCart(userId, betDto.getBetId());
                    Notification.show("Bet added to your slip ");
                    button.getUI().ifPresent(ui -> ui.getPage().reload());
                });
            } else {
                button.addClickListener(click -> Notification.show("Bet is inactive or you are not logged in!"));
            }
            return button;
        });
        menu.addClickListener(event -> menu.getUI().ifPresent(ui -> ui.navigate("")));
    }
    private EventDto getEventById(long id) {
        return events.stream()
                .filter(eventDto -> eventDto.getEventId() == id)
                .findFirst().orElseThrow(EventNotFoundException::new);
    }
}
