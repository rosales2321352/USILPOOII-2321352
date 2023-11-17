package views.category.partials;

import controllers.category.CategoryController;
import views.JPBaseAction;
import views.core.panel.IActionPanel;

import javax.swing.*;

public class JPCategoryAction extends JPBaseAction implements IActionPanel {

    protected int id;
    protected CategoryController controller;
    public JPCategoryAction(CategoryController controller){
        super();
        this.controller = controller;
        this.btnEdit.addActionListener((e)->controller.onClickEdit(e,id));
        this.btnDelete.addActionListener((e)->controller.onClickDelete(e,id));
    }

    public JPanel getPanel() {return this;}
    public void setId(Object id){this.id=Integer.parseInt(id.toString());}

}
