package controllers.product;

import models.ProductSunat;
import models.TypeAffectation;
import models.category.Category;
import models.combobox.UnityComboBox;
import models.product.Product;
import views.core.combobox.CustomComboBoxModel;
import views.product.ProductView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.*;

public class ProductController {
    final private ProductView panel;
    final String[] COLUMN_NAMES = { "Id", "SUNAT", "Referencia", "TA", "Descripción", "Aciones"};
    public ProductController(ProductView panel){
        this.panel = panel;
    }

    public void loadDataTableAsync(){
        CompletableFuture<List<Product>> futureTableProduct = CompletableFuture.supplyAsync(new Product()::getListProducts);
        futureTableProduct.thenAcceptAsync(products -> {
            Object[][] information = products.stream()
                    .map(product -> new Object[]{ String.valueOf(product.getProductId()), product.getProductIdSunat(), product.getReference(), product.getTypeAffectationId(), product.getName()})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.productList.makeTable(information,COLUMN_NAMES));
        });
    }
    public void loadDataComboBoxAsync(){
        CompletableFuture<List<Category>> futureCategory = CompletableFuture.supplyAsync(new Category()::getCategoriesList);
        CompletableFuture<List<TypeAffectation>> futureTypeAffectation = CompletableFuture.supplyAsync(new TypeAffectation()::getTypesAffectationList);
        CompletableFuture<List<ProductSunat>> futureProductSunat = CompletableFuture.supplyAsync(new ProductSunat()::getProductSunat);
        CompletableFuture<List<UnityComboBox>> futureUnity = CompletableFuture.supplyAsync(new UnityComboBox()::getUnits_);

        futureCategory.thenAcceptAsync(categories -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<Category> comboBoxModel = new CustomComboBoxModel<>(categories);
            panel.productEditor.cmbCategory.setModel(comboBoxModel);
            //panel.productEditor.cmbCategory.setSelectedIndex(0);
        }));
        futureProductSunat.thenAcceptAsync(productSunats -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<ProductSunat> comboBoxModel = new CustomComboBoxModel<>(productSunats);
            panel.productEditor.cmbProductSunat.setModel(comboBoxModel);
            panel.productEditor.cmbProductSunat.setSelectedIndex(0);
        }));
        futureTypeAffectation.thenAcceptAsync(typeAffectations -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<TypeAffectation> comboBoxModel = new CustomComboBoxModel<>(typeAffectations);
            panel.productEditor.cmbTypeAffectation.setModel(comboBoxModel);
            panel.productEditor.cmbTypeAffectation.setSelectedIndex(0);
        }));
        futureUnity.thenAcceptAsync(unities -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<UnityComboBox> comboBoxModel = new CustomComboBoxModel<>(unities);
            panel.productEditor.cmbUnity.setModel(comboBoxModel);
            panel.productEditor.cmbUnity.setSelectedIndex(0);
        }));
    }

    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }

    public void onClickDeleteAction(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }

    public void onClickEditAction(ActionEvent e, Object id){
        CompletableFuture<Product> futureProduct = CompletableFuture.supplyAsync(() -> new Product().getProduct((int) id));
        futureProduct.thenAcceptAsync(product -> SwingUtilities.invokeLater(() -> {
            panel.productEditor.cmbCategory.setSelectedItemById(product.getCategoryId());
            panel.productEditor.cmbProductSunat.setSelectedItemById(product.getProductIdSunat());
            panel.productEditor.cmbTypeAffectation.setSelectedItemById(product.getTypeAffectationId());
            panel.productEditor.cmbUnity.setSelectedItemById(product.getCategoryId());
        }));
        this.switchTab((JButton) e.getSource());
    }

    public void onClickSaveAction(ActionEvent e) { this.switchTab((JButton) e.getSource()); }
    public void onClickCancelAction(ActionEvent e) { this.switchTab((JButton) e.getSource()); }

    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent,command);
    }


}
