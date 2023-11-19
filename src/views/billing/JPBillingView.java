package views.billing;

import controllers.billing.BillingController;
import views.JPBaseView;
import views.billing.partials.JPBillingEditor;
import views.billing.partials.JPBillingList;

import java.awt.*;

public class JPBillingView extends JPBaseView {

    public JPBillingView(){
        BillingController controller = new BillingController(this);
        panelList = new JPBillingList(controller);
        panelEditor = new JPBillingEditor(controller);
        tabContent.add(panelList,"List");
        tabContent.add(panelEditor,"Action");
        add(tabContent, BorderLayout.CENTER);
        //controller.init();
    }

}
