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
        IDbFactory factory = switch (ConnectionType) {
            case "Mysql" -> new DbMysql();
            case "Psql" -> new DbPsql();
            default -> null;
        };
        if(factory.createDb() != null){
            Connection conn = factory.createDb().getInstance();
            return conn;
        }
        return null;
    }

    public static ResultSet executeQuery(String sql, Parameter[] params) throws SQLException{
        Connection connection = Db.getInstance();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (Parameter param : params) {
            preparedStatement.setString(param.key_, param.value_);
        }
        return preparedStatement.executeQuery();
    }

    public static ResultSet executeQuery(String sql) throws SQLException{
        return  executeQuery(sql,new Parameter[0]);
    }
}
