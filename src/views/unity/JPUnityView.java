package views.unity;

import controllers.unity.UnityController;
import views.JPBaseView;
import views.unity.partials.JPUnityEditor;
import views.unity.partials.JPUnityList;

import java.awt.*;

public class JPUnityView extends JPBaseView {
    public JPUnityView(){
        super();
        UnityController controller = new UnityController(this);
        panelList = new JPUnityList(controller);
        panelEditor = new JPUnityEditor(controller);
        tabContent.add(panelList,"List");
        tabContent.add(panelEditor,"Action");
        add(tabContent, BorderLayout.CENTER);
        controller.init();
    }
}
