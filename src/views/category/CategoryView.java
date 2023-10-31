package views.category;

import controllers.category.CategoryController;
import controllers.product.ProductController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CategoryView extends JPanel {
    CategoryController controller;
    public CardLayout cardLayout;
    public JPanel tabContent;
    public CategoryView(){
        this.controller = new CategoryController(this);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);

        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);
    }
}
