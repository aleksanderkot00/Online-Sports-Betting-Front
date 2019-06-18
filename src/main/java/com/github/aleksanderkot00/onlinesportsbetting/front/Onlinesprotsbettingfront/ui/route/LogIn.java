package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import org.springframework.stereotype.Component;

@Component
public class LogIn extends FormLayout {

    public LogIn() {

        Button usersButton = new Button();
        usersButton.setText("USERS");
        usersButton.addClickListener( e-> {
            usersButton.getUI().ifPresent(ui -> ui.navigate("users"));
        });

        Button userButton = new Button();
        userButton.setText("Login");
        userButton.addClickListener( e-> {
            userButton.getUI().ifPresent(ui -> ui.navigate("users/3"));
        });

        add(userButton, usersButton);
    }
}
