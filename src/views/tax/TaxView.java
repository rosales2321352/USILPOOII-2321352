package views.tax;

import controllers.tax.TaxController;
import controllers.unity.UnityController;
import views.tax.partials.TaxEditor;
import views.tax.partials.TaxList;
import views.unity.partials.UnityEditor;
import views.unity.partials.UnityList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TaxView extends JPanel{
    public TaxController controller;
    public CardLayout cardLayout;
    public JPanel tabContent;
    public TaxList taxList;
    public TaxEditor taxEditor;
    //public UnityEditor unityEditor;
    public TaxView(){
        this.setBackground(Color.WHITE);
        this.controller = new TaxController(this);
        this.taxList = new TaxList(this.controller);
        this.taxEditor = new TaxEditor(this.controller);
        //this.unityEditor = new UnityEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);
        this.tabContent.setBackground(Color.WHITE);
        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.taxList, "List");
        tabContent.add(this.taxEditor, "Action");

        this.add(tabContent, BorderLayout.CENTER);

        this.controller.renderObjects();
        /*this.controller.loadDataTableAsync("");*/
    }
}
