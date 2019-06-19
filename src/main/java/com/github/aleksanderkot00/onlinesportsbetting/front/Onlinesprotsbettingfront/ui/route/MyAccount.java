package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.UserClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("account")
public class MyAccount extends VerticalLayout {

    private UserClient userClient = new UserClient();
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserDto user = userClient.getUser(userClient.getUserIdByEmail(email));

    Button menu = new Button("Menu");
    Label name = new Label("Name: " + user.getName());
    Label lastName = new Label("Last Name: " + user.getLastName());
    Label emailLab = new Label("Email: " + email);

    Button cartSlip = new Button("Cart Slip");
    Button slipHistory = new Button("Last Slips");
    Button balance = new Button("Account Balance");
    Button edit = new Button("Edit Account");
    HorizontalLayout buttons = new HorizontalLayout(cartSlip, slipHistory, balance, edit);


    public MyAccount(){
        add(menu);
        add(name,  lastName, emailLab, buttons);
        cartSlip.addClickListener(event -> cartSlip.getUI().ifPresent(ui -> ui.navigate("cart")));
        balance.addClickListener(event -> cartSlip.getUI().ifPresent(ui -> ui.navigate("account/balance")));
        slipHistory.addClickListener(event -> cartSlip.getUI().ifPresent(ui -> ui.navigate("account/slips")));
        menu.addClickListener(e -> menu.getUI().ifPresent(ui -> ui.navigate("")));
        edit.addClickListener(e -> menu.getUI().ifPresent(ui -> ui.navigate("account/edit")));
    }
}
