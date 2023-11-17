package views.product.partials;

import controllers.product.ProductController_;
import models.ProductSunat;
import models.category.Category;
import models.combobox.UnityComboBox;
import models.typeAffectation.TypeAffectation;
import views.JPBaseEditor;
import views.core.CustomButton;
import views.core.combobox.CustomComboBox;

import javax.swing.*;
import java.awt.*;

public class JPProductEditor extends JPBaseEditor {

    private final ProductController_ controller;
    public CustomComboBox<Category> cmbCategory;
    public CustomComboBox<TypeAffectation> cmbTypeAffectation;
    public CustomComboBox<ProductSunat> cmbProductSunat;
    public CustomComboBox<UnityComboBox> cmbUnity;
    public JTextField txtReference;
    public JTextField txtName;
    public JSpinner spnQuantity;

    public JPProductEditor(ProductController_ controller){
        super();
        this.controller=controller;
        drawControls();
    }

    @Override
    public void drawControls() {
        makeTitle("Agregar nuevo producto");
        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        JLabel lblName = new JLabel("Nombre:");
        JLabel lblCategory = new JLabel("Categoría:");
        JLabel lblCodSunat = new JLabel("Cod. SUNAT:");
        JLabel lblTipAffect = new JLabel("Tipo de Afectación:");
        JLabel lblReference = new JLabel("Referencia:");
        JLabel lblUnity = new JLabel("Unidad de Medida:");
        JLabel lblQuantity = new JLabel("Stock:");

        txtName = new JTextField();
        cmbCategory = new CustomComboBox<>();
        cmbProductSunat = new CustomComboBox<>();
        cmbTypeAffectation = new CustomComboBox<>();
        cmbUnity = new CustomComboBox<>();
        txtReference = new JTextField();
        SpinnerNumberModel model = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
        spnQuantity = new JSpinner(model);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spnQuantity);
        spnQuantity.setEditor(editor);
        spnQuantity.setValue(0);
        JButton btnViewPrices = new CustomButton("Editar Precios",new Color(0,123,255),Color.WHITE );

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titlePanel)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60)
                    .addComponent(lblName)
                    .addGap(10)
                    .addComponent(txtName,GroupLayout.DEFAULT_SIZE,250,GroupLayout.PREFERRED_SIZE)
                    .addGap(30)
                    .addComponent(lblCategory)
                    .addGap(10)
                    .addComponent(cmbCategory,GroupLayout.DEFAULT_SIZE,250,GroupLayout.PREFERRED_SIZE)
                ).addGroup(layout.createSequentialGroup()
                    .addComponent(lblUnity)
                    .addGap(10)
                    .addComponent(cmbUnity,GroupLayout.DEFAULT_SIZE,250,GroupLayout.PREFERRED_SIZE)
                    .addGap(20)
                    .addComponent(lblCodSunat)
                    .addGap(10)
                    .addComponent(cmbProductSunat,GroupLayout.DEFAULT_SIZE,250,GroupLayout.PREFERRED_SIZE)
                ).addGroup(layout.createSequentialGroup()
                    .addComponent(lblTipAffect)
                    .addGap(10)
                    .addComponent(cmbTypeAffectation,GroupLayout.DEFAULT_SIZE,250,GroupLayout.PREFERRED_SIZE)
                    .addGap(30)
                    .addComponent(lblReference)
                    .addGap(10)
                    .addComponent(txtReference,GroupLayout.DEFAULT_SIZE,250,GroupLayout.PREFERRED_SIZE)
                ).addGroup(layout.createSequentialGroup()
                    .addGap(55)
                    .addComponent(lblQuantity)
                    .addGap(10)
                    .addComponent(spnQuantity,GroupLayout.DEFAULT_SIZE,250,GroupLayout.PREFERRED_SIZE)
                    .addGap(30)
                    .addComponent(btnViewPrices,GroupLayout.DEFAULT_SIZE,300,GroupLayout.PREFERRED_SIZE)
                )
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(titlePanel)
            .addGap(40)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblName)
                .addComponent(txtName,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
                .addComponent(lblCategory)
                .addComponent(cmbCategory,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblUnity)
                .addComponent(cmbUnity,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
                .addComponent(lblCodSunat)
                .addComponent(cmbProductSunat,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTipAffect)
                .addComponent(cmbTypeAffectation,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
                .addComponent(lblReference)
                .addComponent(txtReference,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblQuantity)
                .addComponent(spnQuantity,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
                .addComponent(btnViewPrices,GroupLayout.DEFAULT_SIZE,35,GroupLayout.PREFERRED_SIZE)
            )
        );
        add(panelControls,BorderLayout.PAGE_START);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.setBackground(Color.WHITE);

        JButton btnCancel = new CustomButton("Cancelar",new Color(65, 65, 65),Color.WHITE);
        btnCancel.setName("List");
        btnCancel.addActionListener(controller::onClickCancel);

        JButton btnSave = new CustomButton("Guardar",new Color(0,123,255),Color.WHITE);
        btnSave.setName("List");
        btnSave.addActionListener(controller::onClickSave);

        panelButtons.add(btnSave);
        panelButtons.add(btnCancel);
        add(panelButtons,BorderLayout.PAGE_END);
    }
}
