package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.EventClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.SlipClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.UserClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;

@Route("cart")
public class Cart extends VerticalLayout {

    private UserClient userClient = new UserClient();
    private EventClient eventClient = new EventClient();
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserDto user = userClient.getUser(userClient.getUserIdByEmail(email));
    private Grid<BetDto> grid = new Grid<>(BetDto.class);
    private SlipClient slipClient = new SlipClient();
    private StringDto value = new StringDto();
    private Binder<StringDto> binder;
    SlipDto slip = slipClient.getCartSlip(user.getUserId());

    Button menu = new Button("Menu");
    Label stake = new Label("Stake: " + slip.getStake());
    Label odds = new Label("Total odds: " + slip.getTotalOdds());
    Label possibleToWin = new Label("Possible won:" + slip.getTotalOdds().multiply(slip.getStake()));
    Button order = new Button("Order Slip!");
    Button goToBets = new Button("Go To Bets");

    HorizontalLayout labels = new HorizontalLayout(stake, odds, possibleToWin,goToBets, order);
    private TextField stakeField = new TextField("Stake");
    Button changeStake = new Button("Change Stake");
    VerticalLayout changeStakeLay = new VerticalLayout(stakeField,changeStake);


    public Cart() {
        grid.setColumns("type", "odds", "active");
        add(menu);
        add(grid);
        add(labels);
        add(changeStakeLay);
        setSizeUndefined();
        refresh();
        order.addClickListener(event -> {
            try {
            slipClient.orderCart(user.getUserId());
            order.getUI().ifPresent(ui -> ui.navigate("account/slips"));
            } catch (Exception e) {
                Notification.show("This slip cannot be order. Maybe there are bets from the same events, inactive bets or your AccountBalance is to low.");
            }
        });
        goToBets.addClickListener(event -> goToBets.getUI().ifPresent(ui -> ui.navigate("bets")));
        binder = new Binder<>(StringDto.class);
        binder.forField(stakeField).bind(StringDto::getString, StringDto::setString);
        binder.setBean(this.value);
        changeStake.addClickListener(event -> {
            try {
                value = binder.getBean();
                ValueDto valueDto = new ValueDto();
                valueDto.setValue(new BigDecimal(value.getString()));
                slipClient.setCartStake(user.getUserId(), valueDto);
                changeStake.getUI().ifPresent(ui -> ui.getPage().reload());
            } catch (Exception e){
                Notification.show("Bad value!");
            }
        });
        menu.addClickListener(event -> menu.getUI().ifPresent(ui -> ui.navigate("")));
    }

    private void refresh() {
        grid.setItems(slip.getBets());
        grid.addColumn(betDto -> eventClient.getEvent(betDto.getEventId()).getDateTime());
        grid.addColumn(betDto -> eventClient.getEvent(betDto.getEventId()).getTeamOneName());
        grid.addColumn(betDto -> eventClient.getEvent(betDto.getEventId()).getTeamTwoName());
        grid.addComponentColumn(betDto -> {
            Button button = new Button("Delete from slip");
                long userId = userClient.getUserIdByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
                button.addClickListener(click -> {
                    slipClient.removeBetFromCart(userId, betDto.getBetId());
                    Notification.show("Bet removed from your slip ");
                    button.getUI().ifPresent(ui -> ui.getPage().reload());
                });
            return button;
        });
    }
}
