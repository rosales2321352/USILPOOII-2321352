package controllers.product;

import controllers.observer.IMutableObject;
import controllers.observer.ISubject;
import controllers.strategy.table.classes.ProductTableLoadStrategy;
import models.product.Product;
import views.product.partials.JPProductSelector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProductSelectorController {

    private JPProductSelector productSelector;
    private String[] COLUMN_NAMES = new String[]{ "Id", "SUNAT", "Referencia", "TA", "Descripción"};
    public ProductSelectorController(JPProductSelector productSelector){
        this.productSelector = productSelector;
    }

    public void init(ISubject mutableObject){
        loadDataTableAsync("");
        productSelector.table.getSelectionModel().addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = productSelector.table.getSelectedRow();
                try {
                    ((IMutableObject) mutableObject).setValue(Integer.parseInt(productSelector.table.getValueAt(selectedRow, 0).toString()));
                }catch (Exception ignored){

                }
            }
        });
    }

    public void loadDataTableAsync(String query){
        var tableLoadStrategy = new ProductTableLoadStrategy();
        CompletableFuture<List<Product>> futureProduct = CompletableFuture.supplyAsync(() -> new Product().getListProducts(query    ));
        futureProduct.thenAcceptAsync(dataList -> {
            Object[][] information = tableLoadStrategy.loadTableData(dataList);
            SwingUtilities.invokeLater(() -> productSelector.makeTable(information,COLUMN_NAMES));
        });

    }

    public void obClickSearch(ActionEvent e){
        String query = productSelector.txtQuery.getText();
        if(query != null){
            loadDataTableAsync(query);
        }
    }

}
