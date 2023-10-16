package controller.product;

import model.product.Product;
import view.core.CellManagement;
import view.core.ManageTableHeader;
import view.core.TableModel;
import view.product.ProductPanel;

import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    final private ProductPanel panel;
    public ProductController(ProductPanel panel){
        this.panel = panel;

    }

    public void MakeTable(){



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
                    titlesList.add("Name");

                    String titles[] = new String[titlesList.size()];
                    for(int i=0; i< titles.length; i++){
                        titles[i] = titlesList.get(i);
                    }

                    List<Product> p =get();
                    String information[][] = new String[p.size()][titlesList.size()];
                    for(int x = 0; x < information.length; x ++){
                        information[x][0] = p.get(x).getName() + " ";
                    }

                    panel.model_ = new TableModel(information,titles);
                    panel.table_.setModel(panel.model_);

                    panel.table_.getColumnModel().getColumn(0).setCellRenderer(new CellManagement("text"));

                    panel.table_.getTableHeader().setReorderingAllowed(false);
                    panel.table_.setRowHeight(25);
                    panel.table_.setGridColor(new java.awt.Color(0,0,0));
                    panel.table_.getColumnModel().getColumn(0).setPreferredWidth(130);

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

}
