package view.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeAdminView extends JPanel {
    public EmployeeAdminView(CardLayout cardLayout, JPanel contentPanel) {
        // Panel para las opciones de Employee
        JPanel employeeOptionsPanel = new JPanel();
        employeeOptionsPanel.setLayout(new BoxLayout(employeeOptionsPanel, BoxLayout.Y_AXIS));

        JButton addEmployeeButton = new JButton("Add employer");
        JButton updateEmployeeButton = new JButton("Update employer");
        JButton searchEmployeeButton = new JButton("Search employer");
        JButton deleteEmployeeButton = new JButton("Delete Employer");
        JButton backToMenuButton = new JButton("Back to principal menu");

        // Acción para volver al menú principal
        backToMenuButton.addActionListener(e -> cardLayout.show(contentPanel, "Welcome"));

        // Agregar las acciones necesarias para los otros botones

        // Agregar botones al panel de opciones de Employee
        employeeOptionsPanel.add(addEmployeeButton);
        employeeOptionsPanel.add(updateEmployeeButton);
        employeeOptionsPanel.add(searchEmployeeButton);
        employeeOptionsPanel.add(deleteEmployeeButton);
        employeeOptionsPanel.add(backToMenuButton);

        // Agregar el panel de opciones de Employee al contenido
        contentPanel.add(employeeOptionsPanel, "Employee");
    }
}
