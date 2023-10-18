package view.product;

import controller.product.ProductController;
import view.core.table.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.IOException;

public class ProductPanel extends JPanel {
    ProductController p = null;
    public TableModel model = null;
    public JTable table = null;
    public ProductPanel(){
        this.setLayout(new GridBagLayout());
        p = new ProductController(this);
        this.DrawControls();
        p.loadDataAsync();
    }

    public void DrawControls() {
        this.table = new JTable();
        JScrollPane listScrollPane = new JScrollPane(table);
        listScrollPane.setPreferredSize(new Dimension(500, 500));
        JButton button = new JButton();
        try {
            Image i = ImageIO.read(getClass().getResource("/res/icons/ss.png"));
            button.setIcon(new ImageIcon(i));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JTextField txtBuscador = new JTextField();
        txtBuscador.setPreferredSize(new Dimension(200, 30));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(button, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(txtBuscador, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(listScrollPane, gbc);
    }

    public void makeTable(Object[][] information, String[] titles){
        model = new TableModel(information,titles);
        table.setModel(model);
        table.getColumnModel().getColumn(0).setCellRenderer(new CellManagement("text"));
        table.getColumnModel().getColumn(1).setCellRenderer(new CellManagement("text"));
       // table.getColumnModel().getColumn(2).setCellRenderer(new CellManagement("actions"));

        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);
        table.setGridColor(new java.awt.Color(0,0,0));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);


        ButtonColumn buttonColumn = new ButtonColumn(table,2);

        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new ManageTableHeader());
        table.setTableHeader(jTableHeader);

        if(1==1){

            String s = "HOla";
        }
    }

}
