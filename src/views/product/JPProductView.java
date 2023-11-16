package views.product;

import controllers.product.ProductController;
import controllers.product.ProductController_;
import views.JPBaseView;
import views.product.partials.JPProductEditor;
import views.product.partials.JPProductList;

import java.awt.*;

public class JPProductView extends JPBaseView {

    public JPProductView(){
        super();
        ProductController_ controller = new ProductController_(this);
        panelEditor = new JPProductEditor(controller);
        panelList = new JPProductList(controller);
        tabContent.add(panelEditor,"Action");
        tabContent.add(panelList,"List");
        add(tabContent, BorderLayout.CENTER);
        controller.init();
    }

}
