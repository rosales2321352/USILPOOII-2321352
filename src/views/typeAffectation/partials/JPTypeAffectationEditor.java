package views.typeAffectation.partials;

import controllers.typeAffectation.TypeAffectationController;
import models.combobox.TaxComboBox;
import views.JPBaseEditor;
import views.core.CustomButton;
import views.core.combobox.CustomComboBox;

import javax.swing.*;
import java.awt.*;

public class JPTypeAffectationEditor extends JPBaseEditor {

    private final TypeAffectationController controller;
    public JTextField txtId;
    public JTextField txtName;
    public CustomComboBox<TaxComboBox> cmbTax;
    public JCheckBox chkFree;
    public JCheckBox chkOnerous;

    public JPTypeAffectationEditor(TypeAffectationController controller){
        super();
        this.controller = controller;
        drawControls();
    }

    public void drawControls(){
        makeTitle("Agregar nuevo tipo de afectación");
        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        JLabel lblId = new JLabel("Id:");
        txtId = new JTextField();
        JLabel lblName = new JLabel("Nombre:");
        txtName = new JTextField();
        JLabel lblSymbol = new JLabel("Impuesto:");
        cmbTax = new CustomComboBox<>();
        JLabel lblFree = new JLabel("Gratuito:");
        chkFree = new JCheckBox();
        chkFree.setBackground(Color.WHITE);
        JLabel lblOnerous = new JLabel("Oneroso:");
        chkOnerous = new JCheckBox();
        chkOnerous.setBackground(Color.WHITE);

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
                    .addComponent(cmbTax,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblFree)
                    .addGap(10)
                    .addComponent(chkFree,GroupLayout.DEFAULT_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOnerous)
                    .addGap(10)
                    .addComponent(chkOnerous,GroupLayout.DEFAULT_SIZE, 140, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(cmbTax,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblFree)
                .addComponent(chkFree)
                .addComponent(lblOnerous)
                .addComponent(chkOnerous)
            )
        );

        add(panelControls,BorderLayout.PAGE_START);

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

