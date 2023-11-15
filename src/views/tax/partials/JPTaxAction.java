package views.tax.partials;

import controllers.tax.TaxController;
import views.JPBaseAction;
import views.core.panel.IActionPanel;

import javax.swing.*;

public class JPTaxAction extends JPBaseAction implements IActionPanel {

    protected int id;
    protected TaxController controller;
    public JPTaxAction(TaxController controller){
        super();
        this.controller = controller;
        this.btnEdit.addActionListener((e) -> controller.onClickEdit(e,id));
        this.btnDelete.addActionListener((e) -> controller.onClickDelete(e,id));
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
