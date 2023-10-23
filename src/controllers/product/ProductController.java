package controllers.product;

import models.product.Product;
import views.product.ProductView;
import views.product.partials.ProductList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProductController {
    final private ProductView panel;
    final String[] COLUMN_NAMES = { "Id", "SUNAT", "Referencia", "TA", "Descripción", "Aciones"};
    public ProductController(ProductView panel){
        this.panel = panel;
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

                    panel.productList.makeTable(information, COLUMN_NAMES);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }

    public void onClickDeleteAction(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }

    public void onClickEditAction(ActionEvent e){ this.switchTab((JButton) e.getSource()); }

    public void onClickSaveAction(ActionEvent e) { this.switchTab((JButton) e.getSource()); }
    public void onClickCancelAction(ActionEvent e) { this.switchTab((JButton) e.getSource()); }

    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent,command);
    }

}
