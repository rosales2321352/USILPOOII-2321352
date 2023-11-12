package controllers.Documents;


import models.ModelSQL;
import models.documents.DocumentType;
import views.documents.DocumentsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DocumentsController {

    final String[] COLUMN_NAMES = { "Id", "Nombre" , "Acciones" };
    private final DocumentsView panel;
    private String documents_id="";

    private boolean edit=false;

    public DocumentsController(DocumentsView panel){
        this.panel = panel;
    }

    public void renderObjects(){
        this.panel.documentsList.makeTableHeader(COLUMN_NAMES);
    }

    public void resetControls(){
        this.edit=false;
        this.panel.documentsEditor.txtName.setText("");
        this.panel.documentsEditor.txtId.setText("");
        this.panel.documentsEditor.lblTitle.setText("Agregar documento");
        this.panel.documentsEditor.txtId.setEditable(true);



    }

    public void loadDataTableAsync(String query){
        CompletableFuture<List<DocumentType>> futureDocumentType = CompletableFuture.supplyAsync(() -> {
            return new DocumentType().getDocuments(query);
        });
        futureDocumentType.thenAcceptAsync(unities -> {
            Object[][] information = unities.stream()
                    .map(documents -> new Object[]{
                            String.valueOf(documents.getDocuments_id()),
                            documents.getName()})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.documentsList.makeTable(information, COLUMN_NAMES));
        });
    }

    public boolean validate(){
        if(panel.documentsEditor.txtName.getText().trim().isEmpty()){
            return false;
        }
        if(panel.documentsEditor.txtId.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }

    public int save(){
        DocumentType documentType  = new DocumentType();
        documentType.setDocuments_id(panel.documentsEditor.txtId.getText());
        documentType.setName(panel.documentsEditor.txtName.getText());

        return documentType.save(edit);
    }

    public void onClickBtnCancel(ActionEvent e){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de cancelar la operación?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            this.resetControls();
            this.switchTab((JButton) e.getSource());
        }
    }

    public void onClickBtnEdit(ActionEvent e, String documents_id){
        this.panel.documentsEditor.lblTitle.setText("Editar Documento");
        CompletableFuture<DocumentType> futureDocumentType = CompletableFuture.supplyAsync(() -> new DocumentType().getDocument(documents_id));
        futureDocumentType.thenAcceptAsync(documents -> SwingUtilities.invokeLater(() -> {
            this.edit = true;
            panel.documentsEditor.txtId.setText(documents.getDocuments_id());
            panel.documentsEditor.txtId.setEditable(false);
            panel.documentsEditor.txtName.setText(documents.getName());
        }));
        this.switchTab((JButton) e.getSource());
    }

    public void onClickBtnSave(ActionEvent e){
        if(validate()){
            String message="";
            if (this.documents_id.trim().isEmpty()) {

                message ="¿Está seguro de crear el documento?" ;
            }
            else {
                message = "¿Está seguro de actualizar el documento?";
            }
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int rowsAffected = save();
                if(rowsAffected < 1){
                    JOptionPane.showMessageDialog(
                            null,
                            "No se pudo guardar el documento.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Documento guardada correctamente.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }
                this.loadDataTableAsync("");
                this.resetControls();
                this.switchTab((JButton) e.getSource());
            }
        }
    }

    public void onClickBtnSearch(ActionEvent e){
        String query = panel.documentsList.txtQuery.getText();
        loadDataTableAsync(query);
    }

    public void onClickBtnDelete(ActionEvent e, String documents_id){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar el documento?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            String message ="";
            if(new DocumentType().delete(documents_id) < 1){
                message = "Ha ocurrido un error en el proceso";
            }else{
                message = "Unidad eliminada correctamente.";
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Atención", JOptionPane.INFORMATION_MESSAGE);
            this.loadDataTableAsync("");
        }
        this.documents_id = "";
    }

    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }

    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent, command);
    }
}
