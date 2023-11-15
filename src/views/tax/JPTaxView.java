package views.tax;

import controllers.tax.TaxController;
import views.JPBaseView;
import views.tax.partials.JPTaxEditor;
import views.tax.partials.JPTaxList;

import javax.swing.*;
import java.awt.*;

public class JPTaxView extends JPBaseView {

    public JPTaxView(){
        super();
        TaxController controller = new TaxController(this);
        panelList = new JPTaxList(controller);
        panelEditor = new JPTaxEditor(controller);
        tabContent.add(panelList, "List");
        tabContent.add(panelEditor, "Action");
        add(tabContent, BorderLayout.CENTER);
        controller.init();
    }

}
