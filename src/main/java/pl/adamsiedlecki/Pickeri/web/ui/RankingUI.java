package pl.adamsiedlecki.Pickeri.web.ui;

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
import pl.adamsiedlecki.Pickeri.service.SettingsService;
import pl.adamsiedlecki.Pickeri.tools.UserInterfaceTools.AlignmentSetter;
import pl.adamsiedlecki.Pickeri.tools.UserInterfaceTools.HeaderAdder;
import pl.adamsiedlecki.Pickeri.web.tab.independentTabs.MenuTab;
import pl.adamsiedlecki.Pickeri.web.tab.independentTabs.RankingTab;

@SpringUI(path = "/ranking")
@Theme("mytheme")
@StyleSheet("https://fonts.googleapis.com/css?family=Ubuntu&display=swap")
@Title("${ranking.title}")
public class RankingUI extends UI {

    private VerticalLayout root;
    private RankingTab rankingTab;
    private MenuTab othersTab;
    private Environment env;
    private TabSheet tabs;
    private SettingsService settingsService;

    @Autowired
    public RankingUI(RankingTab rankingTab, MenuTab othersTab, Environment environment, SettingsService settingsService) {
        this.rankingTab = rankingTab;
        this.othersTab = othersTab;
        this.env = environment;
        this.settingsService = settingsService;
    }

    @Override
    protected void init(VaadinRequest request) {
        root = new VerticalLayout();
        HeaderAdder.add(root, settingsService);
        addTabs();
        AlignmentSetter.apply(root, tabs);
        this.setContent(root);
    }

    private void addTabs() {
        tabs = new TabSheet();
        tabs.addTab(rankingTab, env.getProperty("ranking.tab"));
        tabs.addTab(othersTab, env.getProperty("menu.tab.caption"));
        root.addComponent(tabs);
    }

}