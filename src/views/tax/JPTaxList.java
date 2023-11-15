package views.tax;


import controllers.tax.TaxController;
import controllers.tax.TaxController_;
import views.JPBaseEditor;
import views.JPBaseList;
import views.core.CustomButton;
import views.core.CustomJScrollPane;
import views.core.layout.CustomScrollBarUI;

import javax.swing.*;
import java.awt.*;

public class JPTaxList extends JPBaseList {
    public JTextField txtQuery;
    public JPBaseEditor panelEditor;
    private final TaxController_ controller;
    public JPTaxList(TaxController_ controller){
        super();
        this.controller = controller;

        this.setLayout(new BorderLayout());
        this.drawControls();
    }

    public void drawControls(){
        this.table = new JTable();
        this.table.setBackground(Color.WHITE);
        JScrollPane listScrollPane = new CustomJScrollPane(table);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Listado de Impuestos");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);

        JPanel panelOptions = new JPanel();
        GroupLayout layout = new GroupLayout(panelOptions);
        panelOptions.setLayout(layout);
        panelOptions.setBackground(Color.WHITE);

        txtQuery = new JTextField();
        txtQuery.setPreferredSize(new Dimension(250, 30));

        JButton btnSearch = new CustomButton("Buscar",new Color(0,123,255),Color.WHITE);
        btnSearch.addActionListener(this.controller::onClickSave);

        JButton btnNew = new CustomButton("Nuevo",new Color(0,123,255),Color.WHITE);
        btnNew.setName("Action");
        //btnNew.addActionListener(new NewCommand<>(controller)::execute);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(titlePanel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtQuery)
                                        .addGap(5, 5, 5)
                                        .addComponent(btnSearch)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnNew)
                                )
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titlePanel)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtQuery)
                                .addComponent(btnSearch)
                                .addComponent(btnNew)
                        )
        );
        this.add(panelOptions,BorderLayout.PAGE_START);
        this.add(listScrollPane);
    }



}
