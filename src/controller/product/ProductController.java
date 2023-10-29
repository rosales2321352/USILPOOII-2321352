package controller.product;

import model.product.Product;
import view.product.ProductPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class ProductController {
    final private ProductPanel panel;
    final String[] COLUMN_NAMES = { "Id", "SUNAT", "Referencia", "TA", "Descripción", "Aciones"};
    public ProductController(ProductPanel panel){
        this.panel = panel;
        this.initEvents();
    }


    public void loadDataAsync() {
        SwingWorker<List<Product>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Product> doInBackground() {
                return new Product().getListProducts();
            }

            @Override
            protected void done() {
                try {
                    List<Product> p = get();
                    Object[][] information = p.stream()
                            .map(product -> new Object[]{
                                    String.valueOf(product.getProductId()),
                                    product.getProductIdSunat(),
                                    product.getReference(),
                                    product.getTypeAffectationId(),
                                    product.getName()
                            })
                            .toArray(Object[][]::new);

                    panel.makeTable(information, COLUMN_NAMES);
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
