package models.combobox;

import models.documents.DocumentType;
import views.core.combobox.IComboBox;

import java.util.List;

public class DocumentTypeComboBox extends DocumentType implements IComboBox {


    @Override
    public Object getId() {
        return this.getDocuments_id();
    }
    @Override
    public Object getValue() {
        return this.getName();
    }
    @Override
    public String toString(){
        return this.getName();
    }
    public List<DocumentTypeComboBox> getDocumentTypeCmb(String query){
        var list =this.getDocuments(query);
        return list.stream()
                .map(x -> {
                    DocumentTypeComboBox dtc = new DocumentTypeComboBox();
                    dtc.setDocuments_id(x.getDocuments_id());
                    dtc.setCod_sunat(x.getCod_sunat());
                    dtc.setName(x.getName());
                    return dtc;
                }).toList();
    }
}
