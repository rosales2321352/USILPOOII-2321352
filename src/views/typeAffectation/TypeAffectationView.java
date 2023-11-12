package views.typeAffectation;

import controllers.typeAffectation.TypeAffectationController;
import controllers.unity.UnityController;
import views.typeAffectation.partials.TypeAffectationEditor;
import views.typeAffectation.partials.TypeAffectationList;
import views.unity.partials.UnityEditor;
import views.unity.partials.UnityList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TypeAffectationView extends JPanel {

    public CardLayout cardLayout;
    public JPanel tabContent;
    public TypeAffectationController controller;
    public TypeAffectationList typeAffectationList;
    public TypeAffectationEditor typeAffectationEditor;
    public TypeAffectationView(){
        this.setBackground(Color.WHITE);
        this.controller = new TypeAffectationController(this);
        this.typeAffectationList = new TypeAffectationList(this.controller);
        this.typeAffectationEditor = new TypeAffectationEditor(this.controller);
        //this.unityEditor = new UnityEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);
        this.tabContent.setBackground(Color.WHITE);
        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.typeAffectationList, "List");
        tabContent.add(this.typeAffectationEditor, "Action");

        this.add(tabContent, BorderLayout.CENTER);

        this.controller.renderObjects();
        this.controller.loadDataTableAsync("");
    }

}
