package views.tax;

import controllers.tax.TaxController_;
import views.JPBaseAction;
import views.core.panel.IActionPanel;

import javax.swing.*;

public class JPTaxAction extends JPBaseAction implements IActionPanel {

    protected int id;
    protected TaxController_ controller;
    public JPTaxAction(TaxController_ controller){
        super(true);
        this.controller = controller;
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
