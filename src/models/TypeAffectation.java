package models;

import classes.db.Db;
import models.category.Category;
import views.core.combobox.IComboBox;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeAffectation implements IComboBox {

    private int type_affectation_id;
    private String name;
    private int free;
    private int tax_id;

    public TypeAffectation(){

    }
    public TypeAffectation(int type_affectation_id,String name, int free, int tax_id){
        this.type_affectation_id = type_affectation_id;
        this.name = name;
        this.free = free;
        this.tax_id = tax_id;
    }

    public List<TypeAffectation> getTypesAffectationList(){
        try{
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_TYPE_AFFECTATION);
            List<TypeAffectation> typesAffectation = new ArrayList<>();
            while (result.next()){
                typesAffectation.add(new TypeAffectation(
                        result.getInt("type_affectation_id"),
                        result.getString("name"),
                        result.getInt("free"),
                        result.getInt("tax_id")
                ));
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
