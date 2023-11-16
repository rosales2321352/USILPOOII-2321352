package views.category.partials;

import controllers.category.CategoryController;
import views.JPBaseEditor;
import views.core.CustomButton;

import javax.swing.*;
import java.awt.*;

public class JPCategoryEditor extends JPBaseEditor {
    private final CategoryController controller;
    public JTextField txtName;
    public JTextField txtColor;
    public JPCategoryEditor(CategoryController controller){
        super();
        this.controller = controller;
        drawControls();
    }
    public void drawControls(){
        makeTitle("Agregar nueva categoría");
        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        JLabel lblName = new JLabel("Nombre:");
        JLabel lblColor = new JLabel("Color Distintivo:");
        txtName = new JTextField();
        txtColor = new JTextField();

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titlePanel)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblName)
                    .addGap(10)
                    .addComponent(txtName,GroupLayout.DEFAULT_SIZE,250,GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblColor)
                    .addGap(10)
                    .addComponent(txtColor,GroupLayout.DEFAULT_SIZE,250,GroupLayout.PREFERRED_SIZE)
                )
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(titlePanel)
            .addGap(30)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblName)
                .addComponent(txtName,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblColor)
                .addComponent(txtColor,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
            )
        );

        add(panelControls, BorderLayout.PAGE_START);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.setBackground(Color.WHITE);

        JButton btnCancel = new CustomButton("Cancelar",new Color(65, 65, 65),Color.WHITE);
        btnCancel.setName("List");
        btnCancel.addActionListener(controller::onClickCancel);

        JButton btnSave = new CustomButton("Guardar",new Color(0,123,255),Color.WHITE);
        btnSave.setName("List");
        btnSave.addActionListener(controller::onClickSave);

        panelButtons.add(btnSave);
        panelButtons.add(btnCancel);
        add(panelButtons,BorderLayout.PAGE_END);

    }

}
