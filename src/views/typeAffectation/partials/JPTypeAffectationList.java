package views.typeAffectation.partials;

import controllers.typeAffectation.TypeAffectationController;
import views.JPBaseList;
import views.core.CustomButton;
import views.core.CustomJScrollPane;

import javax.swing.*;
import java.awt.*;

public class JPTypeAffectationList extends JPBaseList {

    private final TypeAffectationController controller;
    public JTextField txtQuery;
    public JPTypeAffectationList(TypeAffectationController controller){
        super();
        this.controller = controller;
        setLayout(new BorderLayout());
        drawControls();
    }
    public void drawControls(){
        table = new JTable();
        table.setBackground(Color.WHITE);
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
        btnSearch.addActionListener(controller::onClickBtnSearch);

        JButton btnNew = new CustomButton("Nuevo",new Color(0,123,255),Color.WHITE);
        btnNew.setName("Action");
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
        this.add(panelOptions,BorderLayout.PAGE_START);
        this.add(listScrollPane);
    }
}
