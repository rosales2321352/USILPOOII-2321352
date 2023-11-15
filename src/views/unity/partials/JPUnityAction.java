package views.unity.partials;

import controllers.unity.UnityController;
import views.JPBaseAction;
import views.core.panel.IActionPanel;

import javax.swing.*;

public class JPUnityAction extends JPBaseAction implements IActionPanel {
    protected int id;
    protected UnityController controller;
    public JPUnityAction(UnityController controller){
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
