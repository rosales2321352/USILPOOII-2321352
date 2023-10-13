package views.login;

import controllers.login.LoginController;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class LoginView extends JFrame {

    private LoginController controller;
    public JTextField txtUserName;
    public JPasswordField txtPassword;
    public JButton btnLogin;
    public LoginView(){
        SwingUtilities.invokeLater(() -> {
            this.controller = new LoginController(this);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(450, 450);
            setTitle("Inicio de Sesión");
            drawControls();
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }

    public void drawControls(){
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel panelControls = new JPanel();

        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);

        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));

        JLabel lblUserName = new JLabel("Nombre de Usuario");
        txtUserName = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña");
        txtPassword = new JPasswordField();
        btnLogin = new JButton("INGRESAR");
        btnLogin.setBackground(new Color(0,123,255));
        btnLogin.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(controller::onClickBtnLogin);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitle,GroupLayout.Alignment.CENTER)
                                .addComponent(lblUserName)
                                .addComponent(txtUserName,GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(lblPassword)
                                .addComponent(txtPassword,GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(btnLogin,GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblTitle)
                        .addGap(30, 30, 30)
                        .addComponent(lblUserName)
                        .addGap(5, 5, 5)
                        .addComponent(txtUserName,GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addComponent(lblPassword)
                        .addGap(5, 5, 5)
                        .addComponent(txtPassword,GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addComponent(btnLogin,GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panelControls.setLayout(layout);
        add(panelControls);
    }

}
