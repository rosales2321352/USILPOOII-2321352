package views.category.partials;

import controllers.category.CategoryController;
import views.JPBaseList;
import views.core.CustomButton;
import views.core.CustomJScrollPane;

import javax.swing.*;
import java.awt.*;

public class JPCategoryList extends JPBaseList {
    public JTextField txtQuery;
    private final CategoryController controller;
    public JPCategoryList(CategoryController controller){
        super();
        this.controller=controller;
        this.setLayout(new BorderLayout());
        this.drawControls();
    }
    public void drawControls(){
        table = new JTable();
        table.setBackground(Color.WHITE);
        JScrollPane listScrollPane = new CustomJScrollPane(table);
        makeTitle("Listado de Categorías");

        JPanel panelOptions = new JPanel();
        GroupLayout layout = new GroupLayout(panelOptions);
        panelOptions.setLayout(layout);
        panelOptions.setBackground(Color.WHITE);

        txtQuery = new JTextField();
        txtQuery.setPreferredSize(new Dimension(250, 30));

        JButton btnSearch = new CustomButton("Buscar",new Color(0,123,255),Color.WHITE);
        btnSearch.addActionListener(controller::onClickSearch);

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
