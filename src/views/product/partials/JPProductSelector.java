package views.product.partials;

import controllers.observer.ISubject;
import controllers.product.ProductController;
import controllers.product.ProductSelectorController;
import models.core.MutableObject;
import views.core.CustomButton;
import views.core.CustomJScrollPane;
import views.core.table.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class JPProductSelector extends JPanel {

    public JTable table;
    public TableModel model;
    private ProductSelectorController controller;
    public JTextField txtQuery;
    public JPProductSelector(ISubject mutableObject){
        controller = new ProductSelectorController(this);
        drawControls();
        controller.init(mutableObject);
    }

    public void drawControls(){
        table = new JTable();
        table.setBackground(Color.WHITE);
        JScrollPane listScrollPane = new CustomJScrollPane(table);

        JPanel panelOptions = new JPanel();
        GroupLayout layout = new GroupLayout(panelOptions);
        panelOptions.setLayout(layout);
        panelOptions.setBackground(Color.WHITE);

        txtQuery = new JTextField();
        txtQuery.setPreferredSize(new Dimension(250, 30));

        JButton btnSearch = new CustomButton("Buscar",new Color(0,123,255),Color.WHITE);
        btnSearch.addActionListener(controller::obClickSearch);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtQuery)
                                        .addGap(5, 5, 5)
                                        .addComponent(btnSearch)
                                )
                                .addComponent(listScrollPane,GroupLayout.DEFAULT_SIZE,680,GroupLayout.PREFERRED_SIZE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtQuery)
                                .addComponent(btnSearch)
                        )
                        .addComponent(listScrollPane)
        );
        this.add(panelOptions,BorderLayout.PAGE_START);

    }

    public void makeTable(Object[][] information, String[] titles){
        model = new TableModel(information,titles);
        table.setModel(model);
        for(int i=0;i < table.getColumnCount() - 1;i++){
            table.getColumnModel().getColumn(i).setCellRenderer(new ManageGeneralCells("text"));
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);
        table.setGridColor(new java.awt.Color(216,216,216));

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setMaxWidth(60);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setMaxWidth(80);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setMaxWidth(40);
        table.getColumnModel().getColumn(3).setResizable(false);
/*        new ManageCellsActionButtons(table, 5, new JPProductAction(this), new JPProductAction(this));
        table.getColumnModel().getColumn(5).setMaxWidth(100);
        table.getColumnModel().getColumn(5).setMinWidth(100);*/

        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new ManageTableHeader());
        table.setTableHeader(jTableHeader);

    }
}
