package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.CategoryClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.CategoryDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("categories")
public class Categories extends VerticalLayout {

    private Grid<CategoryDto> grid = new Grid<>(CategoryDto.class);
    private CategoryClient categoryClient = new CategoryClient();
    Button menu = new Button("Menu");

    public Categories() {
        add(menu);
        grid.setColumns("categoryId", "name");
        add(grid);
        setSizeFull();
        refresh();
    }
    private void refresh() {
        grid.setItems(categoryClient.getCategories());
        grid.addComponentColumn(categoryDto -> {
            Button button = new Button("See category!");
            button.addClickListener(click ->{
                button.getUI().ifPresent(ui -> ui.navigate("categories/" + categoryDto.getCategoryId()));
            });
            return button;
        });
        menu.addClickListener(event -> menu.getUI().ifPresent(ui -> ui.navigate("")));
    }
}