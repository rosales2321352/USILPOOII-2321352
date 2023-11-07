package views.product.partials;

import controllers.product.ProductController;
import models.ProductSunat;
import models.typeAffectation.TypeAffectation;
import models.category.Category;
import models.combobox.UnityComboBox;
import views.core.combobox.CustomComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

public class ProductEditor extends JPanel{

    private int id_product;
    private final ProductController controller;
    public CustomComboBox<Category> cmbCategory;
    public CustomComboBox<TypeAffectation> cmbTypeAffectation;
    public CustomComboBox<ProductSunat> cmbProductSunat;
    public CustomComboBox<UnityComboBox> cmbUnity;
    public JTextField txtReference;
    public JTextField txtName;
    public JFormattedTextField txtquantity;

    public ProductEditor(ProductController controller) {
        this.setBackground(Color.WHITE);
        this.controller = controller;
        this.drawControlls();
        this.controller.loadDataComboBoxAsync();
        this.eventFocus();
    }

    public void drawControlls(){

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Editar Producto");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);

        this.add(titlePanel,BorderLayout.PAGE_START);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBackground(Color.WHITE);
        JLabel lblName = new JLabel("Nombre:");
        JLabel lblCategory = new JLabel("Categoría:");
        JLabel lblCodSunat = new JLabel("Cod. SUNAT:");
        JLabel lblTipAfect = new JLabel("Tipo de Afectación:");
        JLabel lblReference = new JLabel("Referencia:");
        JLabel lblUnity = new JLabel("Unidad de Medida:");
        JLabel lblQuantity = new JLabel("Stock:");


        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(200,30));
        txtName.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        cmbCategory = new CustomComboBox<>();
        cmbCategory.setPreferredSize(new Dimension(200,30));


        cmbProductSunat = new CustomComboBox<>();
        cmbProductSunat.setPreferredSize(new Dimension(200,30));
        cmbProductSunat.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        cmbTypeAffectation = new CustomComboBox<>();
        cmbTypeAffectation.setPreferredSize(new Dimension(200,30));
        cmbTypeAffectation.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        cmbUnity = new CustomComboBox<>();
        cmbUnity.setPreferredSize(new Dimension(200,30));
        cmbUnity.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        txtReference = new JTextField();
        txtReference.setPreferredSize(new Dimension(200,30));
        txtReference.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        txtquantity = new JFormattedTextField(numberFormat);
        txtquantity.setValue(0);
        txtquantity.setPreferredSize(new Dimension(200,30));
        txtquantity.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        GroupLayout layout = new GroupLayout(controlsPanel);
        controlsPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblName)
                            .addComponent(txtName)
                            .addComponent(lblCategory)
                            .addComponent(cmbCategory)
                        )
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblQuantity)
                            .addComponent(txtquantity)
                            .addComponent(lblTipAfect)
                            .addComponent(cmbTypeAffectation)
                        )
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblReference)
                            .addComponent(txtReference)
                            .addComponent(lblUnity)
                            .addComponent(cmbUnity)
                        )
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblCodSunat)
                            .addComponent(cmbProductSunat)
                        )
                    )
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName)
                    .addComponent(lblCategory)
                    .addComponent(cmbCategory)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(txtquantity)
                    .addComponent(lblTipAfect)
                    .addComponent(cmbTypeAffectation)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReference)
                    .addComponent(txtReference)
                    .addComponent(lblUnity)
                    .addComponent(cmbUnity)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodSunat)
                    .addComponent(cmbProductSunat)
                )
        );

        this.add(controlsPanel,BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        JButton btnSave = new JButton("Guardar");
        btnSave.setBackground(new Color(0,123,255));
        btnSave.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnSave.setForeground(Color.WHITE);
        btnSave.setName("List");
        btnSave.addActionListener(controller::onClickSaveAction);
        buttonsPanel.add(btnSave,FlowLayout.LEFT);

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setBackground(new Color(146, 154, 167));
        btnCancel.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(146, 154, 167))));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setName("List");
        btnCancel.addActionListener(controller::onClickCancelAction);
        buttonsPanel.add(btnCancel,FlowLayout.LEFT);

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
