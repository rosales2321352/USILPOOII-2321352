package views.core;

import views.core.layout.CustomScrollBarUI;

import javax.swing.*;
import java.awt.*;

public class CustomJScrollPane extends JScrollPane {

    public CustomJScrollPane(Component view){
        super(view);
        setBackground(Color.WHITE);
        getVerticalScrollBar().setUI(new CustomScrollBarUI());
        getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        setOpaque(true);
        getViewport().setBackground(Color.WHITE);
    }

}
