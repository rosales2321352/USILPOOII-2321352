package models.typeAffectation;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;
import views.core.combobox.IComboBox;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeAffectation implements IComboBox {

    private String type_affectation_id;
    private String name;
    private int free;
    private int tax_id;
    private int onerous;

    public TypeAffectation(){

    }

    public String getType_affectation_id() {
        return type_affectation_id;
    }
    public void setType_affectation_id(String type_affectation_id) {
        this.type_affectation_id = type_affectation_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getFree() {
        return free;
    }
    public void setFree(int free) {
        this.free = free;
    }
    public int getTax_id() {
        return tax_id;
    }
    public void setTax_id(int tax_id) {
        this.tax_id = tax_id;
    }
    public int getOnerous() { return onerous; }
    public void setOnerous(int onerous) { this.onerous = onerous; }
    public List<TypeAffectation> getTypesAffectationList(){
        return this.getTypesAffectationList("");
    }
    public List<TypeAffectation> getTypesAffectationList(String query){
        try{
            ResultSet result;
            if(query.trim().isEmpty()) {
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_TYPES_AFFECTATIONS);
            }else{
                query = '%'+query+'%';
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_TYPES_AFFECTATIONS_BY_LIKE, Arrays.asList(
                   new Parameter<>(1,query),
                   new Parameter<>(2,query)
                ));
            }
            List<TypeAffectation> typesAffectation = new ArrayList<>();
            while (result.next()){
                TypeAffectation typeAffectation = new TypeAffectation();
                typeAffectation.setType_affectation_id(result.getString("type_affectation_id"));
                typeAffectation.setName(result.getString("name"));
                typeAffectation.setFree(result.getInt("free"));
                typeAffectation.setTax_id(result.getInt("tax_id"));
                typeAffectation.setOnerous(result.getInt("onerous"));
                typesAffectation.add(typeAffectation);
            }
            result.getStatement().close();
            result.close();
            return typesAffectation;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
    public TypeAffectation getTypeAffectation(String id){
        try{
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_TYPE_AFFECTATION,List.of(
               new Parameter<>(1,id)
            ));
            TypeAffectation typeAffectation = new TypeAffectation();
            while(result.next()){
                typeAffectation.setType_affectation_id(result.getString("type_affectation_id"));
                typeAffectation.setName(result.getString("name"));
                typeAffectation.setFree(result.getInt("free"));
                typeAffectation.setTax_id(result.getInt("tax_id"));
                typeAffectation.setOnerous(result.getInt("onerous"));
            }
            result.getStatement().close();
            result.close();
            return  typeAffectation;
        }catch (Exception e){
            return new TypeAffectation();
        }
    }
    public int save(int editor){
        try{
            String stmt = "";
            List<Parameter> parameters = new ArrayList<>();
            if(editor==1) {
                stmt = ModelSQL.SQL_STMT_UPDATE_TYPE_AFFECTATION;
                parameters.add(new Parameter<>(1,this.name));
                parameters.add(new Parameter<>(2,this.free));
                parameters.add(new Parameter<>(3,this.tax_id));
                parameters.add(new Parameter<>(4,this.onerous));
                parameters.add(new Parameter<>(5,this.type_affectation_id));
            }else{
                stmt = ModelSQL.SQL_STMT_INSERT_TYPE_AFFECTATION;
                parameters.add(new Parameter<>(1,this.type_affectation_id));
                parameters.add(new Parameter<>(2,this.name));
                parameters.add(new Parameter<>(3,this.free));
                parameters.add(new Parameter<>(4,this.tax_id));
                parameters.add(new Parameter<>(5,this.onerous));
            }
            return Db.executeUpdate(stmt,parameters);
        }catch (Exception e){
            return 0;
        }
    }
    public int delete(String id){
        try{
            return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_TYPE_AFFECTATION,List.of(
               new Parameter<>(1,id)
            ));
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public Object getId() {
        return this.type_affectation_id;
    }

    @Override
    public Object getValue() {
        return this.name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
