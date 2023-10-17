package controller.product;

import model.product.Product;
import view.core.table.CellManagement;
import view.core.table.ManageTableHeader;
import view.core.table.TableModel;
import view.product.ProductPanel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductController {
    final private ProductPanel panel;
    public ProductController(ProductPanel panel){
        this.panel = panel;
        this.initEvents();
    }


    public void loadDataAsync() {
        SwingWorker<List<Product>, Void> worker = new SwingWorker<List<Product>, Void>() {

            @Override
            protected List<Product> doInBackground() {
                List<Product> p = new Product().getListProducts();
                return p;
            }

            @Override
            protected void done() {
                try {
                    ArrayList<String> titlesList = new ArrayList<>();
                    titlesList.add("ID");
                    titlesList.add("Name");

                    String[] titles = titlesList.toArray(String[]::new);

                    List<Product> p =get();
                    String[][] information = p.stream()
                            .map(product -> new String[]{
                                    String.valueOf(product.getProductId()),
                                    product.getName()
                            })
                            .toArray(String[][]::new);

                    panel.model_ = new TableModel(information,titles);
                    panel.table_.setModel(panel.model_);

                    panel.table_.getColumnModel().getColumn(0).setCellRenderer(new CellManagement("text"));
                    panel.table_.getColumnModel().getColumn(1).setCellRenderer(new CellManagement("text"));

                    panel.table_.getTableHeader().setReorderingAllowed(false);
                    panel.table_.setRowHeight(25);
                    panel.table_.setGridColor(new java.awt.Color(0,0,0));
                    panel.table_.getColumnModel().getColumn(0).setPreferredWidth(10);

                    JTableHeader jTableHeader = panel.table_.getTableHeader();
                    jTableHeader.setDefaultRenderer(new ManageTableHeader());
                    panel.table_.setTableHeader(jTableHeader);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    public void initEvents(){


    }


}
