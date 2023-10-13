package views.product.partials;

import controllers.product.ProductController;
import views.JPBaseAction;
import views.core.panel.IActionPanel;

import javax.swing.*;

public class JPProductAction extends JPBaseAction implements IActionPanel {

    protected int id;
    protected ProductController controller;

    public JPProductAction(ProductController controller){
        super();
        this.controller=controller;
        btnEdit.addActionListener((e)->controller.onClickEdit(e,id));
        btnDelete.addActionListener((e)->controller.onClickDelete(e,id));
    }
    public JPanel getPanel(){return this;}
    public void setId(Object id){ this.id = Integer.parseInt(id.toString());}
}
