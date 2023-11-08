package views.typeAffectation;

import controllers.typeAffectation.TypeAffectationController;
import controllers.unity.UnityController;
import views.unity.partials.UnityEditor;
import views.unity.partials.UnityList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TypeAffectationView extends JPanel {

    public CardLayout cardLayout;
    public JPanel tabContent;
    public TypeAffectationController controller;
    public TypeAffectationView(){
        this.setBackground(Color.WHITE);
        this.controller = new TypeAffectationController(this);
        //this.unityList = new UnityList(this.controller);
        //this.unityEditor = new UnityEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);
        this.tabContent.setBackground(Color.WHITE);
        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        /*tabContent.add(this.unityList, "List");
        tabContent.add(this.unityEditor, "Action");*/

        this.add(tabContent, BorderLayout.CENTER);

        /*this.controller.renderObjects();
        this.controller.loadDataTableAsync("");*/
    }

}
