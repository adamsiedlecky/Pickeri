package pl.adamsiedlecki.Pickeri.web.tabs.documentsTabs;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.adamsiedlecki.Pickeri.entity.FruitPicker;
import pl.adamsiedlecki.Pickeri.entity.FruitType;
import pl.adamsiedlecki.Pickeri.service.FruitDeliveryService;
import pl.adamsiedlecki.Pickeri.service.FruitPickerService;
import pl.adamsiedlecki.Pickeri.service.FruitTypeService;
import pl.adamsiedlecki.Pickeri.tools.excel.ExcelCreator;

import java.io.File;
import java.util.List;

@Component
@Scope("prototype")
public class ExcelGenerationTab extends VerticalLayout {

    private FruitPickerService fruitPickerService;
    private Environment env;
    private FruitTypeService fruitTypeService;
    private FruitDeliveryService fruitDeliveryService;

    public ExcelGenerationTab(Environment env, FruitPickerService fruitPickerService, FruitTypeService fruitTypeService,
                              FruitDeliveryService fruitDeliveryService){
        this.fruitPickerService = fruitPickerService;
        this.fruitTypeService = fruitTypeService;
        this.fruitDeliveryService = fruitDeliveryService;
        this.env = env;
        addGenerateButton("generate.raport.excel.button", "src\\main\\resources\\downloads\\excelRaport.xls",
                "download.excel.raport", "/download/excel/excelRaport.xls",0);
        addGenerateButton("generate.deliveries.raport.excel.button", "src\\main\\resources\\downloads\\excelDeliveriesRaport.xls",
                "deliveries.raport.title", "/download/excel/excelDeliveriesRaport.xls",1);

        this.forEach(component -> this.setComponentAlignment(component, Alignment.MIDDLE_CENTER));
    }

    private void addGenerateButton(String buttonNamePropertyKey, String filePath, String linkPropertyName, String downloadPath, int raportNumber){
        Button generatePickersRaportExcel = new Button(env.getProperty(buttonNamePropertyKey));
        this.addComponent(generatePickersRaportExcel);
        generatePickersRaportExcel.addClickListener(e->{
            File check = new File(filePath);
            if (check.exists()) {
                check.delete();
            }
            List<FruitPicker> fruitPickers = fruitPickerService.findAll();
            switch(raportNumber){
                case 0:{
                    ExcelCreator.getEmployeesExcelRaport(fruitPickers, filePath,fruitTypeService, env);
                    break;
                } case 1:{
                    ExcelCreator.getDeliveriesSummary(filePath, env, fruitDeliveryService, fruitPickerService);
                    break;
                }
            }
            this.addComponent(new Link(env.getProperty(linkPropertyName), new ExternalResource(downloadPath)));
        });
    }


}
