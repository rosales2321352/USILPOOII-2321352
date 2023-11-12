package views.person.partials;

import controllers.person.PersonController;

import views.core.layout.CustomScrollBarUI;
import views.core.table.ManageTableHeader;
import views.core.table.TableModel;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class PersonList extends  JPanel{
    public TableModel model;
    public JTable table;
    public JButton btnSearch;
    public JTextField txtQuery;
    PersonController controller;

    public PersonList(PersonController controller){
        this.setBackground(Color.WHITE);
        this.controller = controller;
        this.setLayout(new GridBagLayout());
        drawControls();
    }

    public void drawControls() {
        this.table = new JTable();
        JScrollPane listScrollPane = new JScrollPane(table);
        listScrollPane.setBackground(Color.WHITE);
        listScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        listScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());


        JPanel panelOptions = new JPanel();
        panelOptions.setBackground(Color.WHITE);
        panelOptions.setLayout(new BorderLayout());
        JLabel area = new JLabel();
        area.setText("Listado de Personas");
        area.setFont(new Font("Arial", Font.BOLD,24));
        panelOptions.add(area,BorderLayout.LINE_START);


        JPanel flowPanel = new JPanel();
        flowPanel.setBackground(Color.WHITE);
        flowPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelOptions.add(flowPanel,BorderLayout.LINE_END);

        txtQuery = new JTextField();
        txtQuery.setPreferredSize(new Dimension(250, 30));
        flowPanel.add(txtQuery);

        btnSearch = new JButton("Buscar");
        btnSearch.setBackground(new Color(0,123,255));
        btnSearch.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnSearch.setForeground(Color.WHITE);
        //btnSearch.addActionListener(controller::onClickSearchProduct);
        flowPanel.add(btnSearch);

        JButton addNew = new JButton("Nuevo");
        addNew.setName("Action");
        addNew.setBackground(new Color(0,123,255));
        addNew.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        addNew.setForeground(Color.WHITE);
        //addNew.addActionListener(controller::onClickBtnNew);
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

    }
}
