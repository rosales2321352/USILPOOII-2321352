package views.product.partials;

import controllers.product.ProductController;

import javax.swing.*;
import java.awt.*;

public class ProductEditor extends JPanel{

    private int id_product;
    private ProductController controller;

    public ProductEditor(ProductController controller) {
        this.controller = controller;
        this.drawControlls();
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

        JTextField txtCategory = new JTextField();
        txtCategory.setPreferredSize(new Dimension(200,30));
        JTextField txtCodSunat = new JTextField();
        txtCodSunat.setPreferredSize(new Dimension(200,30));
        JTextField txtTipAfect = new JTextField();
        txtTipAfect.setPreferredSize(new Dimension(200,30));
        JTextField txtReference = new JTextField();
        txtReference.setPreferredSize(new Dimension(200,30));

        sectionPanel1.add(lblCategory);
        sectionPanel1.add(txtCategory);

        sectionPanel1.add(lblCodSunat);
        sectionPanel1.add(txtCodSunat);

        sectionPanel2.add(lblTipAfect);
        sectionPanel2.add(txtTipAfect);

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
