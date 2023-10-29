package controllers.product;

import models.ProductSunat;
import models.TypeAffectation;
import models.Unity;
import models.category.Category;
import models.combobox.UnityComboBox;
import models.product.Product;
import views.core.combobox.CustomComboBoxModel;
import views.product.ProductView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.*;

public class ProductController {
    final private ProductView panel;
    final String[] COLUMN_NAMES = { "Id", "SUNAT", "Referencia", "TA", "Descripción", "Aciones"};
    public ProductController(ProductView panel){
        this.panel = panel;
    }

    public void renderObjects(){
        this.panel.productList.makeTableHeader(COLUMN_NAMES);
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

    public Integer addNewProduct(){
        String name = panel.productEditor.txtName.getText();
        Category category = (Category) panel.productEditor.cmbCategory.getSelectedItem();
        TypeAffectation typeAffectation = (TypeAffectation) panel.productEditor.cmbTypeAffectation.getSelectedItem();
        ProductSunat productSunat = (ProductSunat) panel.productEditor.cmbProductSunat.getSelectedItem();
        Unity unity = (Unity) panel.productEditor.cmbUnity.getSelectedItem();
        String reference = panel.productEditor.txtReference.getText();

        Product product = new Product();
        product.setName(name);
        product.setCategoryId((int) category.getId());
        product.setProductIdSunat((String) productSunat.getId());
        product.setType_affectation_id((String) typeAffectation.getId());
        product.setUnityId((int) unity.getUnityId());
        product.setReference(reference);
        product.setOutstanding(0);

        return product.insert();
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
            panel.productEditor.txtName.setText(product.getName());
            panel.productEditor.txtReference.setText(product.getReference());
        }));
        this.switchTab((JButton) e.getSource());
    }

    public void onClickSaveAction(ActionEvent e) {
        Integer rowsAffected = addNewProduct();

        this.switchTab((JButton) e.getSource());
    }
    public void onClickCancelAction(ActionEvent e) { this.switchTab((JButton) e.getSource()); }

    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent,command);
    }


}
