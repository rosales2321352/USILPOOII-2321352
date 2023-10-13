package classes.db;

import classes.db.classes.MysqlConnection;
import classes.db.interfaces.IDb;
import classes.db.interfaces.IDbFactory;

public class DbMysql implements IDbFactory {
    @Override
    public IDb createDb() {
        return new MysqlConnection();
    }
}
