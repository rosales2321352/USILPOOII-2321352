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
    final String[] COLUMN_NAMES = { "Id", "Name", "Action"};
    public ProductController(ProductPanel panel){
        this.panel = panel;
        this.initEvents();
    }


    public void loadDataAsync() {
        SwingWorker<List<Product>, Void> worker = new SwingWorker<List<Product>, Void>() {
            @Override
            protected List<Product> doInBackground() {
                return new Product().getListProducts();
            }
            @Override
            protected void done() {
                try {
                    List<Product> p =get();
                    Object[][] information = p.stream()
                            .map(product -> new Object[]{
                                    String.valueOf(product.getProductId()),
                                    product.getName(),
                            })
                            .toArray(Object[][]::new);

                    panel.makeTable(information,COLUMN_NAMES);
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
