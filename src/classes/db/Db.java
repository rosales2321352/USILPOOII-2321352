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

    public static int executeUpdate(String sql, Parameter[] params) throws SQLException{
        Connection connection = Db.getInstance();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (var param : params) {
            setParameter(preparedStatement,param.getIndex(),param.getValue());
        }

        return preparedStatement.executeUpdate();
    }

    public static int executeUpdate(String sql) throws SQLException{
        return executeUpdate(sql,new Parameter[0]);
    }

    public static ResultSet executeQuery(String sql, Parameter[] params) throws SQLException{
        Connection connection = Db.getInstance();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (var param : params) {
            setParameter(preparedStatement,param.getIndex(),param.getValue());
        }
        return preparedStatement.executeQuery();
    }

    public static ResultSet executeQuery(String sql) throws SQLException{
        return  executeQuery(sql,new Parameter[0]);
    }

    public static void setParameter(PreparedStatement preparedStatement, int index, Object value) throws SQLException {
        if (value instanceof String) {
            preparedStatement.setString(index, (String) value);
        } else if (value instanceof Integer) {
            preparedStatement.setInt(index, (Integer) value);
        } else if (value instanceof Double) {
            preparedStatement.setDouble(index, (Double) value);
        } else {

        }
    }
}
