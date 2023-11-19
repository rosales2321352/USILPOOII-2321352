package views.core;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text){
        super(text);
    }
    public CustomButton(Color Bg, Color Fg){
        super();
        setBackground(Bg);
        setBorder((BorderFactory.createMatteBorder(6,20,6,20,Bg)));
        setForeground(Fg);
    }
    public CustomButton(String text,Color Bg, Color Fg){
        super(text);
        setBackground(Bg);
        setBorder((BorderFactory.createMatteBorder(6,20,6,20,Bg)));
        setForeground(Fg);
    }
    public CustomButton(Color Bg){
        super();
        setBackground(Bg);
        setBorder((BorderFactory.createMatteBorder(6,20,6,20,Bg)));
    }



}
