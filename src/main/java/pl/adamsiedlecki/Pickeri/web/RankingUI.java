package pl.adamsiedlecki.Pickeri.web;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import pl.adamsiedlecki.Pickeri.tools.ResourceGetter;
import pl.adamsiedlecki.Pickeri.web.tabs.independentTabs.MenuTab;
import pl.adamsiedlecki.Pickeri.web.tabs.independentTabs.RankingTab;

@SpringUI(path = "/ranking")
@Theme("mytheme")
@StyleSheet("https://fonts.googleapis.com/css?family=Ubuntu&display=swap")
@Title("Ranking")
public class RankingUI extends UI {

    private VerticalLayout root;
    private RankingTab rankingTab;
    private MenuTab othersTab;
    private Environment env;

    @Autowired
    public RankingUI(RankingTab rankingTab, MenuTab othersTab, Environment environment) {
        this.rankingTab = rankingTab;
        this.othersTab = othersTab;
        this.env = environment;
    }

    @Override
    protected void init(VaadinRequest request) {
        root = new VerticalLayout();
        root.addComponent(ResourceGetter.getPickeriLogoAsEmbeddedComponent());
        addTabs();
        this.setContent(root);
    }

    private void addTabs() {
        TabSheet tabs = new TabSheet();
        tabs.addTab(rankingTab, env.getProperty("ranking.tab"));
        tabs.addTab(othersTab, env.getProperty("menu.tab.caption"));
        root.addComponent(tabs);
    }

}
