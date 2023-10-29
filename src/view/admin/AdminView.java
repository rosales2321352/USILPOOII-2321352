package view.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public AdminView() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Panel de Administrador");
        this.setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel lateral izquierdo para el menú
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        // Botones para las opciones del menú
        JButton productsButton = createMenuButton("Products");
        JButton salesButton = createMenuButton("Sales");
        JButton employeeButton = createMenuButton("Employee");
        JButton usersButton = createMenuButton("Users");
        JButton rolesButton = createMenuButton("Roles");

        // Panel central para contenido cambiante
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Etiqueta para mostrar "Welcome Administrator"
        JLabel welcomeLabel = new JLabel("Welcome Administrator");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(welcomeLabel, "Welcome");

        // Agregar acciones a los botones del menú
        productsButton.addActionListener(e -> cardLayout.show(contentPanel, "Products"));
        salesButton.addActionListener(e -> cardLayout.show(contentPanel, "Sales"));
        employeeButton.addActionListener(e -> cardLayout.show(contentPanel, "Employee"));
        usersButton.addActionListener(e -> cardLayout.show(contentPanel, "Users"));
        rolesButton.addActionListener(e -> cardLayout.show(contentPanel, "Roles"));

        // Agregar botones al panel lateral izquierdo
        sidePanel.add(productsButton);
        sidePanel.add(salesButton);
        sidePanel.add(employeeButton);
        sidePanel.add(usersButton);
        sidePanel.add(rolesButton);

        // Botón de cierre de sesión
        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //lógica para cerrar sesión
            }
        });

        // Panel para el botón de cierre de sesión
        JPanel logoutPanel = new JPanel();
        logoutPanel.add(logoutButton);

        // Agregar paneles al panel principal
        mainPanel.add(sidePanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    // Método para crear botones de menú con un ActionListener común
    private JButton createMenuButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.addActionListener(e -> cardLayout.show(contentPanel, buttonText));
        return button;
    }




    public Button getProductsButton() {
        return null;
    }
}
