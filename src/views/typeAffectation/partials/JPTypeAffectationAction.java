package views.typeAffectation.partials;

import controllers.typeAffectation.TypeAffectationController;
import views.JPBaseAction;
import views.core.panel.IActionPanel;

import javax.swing.*;

public class JPTypeAffectationAction extends JPBaseAction implements IActionPanel {

    protected int id;
    private final TypeAffectationController controller;

    public JPTypeAffectationAction(TypeAffectationController controller){
        super();
        this.controller = controller;
        this.btnEdit.addActionListener((e) -> controller.onClickEdit(e,id));
        this.btnDelete.addActionListener((e) -> controller.onClickDelete(e,id));
    }

    public JPanel getPanel(){return this;}
    public void setId(Object id){this.id = Integer.parseInt(id.toString());}

}
