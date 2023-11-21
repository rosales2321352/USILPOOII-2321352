package views.person.partials;

import controllers.person.PersonController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PersonEditor extends JPanel {

    private int id_employee;
    private PersonController controller;


    //public CustomComboBox<DocumentType> cmbDocumentType;
    public JTextField txtName;
    public JTextField txtDocument;
    public JTextField txtAddress;
    public JTextField txtEmail;
    public JTextField txtTelephone;
    public JTextField txtReferences;

    public PersonEditor(PersonController controller){
        this.setBackground(Color.WHITE);
        this.controller = controller;
        this.drawControlls();
        this.eventFocus();
    }

    public void drawControlls(){

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Editar Empleado");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);

        this.add(titlePanel,BorderLayout.PAGE_START);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBackground(Color.WHITE);
        JLabel lblDocument = new JLabel("Documento");
        JLabel lblName = new JLabel("Nombre");
        JLabel lbladdress = new JLabel("Direccion");
        JLabel lblemail = new JLabel("Correo");
        JLabel lbltelephone = new JLabel("Telefono");
        JLabel lblreference = new JLabel("Referencias");

        txtDocument = new JTextField();
        txtDocument.setPreferredSize(new Dimension(200,30));
        txtDocument.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(200,30));
        txtName.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        txtAddress = new JTextField();
        txtAddress.setPreferredSize(new Dimension(200,30));
        txtAddress.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(200,30));
        txtEmail.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        txtTelephone = new JTextField();
        txtTelephone.setPreferredSize(new Dimension(200,30));
        txtTelephone.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        txtReferences = new JTextField();
        txtReferences.setPreferredSize(new Dimension(200,30));
        txtReferences.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        GroupLayout layout = new GroupLayout(controlsPanel);
        controlsPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblDocument)
                                                .addComponent(txtDocument)
                                                .addComponent(lblName)
                                                .addComponent(txtName)
                                        )
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbladdress)
                                                .addComponent(txtAddress)
                                                .addComponent(lblemail)
                                                .addComponent(txtEmail)
                                        )
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbltelephone)
                                                .addComponent(txtTelephone)
                                                .addComponent(lblreference)
                                                .addComponent(txtReferences)
                                        )
                                )
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDocument)
                                .addComponent(txtDocument)
                                .addComponent(lblName)
                                .addComponent(txtName)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbladdress)
                                .addComponent(txtAddress)
                                .addComponent(lblemail)
                                .addComponent(txtEmail)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbltelephone)
                                .addComponent(txtTelephone)
                                .addComponent(lblreference)
                                .addComponent(txtReferences)
                        )

        );

        this.add(controlsPanel,BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        JButton btnSave = new JButton("Guardar");
        btnSave.setBackground(new Color(0,123,255));
        btnSave.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnSave.setForeground(Color.WHITE);
        btnSave.setName("List");
        btnSave.addActionListener(controller::onClickSaveAction);
        buttonsPanel.add(btnSave,FlowLayout.LEFT);

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setBackground(new Color(146, 154, 167));
        btnCancel.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(146, 154, 167))));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setName("List");
        btnCancel.addActionListener(controller::onClickCancelAction);
        buttonsPanel.add(btnCancel,FlowLayout.LEFT);

        this.add(buttonsPanel,BorderLayout.PAGE_END);

    }
    public void eventFocus(){
        txtName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                if(textField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(
                            null,
                            "El nombre del empleado no puede ser nulo o vacio.",
                            "Atención",
                            JOptionPane.INFORMATION_MESSAGE);
                    textField.requestFocus();
                }
            }
        });
    }
}
