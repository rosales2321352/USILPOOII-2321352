package views.core;

import views.core.layout.CustomScrollBarUI;

import javax.swing.*;
import java.awt.*;

public class CustomJScrollPane extends JScrollPane {

    public CustomJScrollPane(Component view){
        super(view);
        this.setBackground(Color.WHITE);
        this.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        this.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
    }

}
