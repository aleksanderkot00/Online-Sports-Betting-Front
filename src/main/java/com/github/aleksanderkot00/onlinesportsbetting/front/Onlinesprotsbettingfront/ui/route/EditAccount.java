package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.UserClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserRegistrationDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("account/edit")
public class EditAccount extends FormLayout {

    private UserClient userClient = new UserClient();

    private TextField name = new TextField("Name");
    private TextField lastName = new TextField("Last Name");
    private TextField email = new TextField("Email");
    private PasswordField password = new PasswordField("Password");
    private UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
    Binder<UserRegistrationDto> binder;
    Button menu = new Button("Menu");
    Button button = new Button("Register", VaadinIcon.CHECK.create());

    public EditAccount() {
        binder = new Binder<>(UserRegistrationDto.class);
        HorizontalLayout buttons = new HorizontalLayout(button, menu);
        add(name, lastName, email, password, buttons);
        binder.forField(email).bind(UserRegistrationDto::getEmail, UserRegistrationDto::setEmail);
        binder.forField(name).bind(UserRegistrationDto::getName, UserRegistrationDto::setName);
        binder.forField(lastName).bind(UserRegistrationDto::getLastName, UserRegistrationDto::setLastName);
        binder.forField(password).bind(UserRegistrationDto::getPassword, UserRegistrationDto::setPassword);
        binder.setBean(this.userRegistrationDto);
        button.addClickListener(event ->{
            try {
                edit();
                button.getUI().ifPresent(ui -> ui.getPage().reload());
            } catch (Exception e) {
                Notification.show("Something went wrong! Check your data and try again!");
            } finally {
                buttons.getUI().ifPresent(ui -> {
                    ui.getPage().executeJavaScript("window.open(\"http://localhost:8081/logout\", \"_self\");");
                });
            }
        });
        menu.addClickListener(e -> menu.getUI().ifPresent(ui -> ui.navigate("")));
    }

    private void edit() {
        UserRegistrationDto userRegistrationDto = binder.getBean();
        long userId = userClient.getUserIdByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        userClient.editUser(userId, userRegistrationDto);
    }
}