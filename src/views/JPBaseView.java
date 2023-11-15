package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JPBaseView extends JPanel {
    public JPBaseList panelList;
    public JPBaseEditor panelEditor;
    public CardLayout cardLayout;
    public JPanel tabContent;

    public JPBaseView(){
        this.setBackground(Color.WHITE);
        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);
        this.tabContent.setBackground(Color.WHITE);
    }
}
