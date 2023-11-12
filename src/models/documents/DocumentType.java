package models.documents;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import javax.swing.text.Document;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocumentType {
     private String documents_id;
     private String name;


     public DocumentType(){}

     public String getDocuments_id() {
          return documents_id;
     }
     public void setDocuments_id(String documents_id) {
          this.documents_id = documents_id;
     }
     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
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
                            new Parameter<>(2,query)
                    ));
               }

               List<DocumentType> documents = new ArrayList<>();
               while (result.next()){
                    DocumentType document = new DocumentType();
                    document.setDocuments_id(result.getString("document_type_id"));
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

     public DocumentType getDocument(String documents_id){
          try{
               ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_DOCUMENTTYPE, List.of(
                       new Parameter<>(1, documents_id)
               ));
               DocumentType document = new DocumentType();
               while(result.next()){
                    document.setDocuments_id(result.getString("document_type_id"));
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
               parameters.add(new Parameter<>(1,this.name));
               parameters.add(new Parameter<>(2,this.documents_id));
               return Db.executeUpdate(stmt,parameters);
          }catch (Exception e){
               return 0;
          }
     }

    public int delete(String documents_id){
          try{
               return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_DOCUMENTSTYPES,List.of(
                       new Parameter<>(1,documents_id)
               ));
          }catch (Exception e){
               return 0;
          }
     }
}

