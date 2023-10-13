package models.db.classes;

import models.db.interfaces.IDb;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlConnection implements IDb {

    private Connection instance;

    @Override
    public Connection getInstance(){
        if(instance == null){
            try {
                this.instance = connection();
            }catch (SQLException ex){
                Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        return this.instance;
    }

    public Connection connection() throws SQLException{
        String user = "allpccom_usilpooii_db";
        String pass = "$rqeGI~qVaL!";
        String url  = "jdbc:mysql://106.0.62.80:3306/allpccom_usilpooii_db?useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
        DriverManager.registerDriver(new Driver());
        Connection conn = DriverManager.getConnection(url,user,pass);
        return conn;
    }
}
