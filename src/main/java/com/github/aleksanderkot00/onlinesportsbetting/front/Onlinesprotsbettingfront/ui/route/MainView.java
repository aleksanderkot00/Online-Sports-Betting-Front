package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

@Route
public class MainView extends VerticalLayout {

    String name = SecurityContextHolder.getContext().getAuthentication().getName();

    Button loginButton = new Button("Login");
    Button registrationButton = new Button("Create Account");
    Button cartSlipButton = new Button("My Cart Slip");
    Button eventsButton = new Button("Events");
    Button betsButton = new Button("Bets");
    Button categoriesButton = new Button("Categories");
    Button accountButton = new Button("My Account");
    Button lastMatchesButton = new Button("Last Football Scores");
    Button logoutButton = new Button("Logout");

    public MainView() {
        loginButton.addClickListener( e-> {
            loginButton.getUI().ifPresent(ui -> ui.getPage()
                    .executeJavaScript("window.open(\"http://localhost:8081/login\", \"_self\");"));
        });
        registrationButton.addClickListener( e-> {
            registrationButton.getUI().ifPresent(ui -> ui.navigate("registration"));
        });
        cartSlipButton.addClickListener( e-> {
            cartSlipButton.getUI().ifPresent(ui -> ui.navigate("cart"));
        });
        eventsButton.addClickListener( e-> {
            eventsButton.getUI().ifPresent(ui -> ui.navigate("events"));
        });
        betsButton.addClickListener( e-> {
            betsButton.getUI().ifPresent(ui -> ui.navigate("bets"));
        });
        categoriesButton.addClickListener( e-> {
            categoriesButton.getUI().ifPresent(ui -> ui.navigate("categories"));
        });
        accountButton.addClickListener( e-> {
            accountButton.getUI().ifPresent(ui -> ui.navigate("account"));
        });
        lastMatchesButton.addClickListener( e-> {
            lastMatchesButton.getUI().ifPresent(ui -> ui.navigate("matches"));
        });
        logoutButton.addClickListener( e-> {
            logoutButton.getUI().ifPresent(ui -> ui.getPage()
                    .executeJavaScript("window.open(\"http://localhost:8081/logout\", \"_self\");"));
        });
        if (name.contains("@")) {
            add(accountButton, cartSlipButton, eventsButton, betsButton,
                    categoriesButton, lastMatchesButton, logoutButton);
        } else {
            add(loginButton, registrationButton, eventsButton,
                    categoriesButton, lastMatchesButton);
        }
    }
}
