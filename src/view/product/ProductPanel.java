package view.product;

import controller.product.ProductController;
import view.core.table.TableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductPanel extends JPanel {
    public DefaultTableModel model = null;
    public TableModel model_ = null;
    public JTable table_ = null;
    JTable table = null;
    public ProductPanel(){
        /*this.setPreferredSize(new DimensionUIResource(768,576));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setFocusable(true);*/
        this.setLayout(new GridBagLayout());
        ProductController p = new ProductController(this);
        this.table_ = new JTable();
        this.DrawControls();
        p.loadDataAsync();

        //

    }

    public void DrawControls() {
        JScrollPane listScrollPane = new JScrollPane(table_);
        listScrollPane.setPreferredSize(new Dimension(500, 500));
        JButton button = new JButton("Haz Click");
        button.setName("btnBuscar");

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

}
