package views.Currency.partials;

import controllers.Currency.CurrencyController;

import javax.swing.*;
import java.awt.*;

public class CurrencyEditor extends JPanel {

    private final CurrencyController controller;
    public JLabel lblTitle;
    public JButton btnSave;
    public JButton btnCancel;
    public JTextField txtName;
    public JTextField txtsymbol;

    public JTextField txtiso_code;

    public JTextField txtlocation;

    public JCheckBox txtpredetermined;

    public CurrencyEditor(CurrencyController controller){
        this.setBackground(Color.WHITE);
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.drawControls();
    }

    public void drawControls(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblTitle = new JLabel("Agregar Moneda");
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);

        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        JLabel lblName = new JLabel("Nombre:");
        this.txtName = new JTextField();
        JLabel lblsymbol = new JLabel("Simbolo:");
        this.txtsymbol = new JTextField();
        JLabel lbllocation = new JLabel("Locación:");
        this.txtlocation = new JTextField();
        JLabel lbliso_code = new JLabel("Codigo iso:");
        this.txtiso_code = new JTextField();
        JLabel lblpredeterminated = new JLabel("Predeterminado:");
        this.txtpredetermined = new JCheckBox();
        this.txtpredetermined.setBackground(Color.white);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(titlePanel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblName)
                                        .addGap(10)
                                        .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblsymbol)
                                        .addGap(10)
                                        .addComponent(txtsymbol, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbliso_code)
                                        .addGap(10)
                                        .addComponent(txtiso_code, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbllocation)
                                        .addGap(10)
                                        .addComponent(txtlocation, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblpredeterminated)
                                        .addGap(10)
                                        .addComponent(txtpredetermined, GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titlePanel)
                        .addGap(30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lblName)
                                .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lblsymbol)
                                .addComponent(txtsymbol, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lbliso_code)
                                .addComponent(txtiso_code, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lbllocation)
                                .addComponent(txtlocation, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lblpredeterminated)
                                .addComponent(txtpredetermined, GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
        );


        this.add(panelControls, BorderLayout.PAGE_START);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.setBackground(Color.WHITE);

        this.btnCancel = new JButton("Cancelar");
        btnCancel.setBackground(new Color(65, 65, 65));
        btnCancel.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(65, 65, 65))));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setName("List");
        btnCancel.addActionListener(controller::onClickBtnCancel);

        this.btnSave = new JButton("Guardar");
        btnSave.setBackground(new Color(0,123,255));
        btnSave.setBorder((BorderFactory.createMatteBorder(6,20,6,20,new java.awt.Color(0,123,255))));
        btnSave.setForeground(Color.WHITE);
        btnSave.setName("List");
        btnSave.addActionListener(controller::onClickBtnSave);

        panelButtons.add(btnSave);
        panelButtons.add(btnCancel);

        this.add(panelButtons, BorderLayout.PAGE_END);
    }
}
