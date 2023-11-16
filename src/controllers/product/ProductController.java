package controllers.product;

import models.ProductSunat;
import models.typeAffectation.TypeAffectation;
import models.unity.Unity;
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
    private int product_id=0;
    final String[] COLUMN_NAMES = { "Id", "SUNAT", "Referencia", "TA", "Descripción", "Aciones"};
    public ProductController(ProductView panel){
        this.panel = panel;
    }

    public void renderObjects(){
        this.panel.productList.makeTableHeader(COLUMN_NAMES);
    }

    public void loadDataTableAsync(String query){
        CompletableFuture<List<Product>> futureTableProduct = CompletableFuture.supplyAsync(() -> {
            return new Product().getListProducts(query);
        });
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

    public Integer saveProduct(){
        String name = panel.productEditor.txtName.getText();
        Category category = (Category) panel.productEditor.cmbCategory.getSelectedItem();
        TypeAffectation typeAffectation = (TypeAffectation) panel.productEditor.cmbTypeAffectation.getSelectedItem();
        ProductSunat productSunat = (ProductSunat) panel.productEditor.cmbProductSunat.getSelectedItem();
        Unity unity = (Unity) panel.productEditor.cmbUnity.getSelectedItem();
        String reference = panel.productEditor.txtReference.getText();

        Product product = new Product();
        product.setProductId(this.product_id);
        product.setName(name);
        product.setCategoryId((int) category.getId());
        product.setProductIdSunat((String) productSunat.getId());
        product.setType_affectation_id((String) typeAffectation.getId());
        product.setUnityId((int) unity.getUnityId());
        product.setReference(reference);
        product.setOutstanding(0);

        return product.save();
    }

    public Integer deleteProduct(){
        Product product = new Product();
        product.setProductId(this.product_id);
        return product.delete(0);
    }

    private void resetControls(){
        this.product_id=0;
        panel.productEditor.txtName.setText("");
        panel.productEditor.cmbCategory.setSelectedIndex(0);
        panel.productEditor.cmbTypeAffectation.setSelectedIndex(0);
        panel.productEditor.cmbProductSunat.setSelectedIndex(0);
        panel.productEditor.cmbUnity.setSelectedIndex(0);
        panel.productEditor.txtReference.setText("");
    }

    public void onClickSearchProduct(ActionEvent e){
        String query = panel.productList.txtQuery.getText();
        if(query != null){
            this.loadDataTableAsync(query);
        }
    }

    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }

    public void onClickDeleteAction(ActionEvent e, Object id){
        this.product_id = (int)id;
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar el producto?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            Integer rowsAffected = this.deleteProduct();

            if(rowsAffected < 1){
                JOptionPane.showMessageDialog(null,
                        "",
                        "Atención", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,
                        "Producto eliminado correctamente.",
                        "Atención", JOptionPane.INFORMATION_MESSAGE);
            }
            this.loadDataTableAsync("");
        }
        this.product_id = 0;

    }

    public void onClickEditAction(ActionEvent e, Object id){
        CompletableFuture<Product> futureProduct = CompletableFuture.supplyAsync(() -> new Product().getProduct((int) id));
        futureProduct.thenAcceptAsync(product -> SwingUtilities.invokeLater(() -> {
            this.product_id = product.getProductId();
            panel.productEditor.cmbCategory.setSelectedItemById(product.getCategoryId());
            panel.productEditor.cmbProductSunat.setSelectedItemById(product.getProductIdSunat());
            panel.productEditor.cmbTypeAffectation.setSelectedItemById(product.getTypeAffectationId());
            panel.productEditor.cmbUnity.setSelectedItemById(product.getCategoryId());
            panel.productEditor.txtName.setText(product.getName());
            panel.productEditor.txtReference.setText(product.getReference());
        }));
        this.switchTab((JButton) e.getSource());
    }


    public boolean validateControlsValue(){
        String name = panel.productEditor.txtName.getText();
        Category category = (Category) panel.productEditor.cmbCategory.getSelectedItem();
        TypeAffectation typeAffectation = (TypeAffectation) panel.productEditor.cmbTypeAffectation.getSelectedItem();
        ProductSunat productSunat = (ProductSunat) panel.productEditor.cmbProductSunat.getSelectedItem();
        Unity unity = (Unity) panel.productEditor.cmbUnity.getSelectedItem();

        if(name.trim().isEmpty()){
            return false;
        }
        if(category == null){
            return false;
        }
        if(typeAffectation == null){
            return false;
        }
        if(productSunat == null){
            return false;
        }
        if(unity == null){
            return false;
        }

        return true;
    }

    public void onClickSaveAction(ActionEvent e) {
        if(this.validateControlsValue()) {
            String message = this.product_id == 0 ? "¿Está seguro de crear el producto?" : "¿Está seguro de actualizar el producto?";
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                Integer rowsAffected = saveProduct();
                if(rowsAffected < 1){
                    JOptionPane.showMessageDialog(
                            null,
                            "No se pudo guardar el producto.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Producto guardado correctamente.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }
                this.loadDataTableAsync("");
                this.resetControls();
                this.switchTab((JButton) e.getSource());
            }
        }
    }
    public void onClickCancelAction(ActionEvent e) {
        String message = "¿Está seguro de cancelar la operación?";
        int response = JOptionPane.showConfirmDialog(null, message, "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            this.resetControls();
            this.switchTab((JButton) e.getSource());
        }
    }

    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent,command);
    }


}
