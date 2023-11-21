package views.user.partials;

import controllers.user.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UserEditor extends JPanel {
    private int user_id;
    private UserController controller;
    //public CustomComboBox<Rol> cmbRol;
    public JTextField txtUsername;
    public JTextField txtPassword;

    public UserEditor(UserController controller){
        this.setBackground(Color.WHITE);
        this.controller = controller;
        this.drawControlls();
        this.eventFocus();
    }

    public void drawControlls() {

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Editar Usuario");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(lblTitle);

        this.add(titlePanel, BorderLayout.PAGE_START);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBackground(Color.WHITE);
        JLabel lblUsername = new JLabel("Usuario");
        JLabel lblPassword = new JLabel("Contraseña");

        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(200,30));
        txtUsername.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        txtPassword = new JTextField();
        txtPassword.setPreferredSize(new Dimension(200,30));
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        GroupLayout layout = new GroupLayout(controlsPanel);
        controlsPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblUsername)
                                                .addComponent(txtUsername)
                                                .addComponent(lblPassword)
                                                .addComponent(txtPassword)
                                        )
                                )

                        )
        );


        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUsername)
                                .addComponent(txtUsername)
                                .addComponent(lblPassword)
                                .addComponent(txtPassword)
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
        txtUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                if(textField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(
                            null,
                            "El nombre del producto no puede ser nulo o vacio.",
                            "Atención",
                            JOptionPane.INFORMATION_MESSAGE);
                    textField.requestFocus();
                }
            }
        });
    }
}
