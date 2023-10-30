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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

public class ProductEditor extends JPanel{

    private int id_product;
    private ProductController controller;
    public CustomComboBox<Category> cmbCategory;
    public CustomComboBox<TypeAffectation> cmbTypeAffectation;
    public CustomComboBox<ProductSunat> cmbProductSunat;
    public CustomComboBox<UnityComboBox> cmbUnity;
    public JTextField txtReference;
    public JTextField txtName;
    public JCheckBox jCheckBox2;

    public ProductEditor(ProductController controller) {
        this.controller = controller;
        this.drawControlls();
        this.controller.loadDataComboBoxAsync();
        this.eventFocus();
    }

    public void drawControlls(){

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Editar Producto");
        titlePanel.add(lblTitle);

        this.add(titlePanel,BorderLayout.PAGE_START);

        JPanel controlsPanel = new JPanel();
        JLabel lblName = new JLabel("Nombre:");
        JLabel lblCategory = new JLabel("Categoría:");
        JLabel lblCodSunat = new JLabel("Cod. SUNAT:");
        JLabel lblTipAfect = new JLabel("Tipo de Afectación:");
        JLabel lblReference = new JLabel("Referencia:");
        JLabel lblUnity = new JLabel("Unidad de Medida:");
        JLabel lblQuantity = new JLabel("Stock");


        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(200,30));
        cmbCategory = new CustomComboBox<>();
        cmbCategory.setPreferredSize(new Dimension(200,30));
        cmbProductSunat = new CustomComboBox<>();
        cmbProductSunat.setPreferredSize(new Dimension(200,30));
        cmbTypeAffectation = new CustomComboBox<>();
        cmbTypeAffectation.setPreferredSize(new Dimension(200,30));
        cmbUnity = new CustomComboBox<>();
        cmbUnity.setPreferredSize(new Dimension(200,30));
        txtReference = new JTextField();
        txtReference.setPreferredSize(new Dimension(200,30));
        cmbCategory.setPreferredSize(new Dimension(200,30));
        cmbCategory.setPreferredSize(new Dimension(200,30));

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);

        JFormattedTextField txtquantity = new JFormattedTextField(numberFormat);
        txtquantity.setValue(0);

        jCheckBox2 = new javax.swing.JCheckBox();

        GroupLayout layout = new GroupLayout(controlsPanel);
        controlsPanel.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblName, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblCategory, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblCodSunat, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblReference, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(208, 208, 208)
                                                                .addComponent(lblUnity))
                                                        .addComponent(txtReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cmbUnity, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(cmbProductSunat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6)
                                                .addComponent(lblTipAfect)
                                                .addGap(16, 16, 16)
                                                .addComponent(cmbTypeAffectation, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCategory)
                                        .addComponent(lblCodSunat)
                                        .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbTypeAffectation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTipAfect)
                                        .addComponent(lblUnity)
                                        .addComponent(cmbProductSunat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbUnity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblReference)
                                        .addComponent(txtReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox2))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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



    public void eventFocus(){
        txtName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                if(textField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(
                            null,
                            "El nombre del producto no puede ser nulo o vacio.",
                            "Atención",
                            JOptionPane.INFORMATION_MESSAGE);
                    textField.requestFocus();
                }
            }
        });
    }


}
