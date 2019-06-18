package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.UserClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "users")
public class UsersView extends VerticalLayout {

    private Grid<UserDto> grid = new Grid<>(UserDto.class);
    private UserClient userClient = new UserClient();

    public UsersView() {
        grid.setColumns("userId", "name", "lastName", "email","balance", "active", "roles");
        add(grid);
        setSizeFull();
        refresh();
    }
    private void refresh() {
        grid.setItems(userClient.getUsers());
    }
}
