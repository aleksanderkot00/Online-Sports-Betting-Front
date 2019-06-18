package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;

public class Menu extends Div {
    public Menu() {
        String route = UI.getCurrent().getRouter()
                .getUrl(PathComponent.class);
        Anchor link = new Anchor(route, "Path");
        add(link);
    }
}