package views.user;

import controllers.user.UserController;
import views.user.partials.UserEditor;
import views.user.partials.UserList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserView extends JPanel {

    UserController controller;
    public CardLayout cardLayout;
    public UserList userList;
    public UserEditor userEditor;
    public JPanel tabContent;

    public UserView(){
        this.setBackground(Color.WHITE);
        this.controller = new UserController(this);
        this.userList = new UserList(this.controller);
        this.userEditor = new UserEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);

        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.userList,"List");
        tabContent.add(this.userEditor,"Action");

        this.add(tabContent,BorderLayout.CENTER);
        this.controller.renderObjects();
        this.controller.loadDataTableAsync("");
    }
}
