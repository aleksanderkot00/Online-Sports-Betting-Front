package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.ui.route;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.MatchesClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.FootballMatchDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("matches")
public class LastMatches extends VerticalLayout {

    private final int PREMIER_LEAGUE = 62;
    private final int BUNDESLIGA = 117;
    private static int league = 62;
    private Grid<FootballMatchDto> grid = new Grid<>(FootballMatchDto.class);
    private MatchesClient matchesClient = new MatchesClient();

    Button menu = new Button("Menu");
    Button bundesliga = new Button("Bundesliga");
    Button premierLeaguer = new Button("Premier League");

    HorizontalLayout buttons = new HorizontalLayout(menu, premierLeaguer, bundesliga);

    public LastMatches() {
        grid.setColumns("league", "date", "teamOneName", "teamOneScore","teamTwoName", "teamTwoScore");
        add(buttons);
        add(grid);
        setSizeUndefined();
        refresh();
        premierLeaguer.addClickListener(event -> {
            if (league != PREMIER_LEAGUE) {
                league = PREMIER_LEAGUE;
                premierLeaguer.getUI().ifPresent(ui -> ui.getPage().reload());
            }
        });
        bundesliga.addClickListener(event -> {
            if (league != BUNDESLIGA) {
                league = BUNDESLIGA;
                bundesliga.getUI().ifPresent(ui -> ui.getPage().reload());
            }
        });
        menu.addClickListener(event -> menu.getUI().ifPresent(ui -> ui.navigate("")));
    }
    private void refresh() {
        grid.setItems(matchesClient.getMatches(league,55));
    }
}
