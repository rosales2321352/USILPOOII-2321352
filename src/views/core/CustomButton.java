package views.core;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text){
        super(text);
    }
    public CustomButton(Color Bg, Color Fg){
        super();
        this.setBackground(Bg);
        this.setBorder((BorderFactory.createMatteBorder(6,20,6,20,Bg)));
        this.setForeground(Fg);
    }
    public CustomButton(String text,Color Bg, Color Fg){
        super(text);
        this.setBackground(Bg);
        this.setBorder((BorderFactory.createMatteBorder(6,20,6,20,Bg)));
        this.setForeground(Fg);
    }
    public CustomButton(Color Bg){
        super();
        this.setBackground(Bg);
        this.setBorder((BorderFactory.createMatteBorder(6,20,6,20,Bg)));
    }



}
