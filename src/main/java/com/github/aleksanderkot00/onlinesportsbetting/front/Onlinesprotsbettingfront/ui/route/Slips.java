package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.SlipClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.UserClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.SlipDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("account/slips")
public class Slips extends HorizontalLayout {
    private UserClient userClient = new UserClient();
    private SlipClient slipClient = new SlipClient();
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserDto user = userClient.getUser(userClient.getUserIdByEmail(email));
    private Grid<SlipDto> grid = new Grid<>(SlipDto.class);

    Button menu = new Button("Menu");

    public Slips() {
        add(menu);
        grid.setColumns("state", "stake", "totalOdds");
        add(grid);
        setSizeFull();
        refresh();
    }
    private void refresh() {
        grid.setItems(slipClient.getUserSlips(user.getUserId()));
        menu.addClickListener(e -> menu.getUI().ifPresent(ui -> ui.navigate("")));
    }
}