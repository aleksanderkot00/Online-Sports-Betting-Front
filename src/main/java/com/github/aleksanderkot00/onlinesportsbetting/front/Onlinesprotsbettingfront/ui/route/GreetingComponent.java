package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Route(value = "greet")
public class GreetingComponent extends Div
        implements HasUrlParameter<String> {

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
        setText(String.format("Hello, %s!", vaadinRequest.getRemoteUser()));
    }
}