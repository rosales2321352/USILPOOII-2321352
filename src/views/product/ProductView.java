package views.product;

import controllers.product.ProductController;
import views.product.partials.ProductEditor;
import views.product.partials.ProductList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductView extends JPanel {

    ProductController controller = null;
    public CardLayout cardLayout;
    public ProductList productList;
    public ProductEditor productEditor;
    public JPanel tabContent;
    public ProductView(){
        this.controller = new ProductController(this);
        this.productList = new ProductList(this.controller);
        this.productEditor = new ProductEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);

        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.productList, "List");
        tabContent.add(this.productEditor, "Action");

        this.add(tabContent, BorderLayout.CENTER);
        this.controller.renderObjects();
        this.controller.loadDataTableAsync();

    }



}
