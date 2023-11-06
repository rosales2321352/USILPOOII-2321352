package views.category;

import controllers.category.CategoryController;
import controllers.product.ProductController;
import views.category.partials.CategoryEditor;
import views.category.partials.CategoryList;
import views.product.partials.ProductList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CategoryView extends JPanel {
    CategoryController controller;
    public CardLayout cardLayout;
    public JPanel tabContent;
    public CategoryList categoryList;
    public CategoryEditor categoryEditor;
    public CategoryView(){
        this.controller = new CategoryController(this);
        this.categoryList = new CategoryList(this.controller);
        this.categoryEditor = new CategoryEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);

        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.categoryList, "List");
        tabContent.add(this.categoryEditor,"Action");

        this.add(tabContent, BorderLayout.CENTER);
        this.controller.renderObjects();
        this.controller.loadDataTableAsync("");
    }
}
