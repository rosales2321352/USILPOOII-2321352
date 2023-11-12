package views.typeAffectation.partials;

import controllers.typeAffectation.TypeAffectationController;
import models.category.Category;
import models.combobox.TaxComboBox;
import views.core.combobox.CustomComboBox;

import javax.swing.*;
import java.awt.*;

public class TypeAffectationEditor extends JPanel {
    private TypeAffectationController controller;
    public JLabel lblTitle;
    public JTextField txtId;
    public JTextField txtName;
    public JButton btnSave;
    public JButton btnCancel;
    public CustomComboBox<TaxComboBox> cmbTax;
    public JCheckBox chkFree;
    public JCheckBox chkOnerous;
    public TypeAffectationEditor(TypeAffectationController controller){
        this.setBackground(Color.WHITE);
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.drawControls();
    }
    public void drawControls(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblTitle = new JLabel("Agregar Tipo de Afectación");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);

        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        JLabel lblId = new JLabel("Id:");
        this.txtId = new JTextField();
        JLabel lblName = new JLabel("Nombre:");
        this.txtName = new JTextField();
        JLabel lblSymbol = new JLabel("Impuesto:");
        this.cmbTax = new CustomComboBox<>();
        JLabel lblFree = new JLabel("Gratuito:");
        this.chkFree = new JCheckBox();
        this.chkFree.setBackground(Color.WHITE);
        JLabel lblOnerous = new JLabel("Oneroso:");
        this.chkOnerous = new JCheckBox();
        this.chkOnerous.setBackground(Color.WHITE);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titlePanel)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50)
                    .addComponent(lblId)
                    .addGap(10)
                    .addComponent(txtId,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addGap(10)
                    .addComponent(lblName)
                    .addGap(10)
                    .addComponent(txtName,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblSymbol)
                    .addGap(10)
                    .addComponent(this.cmbTax,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblFree)
                    .addGap(10)
                    .addComponent(this.chkFree,GroupLayout.DEFAULT_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOnerous)
                    .addGap(10)
                    .addComponent(this.chkOnerous,GroupLayout.DEFAULT_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                )
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(titlePanel)
            .addGap(30)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lblId)
                    .addComponent(txtId,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblName)
                .addComponent(txtName,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblSymbol)
                .addComponent(this.cmbTax,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblFree)
                .addComponent(this.chkFree)
                .addComponent(lblOnerous)
                .addComponent(this.chkOnerous)
            )
        );

        this.add(panelControls,BorderLayout.PAGE_START);

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

        this.add(panelButtons,BorderLayout.PAGE_END);
    }
}
