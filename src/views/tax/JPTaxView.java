package views.tax;

import controllers.BaseController;
import controllers.tax.TaxController;
import controllers.tax.TaxController_;
import models.tax.Tax;
import views.JPBaseView;

import javax.swing.*;
import java.awt.*;

public class JPTaxView extends JPBaseView {

    public JPTaxView(){
        super();
        TaxController_ controller = new TaxController_(this);
        this.panelList = new JPTaxList(controller);
        this.panelEditor = new JPTaxEditor(controller);
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);
        this.tabContent.setBackground(Color.WHITE);
        tabContent.add(this.panelEditor, "Action");
        tabContent.add(this.panelList, "List");
        this.add(tabContent, BorderLayout.CENTER);
        controller.loadDataTableTaxAsync("");
    }

}
