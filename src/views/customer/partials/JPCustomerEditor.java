package views.customer.partials;

import controllers.customer.CustomerController;
import models.combobox.DocumentTypeComboBox;
import models.documents.DocumentType;
import models.typeAffectation.TypeAffectation;
import views.JPBaseEditor;
import views.core.CustomButton;
import views.core.combobox.CustomComboBox;

import javax.swing.*;
import java.awt.*;

public class JPCustomerEditor extends JPBaseEditor {

    private final CustomerController controller;
    public CustomComboBox<DocumentTypeComboBox> cmbTypeDocument;
    public JTextField txtDocument;
    public JTextField txtFullName;
    public JTextField txtAddress;
    public JTextField txtEmail;
    public JTextField txtTelephoneNumber;
    public JTextField txtReference;
    public JPCustomerEditor(CustomerController controller) {
        super();
        this.controller = controller;
        drawControls();
    }

    @Override
    public void drawControls() {
        makeTitle("Agregar Nuevo Cliente");
        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        JLabel lblTypeDocument = new JLabel("Tipo de Documento:");
        JLabel lblDocument = new JLabel("Número de Documento:");
        JLabel lblFullName = new JLabel("Nombres y Apellidos:");
        JLabel lblAddress = new JLabel("Dirección:");
        JLabel lblEmail = new JLabel("Correo:");
        JLabel lblTelephoneNumber = new JLabel("Teléfono:");
        JLabel lblReference = new JLabel("Referencia:");

        cmbTypeDocument = new CustomComboBox<>();
        txtDocument = new JTextField();
        txtFullName = new JTextField();
        txtAddress = new JTextField();
        txtEmail = new JTextField();
        txtTelephoneNumber = new JTextField();
        txtReference = new JTextField();

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titlePanel)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblTypeDocument)
                    .addGap(10)
                    .addComponent(cmbTypeDocument,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblDocument)
                    .addGap(10)
                    .addComponent(txtDocument,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblFullName)
                    .addGap(10)
                    .addComponent(txtFullName,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblAddress)
                    .addGap(10)
                    .addComponent(txtAddress,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblEmail)
                    .addGap(10)
                    .addComponent(txtEmail,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblTelephoneNumber)
                    .addGap(10)
                    .addComponent(txtTelephoneNumber,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblReference)
                    .addGap(10)
                    .addComponent(txtReference,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(titlePanel)
            .addGap(30)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTypeDocument)
                .addComponent(cmbTypeDocument,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblDocument)
                .addComponent(txtDocument,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblFullName)
                .addComponent(txtFullName,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblAddress)
                .addComponent(txtAddress,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblEmail)
                .addComponent(txtEmail,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTelephoneNumber)
                .addComponent(txtTelephoneNumber,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblReference)
                .addComponent(txtReference,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
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
