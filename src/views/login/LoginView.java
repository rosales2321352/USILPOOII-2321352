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
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(450, 450);
            this.setTitle("Inicio de Sesión");
            this.drawControls();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        });
    }

    public void drawControls(){
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel panelControls = new JPanel();

        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);

        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));

        JLabel lblUserName = new JLabel("Nombre de Usuario");
        this.txtUserName = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña");
        this.txtPassword = new JPasswordField();
        this.btnLogin = new JButton("INGRESAR");
        this.btnLogin.setBackground(new Color(0,123,255));
        this.btnLogin.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        this.btnLogin.setForeground(Color.WHITE);
        this.btnLogin.addActionListener(controller::onClickBtnLogin);

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
        this.add(panelControls);
    }

}
