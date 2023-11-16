package views.Currency.partials;

import controllers.Currency.CurrencyController;
import views.core.panel.IActionPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Currency;
import java.util.Objects;

public class CurrencyAction extends JPanel implements IActionPanel {

    public CurrencyController controller;
    private String name;
    public JButton btnEdit;
    public JButton btnDelete;

    public CurrencyAction(CurrencyController controller){

        this.controller = controller;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBackground(Color.WHITE);

        btnEdit = new JButton();
        btnEdit.setName("Action");
        btnEdit.setBackground(Color.WHITE);
        btnEdit.setBorder(BorderFactory.createMatteBorder(2,10,2,10,new Color(255,255,255)));

        btnDelete = new JButton();
        btnDelete.setName("Action");
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBorder(BorderFactory.createMatteBorder(2,10,2,10,new Color(255,255,255)));

        try {
            Image edit = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/icons/edit.png")));
            Image delete = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/icons/delete.png")));
            btnEdit.setIcon(new ImageIcon(edit));
            btnDelete.setIcon(new ImageIcon(delete));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       /* btnEdit.addActionListener((e) -> {
            controller.onClickBtnEdit(e,name);
        });
        btnDelete.addActionListener((e) -> {
            controller.onClickBtnDelete(e,name);
        });*/

        this.add(btnEdit);
        this.add(btnDelete);
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void setId(Object id) {
        this.name= id. toString();

    }

    /*@Override
    public void setId(int id) {
        this.documents_id = id;
    }*/
}
