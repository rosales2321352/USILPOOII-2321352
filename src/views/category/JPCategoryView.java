package views.category;

import controllers.category.CategoryController;
import views.JPBaseView;
import views.category.partials.JPCategoryEditor;
import views.category.partials.JPCategoryList;

import java.awt.*;

public class JPCategoryView extends JPBaseView {

    public JPCategoryView(){
        super();
        CategoryController controller = new CategoryController(this);
        panelList = new JPCategoryList(controller);
        panelEditor = new JPCategoryEditor(controller);
        tabContent.add(panelList,"List");
        tabContent.add(panelEditor,"Action");
        add(tabContent, BorderLayout.CENTER);
        controller.init();
    }

}
