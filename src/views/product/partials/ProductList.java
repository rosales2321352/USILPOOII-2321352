package views.product.partials;

import controllers.product.ProductController;
import views.core.layout.CustomScrollBarUI;
import views.core.table.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ProductList extends JPanel {

    public TableModel model;
    public JTable table;
    public JButton btnSearch;
    public JTextField txtQuery;
    ProductController controller;

    public ProductList(ProductController controller){
        this.controller = controller;
        this.setLayout(new GridBagLayout());
        this.DrawControls();
    }

    public void DrawControls() {
        this.table = new JTable();
        JScrollPane listScrollPane = new JScrollPane(table);
        listScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        listScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());


        JPanel panelOptions = new JPanel();
        panelOptions.setLayout(new BorderLayout());
        JLabel area = new JLabel();
        area.setText("Listado de Productos");
        area.setFont(new Font("Arial", Font.BOLD,24));
        panelOptions.add(area,BorderLayout.LINE_START);


        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelOptions.add(flowPanel,BorderLayout.LINE_END);

        txtQuery = new JTextField();
        txtQuery.setPreferredSize(new Dimension(250, 30));
        flowPanel.add(txtQuery);

        btnSearch = new JButton("Buscar");
        btnSearch.setBackground(new Color(0,123,255));
        btnSearch.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.addActionListener(controller::onClickSearchProduct);
        flowPanel.add(btnSearch);

        JButton addNew = new JButton("Nuevo");
        addNew.setName("Action");
        addNew.setBackground(new Color(0,123,255));
        addNew.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        addNew.setForeground(Color.WHITE);
        addNew.addActionListener(controller::onClickBtnNew);
        flowPanel.add(addNew);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(panelOptions, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(listScrollPane, gbc);
    }

    public void makeTableHeader(String[] titles){
        Object[][] objects = new Object[25][];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new Object[]{""};
        }
        model = new TableModel(objects,titles);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new ManageTableHeader());
        table.setTableHeader(jTableHeader);
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

        ManageCellsActionButtons actionsButtons =
                new ManageCellsActionButtons(table,
                        5,
                        new ProductAction(this.controller),
                        new ProductAction(this.controller));

        table.getColumnModel().getColumn(5).setMaxWidth(100);
        table.getColumnModel().getColumn(5).setMinWidth(100);

        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new ManageTableHeader());
        table.setTableHeader(jTableHeader);

    }

}
