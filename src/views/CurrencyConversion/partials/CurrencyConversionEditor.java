package views.CurrencyConversion.partials;

import controllers.CurrencyConversion.CurrencyConversionController;
import controllers.CurrencyConversion.DateLabelFormatter;
import models.Currency.Currency;
import models.combobox.CurrencyComboBox;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import views.core.combobox.CustomComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class CurrencyConversionEditor extends JPanel {

    private final CurrencyConversionController controller;
    public JLabel lblTitle;
    public JButton btnSave;
    public JButton btnCancel;
     public UtilDateModel model;

    public CustomComboBox <CurrencyComboBox> txtcurrency;
    public JDatePickerImpl txtdate;
    public JTextField txtconversion;

    public JTextField txtbuy;

    public JTextField txtsale;



    public CurrencyConversionEditor(CurrencyConversionController controller){
        this.setBackground(Color.WHITE);
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.drawControls();
    }

    public void drawControls(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblTitle = new JLabel("Agregar tipo de cambio");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);

        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        JLabel lblcurrency = new JLabel("Moneda:");
        this.txtcurrency = new CustomComboBox<>();
        //JLabel lblconversion = new JLabel("Tipo de cambio:");
        //this.txtconversion = new JTextField();
        JLabel lbldate = new JLabel("Fecha:");
        Properties p = new Properties();
        p.put("text.year", "año");
        p.put("text.month", "Mes");
        p.put("text.today", "Hoy");
         model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
         txtdate = new JDatePickerImpl(datePanel,new DateLabelFormatter());
        JLabel lblbuy = new JLabel("Venta:");
        this.txtbuy = new JTextField();
        JLabel lblisale = new JLabel("Compra:");
        this.txtsale   = new JTextField();

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(titlePanel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblcurrency)
                                        .addGap(10)
                                        .addComponent(txtcurrency, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbldate)
                                        .addGap(10)
                                        .addComponent(txtdate, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblbuy)
                                        .addGap(10)
                                        .addComponent(txtbuy, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblisale)
                                        .addGap(10)
                                        .addComponent(txtsale, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titlePanel)
                        .addGap(30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lblcurrency)
                                .addComponent(txtcurrency, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)


                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lbldate)
                                .addComponent(txtdate, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lblbuy)
                                .addComponent(txtbuy, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lblisale)
                                .addComponent(txtsale, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)

        );


        this.add(panelControls, BorderLayout.PAGE_START);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.setBackground(Color.WHITE);

        this.btnCancel = new JButton("Cancelar");
        btnCancel.setBackground(new Color(65, 65, 65));
        btnCancel.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(65, 65, 65))));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setName("List");
        btnCancel.addActionListener(controller::onClickBtnCancel);

        this.btnSave = new JButton("Guardar");
        btnSave.setBackground(new Color(0,123,255));
        btnSave.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnSave.setForeground(Color.WHITE);
        btnSave.setName("List");
        btnSave.addActionListener(controller::onClickBtnSave);

        panelButtons.add(btnSave);
        panelButtons.add(btnCancel);

        this.add(panelButtons, BorderLayout.PAGE_END);
    }
}
