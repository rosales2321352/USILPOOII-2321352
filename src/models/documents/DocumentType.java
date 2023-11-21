package models.documents;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

<<<<<<< HEAD
import javax.swing.text.Document;
=======
>>>>>>> feature/VentanaTipoDeCambio-1911054
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocumentType {
     private int documents_id;
     private String cod_sunat;
     private String name;

     public DocumentType(){}

     public int getDocuments_id() {
          return documents_id;
     }
     public void setDocuments_id(int documents_id) {
          this.documents_id = documents_id;
     }
     public String getName() {
          return name;
     }
     public void setName(String name) {
          this.name = name;
     }
     public String getCod_sunat() {
          return cod_sunat;
     }
     public void setCod_sunat(String cod_sunat) {
          this.cod_sunat = cod_sunat;
     }
     public List<DocumentType> getDocuments(){
          return this.getDocuments("");
     }
     public List<DocumentType> getDocuments(String query){
          try{
               ResultSet result;
               if(query.trim().isEmpty()){
                    result = Db.executeQuery(ModelSQL.SQL_STMT_GET_DOCUMENTSTYPES);
               }else{
                    query = '%'+query+'%';
                    result = Db.executeQuery(ModelSQL.SQL_STMT_GET_DOCUMENTSTYPES_BY_LIKE,Arrays.asList(
                            new Parameter<>(1,query),
                            new Parameter<>(2,query),
                            new Parameter<>(3,query)
                    ));
               }

               List<DocumentType> documents = new ArrayList<>();
               while (result.next()){
                    DocumentType document = new DocumentType();
                    document.setDocuments_id(result.getInt("document_type_id"));
                    document.setCod_sunat(result.getString("cod_sunat"));
                    document.setName(result.getString("name"));
                    documents.add(document);
               }
               result.getStatement().close();
               result.close();
               return documents;
          }catch (Exception e){
               return new ArrayList<>();
          }
     }

     public DocumentType getDocument(int documents_id){
          try{
               ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_DOCUMENTTYPE, List.of(
                       new Parameter<>(1, documents_id)
               ));
               DocumentType document = new DocumentType();
               while(result.next()){
                    document.setDocuments_id(result.getInt("document_type_id"));
                    document.setCod_sunat(result.getString("cod_sunat"));
                    document.setName(result.getString("name"));
               }
               result.getStatement().close();
               result.close();
               return document;
          }catch(Exception e){
               return new DocumentType();
          }
     }
     public int save(boolean edit){
          try{
               String stmt="";
               if (!edit) {

                    stmt = ModelSQL.SQL_STMT_INSERT_DOCUMENTSTYPES;
               }
               else {
                   stmt = ModelSQL.SQL_STMT_UPDATE_DOCUMENTSTYPES;
               }
               List<Parameter> parameters = new ArrayList<>();
               parameters.add(new Parameter<>(1,this.cod_sunat));
               parameters.add(new Parameter<>(2,this.name));
               parameters.add(new Parameter<>(3,this.documents_id));
               return Db.executeUpdate(stmt,parameters);
          }catch (Exception e){
               return 0;
          }
     }

    public int delete(int documents_id){
          try{
               return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_DOCUMENTSTYPES,List.of(
                       new Parameter<>(1,documents_id)
               ));
          }catch (Exception e){
               return 0;
          }
     }
}

