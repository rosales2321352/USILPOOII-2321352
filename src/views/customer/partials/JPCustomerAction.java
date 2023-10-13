package views.customer.partials;

import controllers.customer.CustomerController;
import views.JPBaseAction;
import views.core.panel.IActionPanel;

import javax.swing.*;

public class JPCustomerAction extends JPBaseAction implements IActionPanel {

    protected int id;
    protected CustomerController controller;

    public JPCustomerAction(CustomerController controller){
        super();
        this.controller = controller;
        btnEdit.addActionListener((e) -> controller.onClickEdit(e,id));
        btnDelete.addActionListener((e) -> controller.onClickDelete(e,id));
    }


    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void setId(Object id) {
        this.id = Integer.parseInt(id.toString());
    }
}
