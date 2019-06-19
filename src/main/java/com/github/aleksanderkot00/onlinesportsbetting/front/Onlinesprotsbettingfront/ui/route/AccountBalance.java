package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.BalanceClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.UserClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.BalanceDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.StringDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.ValueDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;

@Route("account/balance")
public class AccountBalance extends HorizontalLayout {
    private UserClient userClient = new UserClient();
    private BalanceClient balanceClient = new BalanceClient();
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserDto user = userClient.getUser(userClient.getUserIdByEmail(email));

    private StringDto value = new StringDto();
    private Binder<StringDto> binder;
    private TextField valueField = new TextField("Value");
    private Button valueButton = new Button("Make Payment");

    BalanceDto payment = balanceClient.getBalance(user.getUserId());

    Button menu = new Button("Menu");
    Label date = new Label("Rates Date: " + payment.getRateDate());
    Label pln = new Label("PLN: " + payment.getPlnBalance());
    Label eur = new Label("EUR: " + payment.getEurBalance());
    Label usd = new Label("USD: " + payment.getUsdBalance());
    Label gbp = new Label("GBP: " + payment.getGbpBalance());


    VerticalLayout balanceLayout = new VerticalLayout(date, pln, eur,usd, gbp);
    VerticalLayout paymentLayout = new VerticalLayout(valueField, valueButton);

    public AccountBalance() {
        add(menu);
        add(balanceLayout);
        add(paymentLayout);
        binder = new Binder<>(StringDto.class);
        binder.forField(valueField).bind(StringDto::getString, StringDto::setString);
        binder.setBean(this.value);
        valueButton.addClickListener(event -> {
            try {
                value = binder.getBean();
                ValueDto valueDto = new ValueDto();
                valueDto.setValue(new BigDecimal(value.getString()));
                balanceClient.makePayment(user.getUserId(), valueDto);
            } catch (Exception e){
                Notification.show("Bad value!");
            } finally {
                valueButton.getUI().ifPresent(ui -> ui.getPage().reload());
            }
        });
        menu.addClickListener(event -> menu.getUI().ifPresent(ui -> ui.navigate("")));
    }
}
