package models.CurrencyConversion;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.ResultSet;
import java.util.Date;

public class CurrencyConversion  {
    private int currency_id;
    private int conversion_id;

    private Date date;

    private double buy;

    private double sale ;
    public String dateString;

    public String getDateString() {
        return dateString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }


    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }

    public String getConversion_id() {
        return String.valueOf(conversion_id);
    }

    public void setConversion_id(int conversion_id) {
        this.conversion_id = conversion_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public List<CurrencyConversion> getCurrencyConversions(String query){
        try{
            ResultSet result;
            if(query.trim().isEmpty()){
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_CURRENCYCONVERSIONS);
            }else{
                query = '%'+query+'%';
                result = Db.executeQuery(ModelSQL.SQL_STMT_GET_CURRENCYCONVERSION_BY_LIKE,Arrays.asList(
                        new Parameter<>(1,query),
                        new Parameter<>(2,query),
                        new Parameter<>(3,query),
                        new Parameter<>(4,query)
                        //new Parameter<>(5,query)
                ));
            }
            List<CurrencyConversion> currencyConversions = new ArrayList<>();
            while (result.next()) {
                CurrencyConversion currencyconversion = new CurrencyConversion();
                currencyconversion.setCurrency_id(result.getInt("currency_id"));
                java.util.Date dateFromDB = result.getDate("date");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateFromDB);
                calendar.add(Calendar.HOUR_OF_DAY, -calendar.getTimeZone().getRawOffset() / (60 * 60 * 1000));
                java.util.Date adjustedDate = new java.util.Date(calendar.getTimeInMillis());
                currencyconversion.setDate(adjustedDate);
                currencyconversion.setName(result.getString("name"));
                currencyconversion.setConversion_id(result.getInt("conversion_id"));
                currencyconversion.setBuy(result.getDouble("buy"));
                currencyconversion.setSale(result.getDouble("sale"));
                currencyConversions.add(currencyconversion);
            }

            /*while (result.next()){
                CurrencyConversion currencyconversion = new CurrencyConversion();
                currencyconversion.setCurrency_id(result.getInt("currency_id"));
                Timestamp tmp= result.getTimestamp("date");
                currencyconversion.setDate(new Date(tmp.getTime()));
                currencyconversion.setDate(result.getDate("date"));
                currencyconversion.setConversion_id(result.getInt("conversion_id"));
                currencyconversion.setBuy(result.getDouble("buy"));
                currencyconversion.setSale(result.getDouble("sale"));
                currencyConversions.add(currencyconversion);
            }*/
            result.getStatement().close();
            result.close();
            return currencyConversions;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public CurrencyConversion getCurrencyConversion(int currency_id){
        try{
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_CURRENCYCONVERSION, List.of(
                    new Parameter<>(1, currency_id)
            ));
            CurrencyConversion currencyConversion = new CurrencyConversion();
            while(result.next()){
                CurrencyConversion currencyconversion = new CurrencyConversion();
                currencyconversion.setCurrency_id(result.getInt("currency_id"));
                currencyconversion.setDate(result.getDate("date"));
                currencyconversion.setConversion_id(result.getInt("conversion_id"));
                currencyconversion.setName(result.getString("name"));
                currencyconversion.setBuy(result.getDouble("buy"));
                currencyconversion.setSale(result.getDouble("sale"));

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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(date);
            String stmt = ModelSQL.SQL_STMT_INSERT_CURRENCYCONVERSION;
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1, this.currency_id));
            parameters.add(new Parameter<>(2, dateString));
            //parameters.add(new Parameter<>(3, this.conversion_id));
            parameters.add(new Parameter<>(3, this.buy));
            parameters.add(new Parameter<>(4, this.sale));
            if (this.conversion_id != 0) {
                stmt = ModelSQL.SQL_STMT_UPDATE_CURRECNYCONVERSION;
                parameters.add(new Parameter<>(5, this.conversion_id));
            }
            return Db.executeUpdate(stmt,parameters);
        }catch(Exception e){
            return 0;
        }
    }


    /*public int delete(int currency_id){
        try{
            return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_Currency,List.of(
                    new Parameter<>(1,currency_id)
            ));
        }catch (Exception e){
            return 0;
        }
    }*/




}

