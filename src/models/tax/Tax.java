package models.tax;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tax {

    private int tax_id;
    private String name;
    private Double percentage;
    public Tax(){}
    public Tax(int tax_id, String name, Double percentage) {
        this.tax_id = tax_id;
        this.name = name;
        this.percentage = percentage;
    }
    public int getTax_id() {
        return tax_id;
    }
    public void setTax_id(int tax_id) {
        this.tax_id = tax_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPercentage() {
        return percentage;
    }
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
    public List<Tax> getTaxes(){
        return this.getTaxes("");
    }
    public List<Tax> getTaxes(String query){
        try{
            ResultSet result;
            if(query.trim().isEmpty()){
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_TAXES);
            }else{
                query = '%'+query+'%';
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_TAXES_BY_LIKE, Arrays.asList(
                    new Parameter<>(1,query),
                    new Parameter<>(2,query)
                ));
            }
            List<Tax> taxList = new ArrayList<>();
            while (result.next()){
                taxList.add(new Tax(
                   result.getInt("tax_id"),
                   result.getString("name"),
                   result.getDouble("percentage")
                ));
            }

            return taxList;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Tax getTax(int tax_id){
        try{
            var result = Db.executeQuery(ModelSQL.SQL_STMT_GET_TAX,List.of(
                    new Parameter<>(1,tax_id)
            ));
            Tax tax = new Tax();
            while (result.next()){
                    tax.setTax_id(result.getInt("tax_id"));
                    tax.setName(result.getString("name"));
                    tax.setPercentage(result.getDouble("percentage"));
            }
            return tax;
        }catch (Exception e){
            return new Tax();
        }
    }

    public int save(){
        try{
            String stmt = ModelSQL.SQL_STMT_INSERT_TAX;
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1,this.name));
            parameters.add(new Parameter<>(2,this.percentage));
            if(this.tax_id!=0){
                stmt = ModelSQL.SQL_STMT_UPDATE_TAX;
                parameters.add(new Parameter<>(3,this.tax_id));
            }
            return Db.executeUpdate(stmt,parameters);
        }catch (Exception e){
            return 0;
        }
    }

    public int delete(int tax_id){
        try{
            return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_TAX, List.of(
                new Parameter<>(1,tax_id)
            ));
        }catch (Exception e){
            return 0;
        }
    }
}
