package views.Currency.partials;

import views.core.layout.CustomScrollBarUI;
import views.core.table.ManageCellsActionButtons;
import views.core.table.ManageGeneralCells;
import views.core.table.ManageTableHeader;
import views.core.table.TableModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CurrencyList extends JPanel {

    private final controllers.Currency.CurrencyController controller;
    public JTable table;
    public TableModel model;
    public JTextField txtQuery;

    public CurrencyList(controllers.Currency.CurrencyController controller){
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.controller = controller;
        this.drawControls();
    }

    public void drawControls(){
        this.table = new JTable();
        JScrollPane listScrollPane = new JScrollPane(table);
        listScrollPane.setBackground(Color.WHITE);
        listScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        listScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Listado de Monedas");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);

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
        btnSearch.addActionListener(controller::onClickBtnSearch);

        JButton btnNew = new JButton("Nuevo");
        btnNew.setName("Action");
        btnNew.setBackground(new Color(0,123,255));
        btnNew.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnNew.setForeground(Color.WHITE);
        btnNew.addActionListener(controller::onClickBtnNew);

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

        this.add(panelOptions, BorderLayout.PAGE_START);
        this.add(listScrollPane);
    }

    public void makeTableHeader(String[] titles){
        Object[][] objects = new Object[25][];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new Object[]{""};
        }
        model = new TableModel(objects, titles);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new ManageTableHeader());
        table.setTableHeader(jTableHeader);
    }

    public void makeTable(Object[][] information, String[] titles){
        model = new TableModel(information, titles);
        table.setModel(model);
        for(int i=0; i < table.getColumnCount() - 1; i++){
            table.getColumnModel().getColumn(i).setCellRenderer(new ManageGeneralCells("text"));
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);
        table.setGridColor(new java.awt.Color(216, 216, 216));
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setMaxWidth(50);

        ManageCellsActionButtons actionsButtons =
                new ManageCellsActionButtons(table,
                        3,
                        new views.Currency.partials.CurrencyAction(this.controller),
                        new views.Currency.partials.CurrencyAction(this.controller));

        table.getColumnModel().getColumn(3).setMaxWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(100);

        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new ManageTableHeader());
        table.setTableHeader(jTableHeader);
    }
}
