package models.unity;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Unity {
    private int unity_id;
    private String name;
    private String symbol;

    public Unity(){}

    public int getUnityId() {
        return unity_id;
    }
    public void setUnityId(int unity_id) {
        this.unity_id = unity_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public List<Unity> getUnits(){
        return this.getUnits("");
    }
    public List<Unity> getUnits(String query){
        try{
            ResultSet result;
            if(query.trim().isEmpty()){
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_UNITIES);
            }else{
                query = '%'+query+'%';
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_UNITIES_BY_LIKE,Arrays.asList(
                        new Parameter<>(1,query),
                        new Parameter<>(2,query)
                ));
            }

            List<Unity> unities = new ArrayList<>();
            while (result.next()){
                Unity unity = new Unity();
                unity.setUnityId(result.getInt("unity_id"));
                unity.setName(result.getString("name"));
                unity.setSymbol(result.getString("symbol"));
                unities.add(unity);
            }
            result.getStatement().close();
            result.close();
            return unities;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Unity getUnit(int unity_id){
        try{
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_UNITY, List.of(
                    new Parameter<>(1, unity_id)
            ));
            Unity unity = new Unity();
            while(result.next()){
                unity.setUnityId(result.getInt("unity_id"));
                unity.setName(result.getString("name"));
                unity.setSymbol(result.getString("symbol"));
            }
            result.getStatement().close();
            result.close();
            return unity;
        }catch(Exception e){
            return new Unity();
        }
    }
    public int save(){
        try{
            String stmt = this.unity_id==0?ModelSQL.SQL_STMT_INSERT_UNITY:ModelSQL.SQL_STMT_UPDATE_UNITY;
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1,this.name));
            parameters.add(new Parameter<>(2,this.symbol));
            if(this.unity_id!=0) parameters.add(new Parameter<>(3,this.unity_id));
            return Db.executeUpdate(stmt,parameters);
        }catch (Exception e){
            return 0;
        }
    }

    public int delete(int unity_id){
        try{
            return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_UNITY,List.of(
               new Parameter<>(1,unity_id)
            ));
        }catch (Exception e){
            return 0;
        }
    }
}
