package models.CurrencyConversion;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrencyConversion  {
    private int currency_id;
    private String conversion_id;

    private Date date;

    private String buy;

    private String sale ;

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }

    public String getConversion_id() {
        return conversion_id;
    }

    public void setConversion_id(String conversion_id) {
        this.conversion_id = conversion_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public List<CurrencyConversion> getCurrencyConversions(String query){
        try{
            ResultSet result;
            if(query.trim().isEmpty()){
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_Currencies);
            }else{
                query = '%'+query+'%';
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_Currency_BY_LIKE,Arrays.asList(
                        new Parameter<>(1,query),
                        new Parameter<>(2,query),
                        new Parameter<>(3,query)


                ));
            }

            List<CurrencyConversion> currencyConversions = new ArrayList<>();
            while (result.next()){
                CurrencyConversion currencyconversion = new CurrencyConversion();
                currencyconversion.setCurrency_id(result.getInt("currency_id"));
                currencyconversion.setDate(result.getDate("Date"));
                currencyconversion.setConversion_id(result.getString("converion_id"));
                currencyconversion.setBuy(result.getString("buy"));
                currencyconversion.setSale(result.getString("sale"));
                currencyConversions.add(currencyconversion);
            }
            result.getStatement().close();
            result.close();
            return currencyConversions;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public CurrencyConversion getCurrencyConversion(int currency_id){
        try{
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_Currency, List.of(
                    new Parameter<>(1, currency_id)
            ));
            CurrencyConversion currencyConversion = new CurrencyConversion();
            while(result.next()){
                CurrencyConversion currencyconversion = new CurrencyConversion();
                currencyconversion.setCurrency_id(result.getInt("currency_id"));
                currencyconversion.setDate(result.getDate("Date"));
                currencyconversion.setConversion_id(result.getString("converion_id"));
                currencyconversion.setBuy(result.getString("buy"));
                currencyconversion.setSale(result.getString("sale"));
            }
            result.getStatement().close();
            result.close();
            return currencyConversion;
        }catch(Exception e){
            return new CurrencyConversion();
        }
    }
    public int save() {
        try {
            String stmt = ModelSQL.SQL_STMT_INSERT_Currency;
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1, this.currency_id));
            parameters.add(new Parameter<>(2, this.date));
            parameters.add(new Parameter<>(3, this.conversion_id));
            parameters.add(new Parameter<>(4, this.buy));
            parameters.add(new Parameter<>(5, this.sale));
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




}

