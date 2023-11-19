package views.customer;

import controllers.customer.CustomerController;
import views.JPBaseView;
import views.customer.partials.JPCustomerEditor;
import views.customer.partials.JPCustomerList;

import java.awt.*;

public class JPCustomerView extends JPBaseView {

    public JPCustomerView(){
        super();
        CustomerController controller = new CustomerController(this);
        panelList = new JPCustomerList(controller);
        panelEditor = new JPCustomerEditor(controller);
        tabContent.add(panelList,"List");
        tabContent.add(panelEditor,"Action");
        add(tabContent, BorderLayout.CENTER);
        controller.init();
    }

}
