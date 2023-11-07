package views.unity;

import controllers.product.ProductController;
import controllers.unity.UnityController;
import views.product.partials.ProductEditor;
import views.product.partials.ProductList;
import views.unity.partials.UnityEditor;
import views.unity.partials.UnityList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UnityView extends JPanel{

    public UnityController controller;
    public CardLayout cardLayout;
    public JPanel tabContent;
    public UnityList unityList;
    public UnityEditor unityEditor;
    public UnityView(){
        this.setBackground(Color.WHITE);
        this.controller = new UnityController(this);
        this.unityList = new UnityList(this.controller);
        this.unityEditor = new UnityEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);

        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.unityList, "List");
        tabContent.add(this.unityEditor, "Action");

        this.add(tabContent, BorderLayout.CENTER);

        this.controller.renderObjects();
        this.controller.loadDataTableAsync("");
    }


}
