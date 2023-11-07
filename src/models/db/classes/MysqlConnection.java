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
        String user = "usil_root";
        String pass = "675&mF{~D-P=";
        String url  = "jdbc:mysql://usilpooii.mysql.database.azure.com:3306/usilpooii_db?useTimezone=true&serverTimezone=UTC&useSSL=true&allowPublicKeyRetrieval=true";
        DriverManager.registerDriver(new Driver());
        Connection conn = DriverManager.getConnection(url,user,pass);
        return conn;
    }
}
