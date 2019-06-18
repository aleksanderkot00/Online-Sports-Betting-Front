package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;

public class ParameterMenu extends Div {
    public ParameterMenu() {
        String route = UI.getCurrent().getRouter()
                .getUrl(GreetingComponent.class, "anonymous");
        Anchor link = new Anchor(route, "Greeting");
        add(link);
    }
}