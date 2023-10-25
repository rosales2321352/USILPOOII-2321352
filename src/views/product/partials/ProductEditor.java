package views.product.partials;

import controllers.product.ProductController;
import models.ProductSunat;
import models.TypeAffectation;
import models.Unity;
import models.category.Category;
import models.combobox.UnityComboBox;
import views.core.combobox.CustomComboBox;
import views.core.combobox.CustomComboBoxModel;

import javax.swing.*;
import java.awt.*;

public class ProductEditor extends JPanel{

    private int id_product;
    private ProductController controller;
    public CustomComboBox<Category> cmbCategory;
    public CustomComboBox<TypeAffectation> cmbTypeAffectation;
    public CustomComboBox<ProductSunat> cmbProductSunat;
    public CustomComboBox<UnityComboBox> cmbUnity;

    public ProductEditor(ProductController controller) {
        this.controller = controller;
        this.drawControlls();
        this.controller.loadDataComboBoxAsync();
    }

    public void drawControlls(){

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Editar Producto");
        titlePanel.add(lblTitle);

        this.add(titlePanel,BorderLayout.PAGE_START);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel,BoxLayout.Y_AXIS));

        JLabel lblName = new JLabel("Nombre:");

        JPanel sectionPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel sectionPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblCategory = new JLabel("Categoría:");
        JLabel lblCodSunat = new JLabel("Cod. SUNAT:");
        JLabel lblTipAfect = new JLabel("Tipo de Afectación:");
        JLabel lblReference = new JLabel("Referencia:");

        cmbCategory = new CustomComboBox<>();
        cmbProductSunat = new CustomComboBox<>();
        cmbTypeAffectation = new CustomComboBox<>();
        cmbUnity = new CustomComboBox<>();

        JTextField txtReference = new JTextField();
        txtReference.setPreferredSize(new Dimension(200,30));

        sectionPanel1.add(lblCategory);
        sectionPanel1.add(cmbCategory);

        sectionPanel1.add(lblCodSunat);
        sectionPanel1.add(cmbProductSunat);

        sectionPanel2.add(lblTipAfect);
        sectionPanel2.add(cmbTypeAffectation);

        sectionPanel2.add(lblTipAfect);
        sectionPanel2.add(cmbUnity);

        sectionPanel2.add(lblReference);
        sectionPanel2.add(txtReference);

        controlsPanel.add(lblName);
        controlsPanel.add(sectionPanel1);
        controlsPanel.add(sectionPanel2);

        this.add(controlsPanel,BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setName("List");
        btnCancel.addActionListener(controller::onClickCancelAction);
        buttonsPanel.add(btnCancel,FlowLayout.LEFT);

        JButton btnSave = new JButton("Guardar");
        btnSave.setName("List");
        btnSave.addActionListener(controller::onClickSaveAction);
        buttonsPanel.add(btnSave,FlowLayout.LEFT);

        this.add(buttonsPanel,BorderLayout.PAGE_END);

    }

    public int getIdProduct(){
        return  this.id_product;
    }

    public void setIdProduct(int id_product){
        this.id_product = id_product;
    }


}
