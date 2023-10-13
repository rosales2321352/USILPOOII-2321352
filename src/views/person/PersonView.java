package views.person;

import controllers.person.PersonController;
import views.person.partials.PersonEditor;
import views.person.partials.PersonList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PersonView extends JPanel {

    PersonController controller;
    public CardLayout cardLayout;
    public PersonList personList;
    public PersonEditor personEditor;
    public JPanel tabContent;

    public PersonView(){
        this.setBackground(Color.WHITE);
        this.controller = new PersonController(this);
        this.personList = new PersonList(this.controller);
        //this.personEditor = new PersonEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);

        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.personList,"List");
        //tabContent.add(this.personEditor,"Action");

        this.add(tabContent, BorderLayout.CENTER);
        this.controller.renderObjects();
        this.controller.loadDataTableAsync("");
    }
}
