package view.core.panel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class ActionPanel extends JPanel {


    public ActionPanel(){

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBackground(Color.WHITE);


        JButton boton1 = new JButton();
        boton1.setBackground(Color.WHITE);
        boton1.setBorder(BorderFactory.createMatteBorder(2,10,2,10,new Color(255,255,255)));
        JButton boton2 = new JButton();
        boton2.setBackground(Color.WHITE);
        boton2.setBorder(BorderFactory.createMatteBorder(2,10,2,10,new Color(255,255,255)));

        try {
            Image edit = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/icons/edit.png")));
            Image delete = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/icons/delete.png")));
            boton1.setIcon(new ImageIcon(edit));
            boton2.setIcon(new ImageIcon(delete));
        }catch (Exception e){

        }

        this.add(boton1);
        this.add(boton2);

    }

}
