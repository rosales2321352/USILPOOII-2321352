package views.billing.partials;

import controllers.billing.BillingController;
import models.combobox.EmployeeComboBox;
import models.person.Person;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import views.JPBaseEditor;
import views.core.CustomJScrollPane;
import views.core.combobox.CustomComboBox;
import views.core.layout.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Properties;

public class JPBillingEditor extends JPBaseEditor {

    private final BillingController controller;
    public JTextField txtSeries;
    public JTextField txtCorrelative;
    public JTextField txtCustomer;
    public CustomComboBox<EmployeeComboBox> cmbEmployee;
    public JDatePickerImpl dpDateIssue;
    public JDatePickerImpl dpDueDate;
    public JTextField txtGross_amount;
    public JTextField txtDiscount;
    public JTextField txtTotal_pay;

    public JPBillingEditor(BillingController controller){
        super();
        this.controller=controller;
        drawControls();
    }

    @Override
    public void drawControls() {
        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        table = new JTable();
        table.setBackground(Color.WHITE);
        JScrollPane listScrollPane = new CustomJScrollPane(table);

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        txtSeries = new JTextField();
        setMaxLength(txtSeries,4);
        txtCorrelative = new JTextField();
        setMaxLength(txtCorrelative,8);
        txtCustomer = new JTextField();
        cmbEmployee = new CustomComboBox<>();

        UtilDateModel modelDateIssue = new UtilDateModel();
        JDatePanelImpl datePanelDateIssue = new JDatePanelImpl(modelDateIssue,properties);
        dpDateIssue = new JDatePickerImpl(datePanelDateIssue, new DateLabelFormatter());

        UtilDateModel modelDueDate = new UtilDateModel();
        JDatePanelImpl datePanelDueDate = new JDatePanelImpl(modelDueDate,properties);
        dpDueDate = new JDatePickerImpl(datePanelDueDate, new DateLabelFormatter());

        txtGross_amount = new JTextField();
        txtDiscount = new JTextField();
        txtTotal_pay = new JTextField();

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtSeries,GroupLayout.DEFAULT_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCorrelative,GroupLayout.DEFAULT_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                    )
                                    .addComponent(txtCustomer,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbEmployee,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(dpDateIssue,GroupLayout.DEFAULT_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dpDueDate,GroupLayout.DEFAULT_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                    )
                            )
                    )
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(listScrollPane)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGross_amount,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiscount,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal_pay,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(txtSeries,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCorrelative,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbEmployee,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(txtCustomer,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addComponent(dpDateIssue)
                                .addComponent(dpDueDate)
                        )
                        .addGap(15)
                        .addComponent(listScrollPane,GroupLayout.DEFAULT_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addGap(15)
                        .addComponent(txtGross_amount,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDiscount,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotal_pay,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        );

        add(panelControls,BorderLayout.PAGE_START);
    }
}
