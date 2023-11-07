package models.typeAffectation;

import models.ModelSQL;
import models.db.Db;
import views.core.combobox.IComboBox;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeAffectation implements IComboBox {

    private String type_affectation_id;
    private String name;
    private int free;
    private int tax_id;

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

    public List<TypeAffectation> getTypesAffectationList(){
        try{
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_TYPE_AFFECTATION);

            List<TypeAffectation> typesAffectation = new ArrayList<>();
            while (result.next()){
                TypeAffectation typeAffectation = new TypeAffectation();
                typeAffectation.setType_affectation_id(result.getString("type_affectation_id"));
                typeAffectation.setName(result.getString("name"));
                typeAffectation.setFree(result.getInt("free"));
                typeAffectation.setTax_id(result.getInt("tax_id"));
                typesAffectation.add(typeAffectation);
            }
            result.getStatement().close();
            result.close();
            return typesAffectation;
        }catch (Exception e){
            return new ArrayList<>();
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
