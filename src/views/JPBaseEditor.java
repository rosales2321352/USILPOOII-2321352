package views;

import views.core.CustomButton;

import javax.swing.*;
import java.awt.*;

public abstract class JPBaseEditor extends JPanel {
    public JLabel lblTitle;
    public JPanel titlePanel;

    public JPBaseEditor(){
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
    }
    public void makeTitle(String title){
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);
    }

    public abstract void drawControls();


}
