package views.typeAffectation;

import controllers.typeAffectation.TypeAffectationController;
import views.JPBaseView;
import views.typeAffectation.partials.JPTypeAffectationEditor;
import views.typeAffectation.partials.JPTypeAffectationList;

import java.awt.*;

public class JPTypeAffectationView extends JPBaseView {

    public JPTypeAffectationView(){
        super();
        TypeAffectationController controller = new TypeAffectationController(this);
        panelList = new JPTypeAffectationList(controller);
        panelEditor = new JPTypeAffectationEditor(controller);
        tabContent.add(panelList,"List");
        tabContent.add(panelEditor,"Action");
        add(tabContent,BorderLayout.CENTER);
        controller.init();
    }
}
