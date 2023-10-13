package views.unity.partials;

import controllers.unity.UnityController;
import views.JPBaseList;
import views.core.CustomJScrollPane;

import javax.swing.*;
import java.awt.*;

public class JPUnityList extends JPBaseList {

    public JTextField txtQuery;
    private final UnityController controller;

    public JPUnityList(UnityController controller){
        super();
        this.controller = controller;
        this.drawControls();
    }

    @Override
    public void drawControls() {
        table = new JTable();
        table.setBackground(Color.WHITE);
        JScrollPane listScrollPane = new CustomJScrollPane(table);
        makeTitle("Listado de Unidades de Medida");
        JPanel panelOptions = new JPanel();
        GroupLayout layout = new GroupLayout(panelOptions);
        panelOptions.setLayout(layout);
        panelOptions.setBackground(Color.WHITE);

        txtQuery = new JTextField();
        txtQuery.setPreferredSize(new Dimension(250, 30));

        JButton btnSearch = new JButton("Buscar");
        btnSearch.setBackground(new Color(0,123,255));
        btnSearch.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.addActionListener(controller::onClickSearch);

        JButton btnNew = new JButton("Nuevo");
        btnNew.setName("Action");
        btnNew.setBackground(new Color(0,123,255));
        btnNew.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnNew.setForeground(Color.WHITE);
        btnNew.addActionListener(controller::onClickNew);

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
        add(panelOptions,BorderLayout.PAGE_START);
        add(listScrollPane);
    }
}
