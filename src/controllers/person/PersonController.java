package controllers.person;


import models.person.Person;
import views.person.PersonView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class PersonController {

    final private PersonView panel;

    private int employee_id=0;

    final String[] COLUMN_NAMES = { "Id","Tipo_Documento","Documento","Nombre","Direccion","Email","Telefono","Referencias","Acciones"};

    public PersonController(PersonView panel) { this.panel = panel; }

    public void renderObjects() { this.panel.personList.makeTableHeader(COLUMN_NAMES);}

    public void loadDataTableAsync(String query){
        CompletableFuture<List<Person>> futureTablePerson = CompletableFuture.supplyAsync(() -> {
            return  new Person().getListPersons(query);
        });
        futureTablePerson.thenAcceptAsync(persons -> {
            Object[][] information = persons.stream()
                .map(person -> new Object[] { String.valueOf(person.getEmployeeId()),person.getTypeDniId(),person.getDocument(), person.getName(), person.getAddress(), person.getEmail() , person.getTelephone(), person.getReference()})
                .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.personList.makeTable(information,COLUMN_NAMES));

        });
    }


    /*public void loadDataComboBoxAsync(){
        CompletableFuture<List<DocumentType>> futurePerson = CompletableFuture.supplyAsync(new DocumentType()::getDocuments);


        futurePerson.thenAcceptAsync(documentTypes -> SwingUtilities.invokeLater(() -> {
            CustomComboBox<DocumentType> comboBoxModel = new CustomComboBox<>(documentTypes);
            panel.personEditor.cmbDocumentType.setModel((ComboBoxModel<DocumentType>) comboBoxModel);
        }));
    }*/

    public Integer savePerson(){

        //DocumentType documentType = (DocumentType) panel.personEditor.cmbDocumentType.getSelectedItem();
        String document = panel.personEditor.txtDocument.getText();
        String name = panel.personEditor.txtName.getText();
        String address = panel.personEditor.txtAddress.getText();
        String email = panel.personEditor.txtEmail.getText();
        String telephone = panel.personEditor.txtTelephone.getText();
        String reference = panel.personEditor.txtReferences.getText();

        Person person = new Person();
        person.setEmployeeId(this.employee_id);
        //person.setTypeDniId(this.DocumentType);
        person.setDocument(document);
        person.setName(name);
        person.setAddress(address);
        person.setEmail(email);
        person.setTelephone(telephone);
        person.setReference(reference);

        return person.save();
    }

    public  Integer deletePerson(){
        Person person = new Person();
        person.setEmployeeId(this.employee_id);
        return person.delete();
    }

    private void resetControls(){
        this.employee_id=0;
        panel.personEditor.txtDocument.setText("");
        panel.personEditor.txtName.setText("");
        panel.personEditor.txtAddress.setText("");
        panel.personEditor.txtEmail.setText("");
        panel.personEditor.txtTelephone.setText("");
        panel.personEditor.txtReferences.setText("");
    }

    public void onClickSearchPerson(ActionEvent e){
        String query = panel.personList.txtQuery.getText();
        if(query != null){
            this.loadDataTableAsync(query);
        }
    }

    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }

    public void onClickDeleteAction(ActionEvent e, Object id){
        this.employee_id = (int)id;
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eleminar al empleado?" ,
                "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION){
            Integer rowsAffected = this.deletePerson();

            if(rowsAffected < 1){
                JOptionPane.showMessageDialog(null,
                        "",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,
                        "Empleado eliminado correctamente",
                        "Atencion",JOptionPane.INFORMATION_MESSAGE);
            }
            this.loadDataTableAsync("");
        }
        this.employee_id = 0;

    }


    public void onClickEditAction(ActionEvent e, Object id){
        CompletableFuture<Person> futurePerson = CompletableFuture.supplyAsync(() -> new Person().getPerson((int) id));
        futurePerson.thenAcceptAsync(person -> SwingUtilities.invokeLater(() -> {
            this.employee_id = person.getEmployeeId();
            panel.personEditor.txtDocument.setText(person.getDocument());
            panel.personEditor.txtName.setText(person.getName());
            panel.personEditor.txtName.setText(person.getAddress());
            panel.personEditor.txtName.setText(person.getEmail());
            panel.personEditor.txtName.setText(person.getTelephone());
            panel.personEditor.txtName.setText(person.getReference());

        }));
        this.switchTab((JButton) e.getSource());
    }

    public boolean validateControlsValue(){
        String document = panel.personEditor.txtDocument.getText();
        String name = panel.personEditor.txtDocument.getText();
        String address = panel.personEditor.txtDocument.getText();
        String email = panel.personEditor.txtDocument.getText();
        String telephone = panel.personEditor.txtDocument.getText();
        String reference = panel.personEditor.txtDocument.getText();

        if(document.trim().isEmpty()){
            return false;
        }
        if(name.trim().isEmpty()){
            return false;
        }
        if(address.trim().isEmpty()){
            return false;
        }
        if(email.trim().isEmpty()){
            return false;
        }
        if(telephone.trim().isEmpty()){
            return false;
        }if(reference.trim().isEmpty()){
            return false;
        }

        return true;

    }

    public void onClickSaveAction(ActionEvent e) {
        if(this.validateControlsValue()) {
            String message = this.employee_id == 0 ? "Estás seguro de crear el empleado?" : "Estás seguro de actualizar el empleado?";
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmacion" , JOptionPane.YES_NO_OPTION);

            if(response == JOptionPane.YES_OPTION) {
                Integer rowsAffected = savePerson();
                if (rowsAffected < 1) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No se pudo guardar el empleado.",
                            "Atencion", JOptionPane.INFORMATION_MESSAGE);
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
