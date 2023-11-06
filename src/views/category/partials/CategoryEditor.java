package views.category.partials;

import controllers.category.CategoryController;

import javax.swing.*;
import java.awt.*;

public class CategoryEditor extends JPanel {

    private CategoryController controller;
    public JTextField txtName;
    public JTextField txtColor;
    public JButton btnSave;
    public JButton btnCancel;
    public CategoryEditor(CategoryController controller){
        this.setBackground(Color.WHITE);
        this.controller = controller;
        this.drawControls();
    }

    public void drawControls(){

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Editar Categoría");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);

        this.add(titlePanel,BorderLayout.PAGE_START);

        JLabel lblName = new JLabel("Nombre:");
        JLabel lblColor = new JLabel("Color Distintivo:");
        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(200,30));
        txtColor = new JTextField();
        txtColor.setPreferredSize(new Dimension(200,30));

        JPanel panelControls = new JPanel();
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblName)
                        .addGap(66, 66, 66)
                        .addComponent(txtName,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblColor)
                        .addGap(21, 21, 21)
                        .addComponent(txtColor,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addGap(50, 50, 50)
                    .addComponent(lblName)
                    .addComponent(txtName)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColor)
                    .addComponent(txtColor)
                )
        );

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.btnCancel = new JButton("Cancelar");
        btnCancel.setBackground(new Color(65, 65, 65));
        btnCancel.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(65, 65, 65))));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setName("List");
        btnCancel.addActionListener(controller::onClickBtnCancel);

        this.btnSave = new JButton("Guardar");
        btnSave.setBackground(new Color(0,123,255));
        btnSave.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnSave.setForeground(Color.WHITE);
        btnSave.setName("List");
        btnSave.addActionListener(controller::onClickBtnSave);

        panelButtons.add(btnSave);
        panelButtons.add(btnCancel);


        this.add(titlePanel,BorderLayout.PAGE_START);
        this.add(panelControls,BorderLayout.CENTER);
        this.add(panelButtons,BorderLayout.PAGE_END);
    }

}
