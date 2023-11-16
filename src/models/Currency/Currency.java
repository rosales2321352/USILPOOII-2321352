package models.Currency;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Currency {
    private int currency_id;
    private String name;

    private String symbol;

    private String iso_code;

    private String location;

    private int predeterminanted;


    public Currency(){}

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
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

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Currency> getCurrencies(String query){
        try{
            ResultSet result;
            if(query.trim().isEmpty()){
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_Currencies);
            }else{
                query = '%'+query+'%';
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_Currency_BY_LIKE,Arrays.asList(
                        new Parameter<>(1,query),
                        new Parameter<>(2,query)
                ));
            }

            List<Currency> currencies = new ArrayList<>();
            while (result.next()){
                Currency currency = new Currency();
                currency.setCurrency_id(result.getInt("currency_id"));
                currency.setName(result.getString("name"));
                currency.setSymbol(result.getString("symbol"));
                currency.setIso_code(result.getString("iso_code"));
                currency.setLocation(result.getString("location"));
                currency.setPredeterminanted(result.getInt("predetermined"));
                currencies.add(currency);
            }
            result.getStatement().close();
            result.close();
            return currencies;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Currency getCurrency(String name){
        try{
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_Currency, List.of(
                    new Parameter<>(1, name)
            ));
            Currency currency = new Currency();
            while(result.next()){
                currency.setCurrency_id(result.getInt("currency_id"));
                currency.setName(result.getString("name"));
                currency.setSymbol(result.getString("symbol"));
                currency.setIso_code(result.getString("iso_code"));
                currency.setLocation(result.getString("location"));
                currency.setPredeterminanted(result.getInt("predetermined"));
            }
            result.getStatement().close();
            result.close();
            return currency;
        }catch(Exception e){
            return new Currency();
        }
    }
    public int save() {
        try {
            String stmt = ModelSQL.SQL_STMT_INSERT_Currency;
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1, this.name));
            parameters.add(new Parameter<>(2, this.symbol));
            parameters.add(new Parameter<>(3, this.iso_code));
            parameters.add(new Parameter<>(4, this.location));
            parameters.add(new Parameter<>(5, this.predeterminanted));
            if (this.currency_id != 0) {
                stmt = ModelSQL.SQL_STMT_UPDATE_Currency;
                parameters.add(new Parameter<>(6, this.currency_id));
            }
            return Db.executeUpdate(stmt,parameters);
        }catch(Exception e){
                return 0;
            }
        }


    public int delete(int currency_id){
        try{
            return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_Currency,List.of(
                    new Parameter<>(1,currency_id)
            ));
        }catch (Exception e){
            return 0;
        }
    }

    public int getPredeterminanted() {
        return predeterminanted;
    }

    public void setPredeterminanted(int predeterminanted) {
        this.predeterminanted = predeterminanted;
    }
}

