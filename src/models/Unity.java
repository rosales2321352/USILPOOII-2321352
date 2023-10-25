package models;

import classes.db.Db;

import java.sql.ResultSet;
import java.util.ArrayList;
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
    public void setName(String name) {
        this.name = name;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Unity> getUnits(){
        try{
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_UNITIES);
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
}
