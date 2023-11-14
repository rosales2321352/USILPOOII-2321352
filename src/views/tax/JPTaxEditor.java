package views.tax;

import controllers.command.CancelCommand;
import controllers.command.ICommand;
import controllers.command.SaveCommand;
import controllers.tax.TaxController_;
import views.JPBaseEditor;
import views.core.CustomButton;

import javax.swing.*;
import java.awt.*;

public class JPTaxEditor extends JPBaseEditor {
    private final TaxController_ controller;

    public JTextField txtName;
    public JSpinner spnPercentage;
    public JPTaxEditor(TaxController_ controller){
        super();
        this.controller = controller;
        this.drawControls();
    }

    @Override
    public void drawControls() {
        this.makeTitle("Agregar nuevo tipo de impuesto");
        JPanel panelControls = new JPanel();
        panelControls.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(panelControls);
        panelControls.setLayout(layout);

        JLabel lblName = new JLabel("Nombre:");
        this.txtName = new JTextField();
        JLabel lblPercentaje = new JLabel("Porcentaje:");

        SpinnerNumberModel model = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
        this.spnPercentage = new JSpinner(model);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spnPercentage);
        this.spnPercentage.setEditor(editor);
        this.spnPercentage.setValue(0);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(this.titlePanel)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblName)
                    .addGap(10)
                    .addComponent(txtName,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblPercentaje)
                    .addGap(10)
                    .addComponent(this.spnPercentage,GroupLayout.DEFAULT_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(titlePanel)
            .addGap(30)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblName)
                .addComponent(txtName,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblPercentaje)
                .addComponent(this.spnPercentage,GroupLayout.DEFAULT_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            )
        );
        this.add(panelControls,BorderLayout.PAGE_START);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.setBackground(Color.WHITE);

        JButton btnCancel = new CustomButton("Cancelar",new Color(65, 65, 65),Color.WHITE);
        btnCancel.setName("List");
        btnCancel.addActionListener(new CancelCommand<>(controller)::execute);

        JButton btnSave = new CustomButton("Guardar",new Color(0,123,255),Color.WHITE);
        btnSave.setName("List");
        btnSave.addActionListener(new SaveCommand<>(controller)::execute);

        panelButtons.add(btnSave);
        panelButtons.add(btnCancel);
        this.add(panelButtons,BorderLayout.PAGE_END);
    }
}
