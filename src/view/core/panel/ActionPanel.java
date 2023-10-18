package view.core.panel;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

public class ActionPanel extends JPanel {


    public ActionPanel(){
        //this.setPreferredSize(new DimensionUIResource(100,30));
        this.setBackground(Color.YELLOW);
        JButton button = new JButton("Haz Click");
        button.setName("btnAction");
        button.addActionListener((e) -> {
            System.out.println("Hola");
        });

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.add(button,gbc);



    }

}
