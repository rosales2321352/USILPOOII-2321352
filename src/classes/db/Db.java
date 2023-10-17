package classes.db;

import classes.db.classes.Parameter;
import classes.db.interfaces.IDbFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db {
    final static String ConnectionType = "Mysql";

    public static Connection getInstance() throws SQLException {
        IDbFactory factory = null;
        if("Mysql".equals(ConnectionType)){
            factory = new DbMysql();
        }
        if("Psql".equals(ConnectionType)){
            factory = new DbPsql();
        }

        if(factory.createDb() != null){
            Connection conn = factory.createDb().getInstance();
            return conn;
        }
        return null;
    }

    public static ResultSet executeQuery(String sql, Parameter[] params) throws SQLException{
        PreparedStatement preparedStatement = Db.getInstance().prepareStatement(sql);
        if(params.length > 0){
            for(Parameter param: params){
                preparedStatement.setString(param.key_,param.value_);
            }
        }
        return preparedStatement.executeQuery();
    }

    public static ResultSet executeQuery(String sql) throws SQLException{
        return  executeQuery(sql,new Parameter[0]);
    }

    public static void CloseConnection() throws SQLException{
        Connection conn = Db.getInstance();
        conn.close();
    }
}
