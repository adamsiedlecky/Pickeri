package pl.adamsiedlecki.Pickeri.web.tabs;

import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import pl.adamsiedlecki.Pickeri.entity.FruitVariety;
import pl.adamsiedlecki.Pickeri.service.FruitDeliveryService;
import pl.adamsiedlecki.Pickeri.service.FruitPickerService;
import pl.adamsiedlecki.Pickeri.service.FruitVarietyService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringComponent
@Scope("prototype")
public class StatisticsTab extends VerticalLayout {

    private FruitDeliveryService fruitDeliveryService;
    private FruitPickerService fruitPickerService;
    private FruitVarietyService fruitVarietyService;
    private VerticalLayout root;
    private Label pickersSum;
    private Label packagesSum;
    private Label varietiesSum;
    private Label weightSum;
    private VerticalLayout varietiesPackageAmountAndPercentageLayout;
    private Button refreshButton;
    private Grid<FruitVariety> varietiesGridPackageStat;
    private Grid<FruitVariety> varietiesGridWeightStat;

    @Autowired
    public StatisticsTab(FruitDeliveryService fruitDeliveryService, FruitPickerService fruitPickerService,
                         FruitVarietyService fruitVarietyService){
        this.fruitVarietyService = fruitVarietyService;
        this.fruitDeliveryService = fruitDeliveryService;
        this.fruitPickerService = fruitPickerService;
        initComponents();
    }

    private void initComponents(){
        root = new VerticalLayout();
        pickersSum = new Label();
        packagesSum = new Label();
        varietiesSum = new Label();
        weightSum = new Label();
        varietiesPackageAmountAndPercentageLayout = new VerticalLayout();
        refreshButton = new Button("Odśwież");
        refreshButton.addClickListener(e->refreshData());
        varietiesGridPackageStat = new Grid<>();
        varietiesGridPackageStat.addColumn(FruitVariety::getId).setCaption("ID");
        varietiesGridPackageStat.addColumn(FruitVariety::getName).setCaption("Nazwa");
        varietiesGridPackageStat.addColumn(FruitVariety::getTotalPackages).setCaption("Suma opakowań").setId("totalPackages");
        varietiesGridPackageStat.addColumn(FruitVariety::getPercentageParticipationInPackagesAmountPlainText).setCaption("% udział");

        varietiesGridWeightStat = new Grid<>();
        varietiesGridWeightStat.addColumn(FruitVariety::getId).setCaption("ID");
        varietiesGridWeightStat.addColumn(FruitVariety::getName).setCaption("Nazwa");
        varietiesGridWeightStat.addColumn(FruitVariety::getTotalWeightKgPlainText).setCaption("Waga całkowita").setId("totalWeight");
        varietiesGridWeightStat.addColumn(FruitVariety::getPercentageParticipationInWeightPlainText).setCaption("% udział");


        refreshData();

        root.addComponent(refreshButton);
        root.addComponent(pickersSum);
        root.addComponent(packagesSum);
        root.addComponent(varietiesSum);
        root.addComponent(weightSum);
        root.addComponent(varietiesPackageAmountAndPercentageLayout);
        root.addComponent(varietiesGridPackageStat);
        root.addComponent(varietiesGridWeightStat);

        this.addComponent(root);
    }

    private void refreshData(){
        pickersSum.setValue("Ilość pracowników w systemie: "+fruitPickerService.getTotalAmountOfPickers());
        packagesSum.setValue("Suma szystkich opakowań: "+fruitDeliveryService.getTotalAmountOfPackages());
        varietiesSum.setValue("Suma odmian w systemie: "+fruitVarietyService.findAll().size());
        weightSum.setValue("Całkowita masa owoców w systemie [w kg]: "+fruitDeliveryService.getWeightSum()
                .divide(new BigDecimal(1000),4, RoundingMode.FLOOR));

        varietiesGridPackageStat.setItems(fruitVarietyService.findAll());
        varietiesGridPackageStat.setSizeFull();
        varietiesGridPackageStat.sort("totalPackages", SortDirection.DESCENDING);

        varietiesGridWeightStat.setItems(fruitVarietyService.findAll());
        varietiesGridWeightStat.setSizeFull();
        varietiesGridWeightStat.sort("totalWeight", SortDirection.DESCENDING);
    }

}
