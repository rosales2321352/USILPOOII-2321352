package view.login;

import controller.login.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLogin extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JButton btnIngresar;

    public ViewLogin() {
        this.setSize(500, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Inicio de Sesión");
        this.setLocationRelativeTo(null);

        // Panel principal con un BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Panel superior para el logotipo
        JPanel topPanel = new JPanel();
        JLabel logoLabel = new JLabel(new ImageIcon("Logotipo.jpg"));
        topPanel.add(logoLabel);

        // Panel central para campos de inicio de sesión
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        // Agregar un JLabel para el título
        JLabel titleLabel = new JLabel("Sistema de Ventas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));  // Estilo del título
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        centerPanel.add(titleLabel, c);

        JLabel usuarioLabel = new JLabel("Usuario:");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        centerPanel.add(usuarioLabel, c);

        usuarioField = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        centerPanel.add(usuarioField, c);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        c.gridx = 0;
        c.gridy = 2;
        centerPanel.add(contrasenaLabel, c);

        contrasenaField = new JPasswordField(20);
        c.gridx = 1;
        c.gridy = 2;
        centerPanel.add(contrasenaField, c);

        btnIngresar = new JButton("Ingresar");
        c.gridx = 1;
        c.gridy = 3;
        centerPanel.add(btnIngresar, c);

        // Panel inferior para mensajes de error
        JPanel bottomPanel = new JPanel();
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        bottomPanel.add(errorLabel);

        c.gridx = 1;
        c.gridy = 4;
        centerPanel.add(bottomPanel, c);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Controlador: gestionar el evento de inicio de sesión
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                char[] contrasena = contrasenaField.getPassword();
                LoginController controller = new LoginController();
                boolean resultado = controller.iniciarSesion(usuario, String.valueOf(contrasena));
                if (resultado) {
                    errorLabel.setText("Inicio de sesión exitoso.");
                } else {
                    errorLabel.setText("Usuario o contraseña incorrectos.");
                }
                usuarioField.setText("");
                contrasenaField.setText("");
            }
        });

        this.add(panel);
    }


}
