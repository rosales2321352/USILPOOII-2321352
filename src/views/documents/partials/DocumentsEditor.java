package views.documents.partials;

import controllers.Documents.DocumentsController;

import javax.swing.*;
import java.awt.*;

public class DocumentsEditor extends JPanel {

    private final DocumentsController controller;
    public JLabel lblTitle;
    public JButton btnSave;
    public JButton btnCancel;
    public JTextField txtName;
    public JTextField txtId;

    public DocumentsEditor(DocumentsController controller){
        this.setBackground(Color.WHITE);
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.drawControls();
    }

    public void drawControls(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblTitle = new JLabel("Agregar Documento");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);

        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        JLabel lblName = new JLabel("Nombre:");
        this.txtName = new JTextField();
        JLabel lblId = new JLabel("Código SUNAT:");
        this.txtId = new JTextField();

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(titlePanel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblId)
                                        .addGap(10)
                                        .addComponent(txtId, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblName)
                                        .addGap(10)
                                        .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titlePanel)
                        .addGap(30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lblId)
                                .addComponent(txtId, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lblName)
                                .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
        );

        this.add(panelControls, BorderLayout.PAGE_START);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.setBackground(Color.WHITE);

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

        this.add(panelButtons, BorderLayout.PAGE_END);
    }
}
