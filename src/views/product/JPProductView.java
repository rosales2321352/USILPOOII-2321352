package views.product;

import controllers.product.ProductController;
import views.JPBaseView;
import views.product.partials.JPProductEditor;
import views.product.partials.JPProductList;

import java.awt.*;

public class JPProductView extends JPBaseView {

    public JPProductView(){
        super();
        ProductController controller = new ProductController(this);
        panelEditor = new JPProductEditor(controller);
        panelList = new JPProductList(controller);
        tabContent.add(panelList,"List");
        tabContent.add(panelEditor,"Action");
        add(tabContent, BorderLayout.CENTER);
        controller.init();
    }

}
