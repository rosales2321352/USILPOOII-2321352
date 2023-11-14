package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class JPBaseAction extends JPanel {

    public JButton btnEdit;
    public JButton btnDelete;

    public JPBaseAction(Boolean make){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBackground(Color.WHITE);

        if(make) {
            this.makeEditorButton();
            this.makeDeleteButton();
            this.setImages();
            this.add(this.btnEdit);
            this.add(this.btnDelete);
        }
    }

    public void makeEditorButton(){
        btnEdit = new JButton();
        btnEdit.setName("Action");
        btnEdit.setBackground(Color.WHITE);
        btnEdit.setBorder(BorderFactory.createMatteBorder(2,10,2,10,new Color(255,255,255)));
    }
    public void makeDeleteButton(){
        btnDelete = new JButton();
        btnDelete.setName("Action");
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBorder(BorderFactory.createMatteBorder(2,10,2,10,new Color(255,255,255)));
    }

    public void setImages(){
        try {
            Image edit = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/icons/edit.png")));
            Image delete = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/icons/delete.png")));
            btnEdit.setIcon(new ImageIcon(edit));
            btnDelete.setIcon(new ImageIcon(delete));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
