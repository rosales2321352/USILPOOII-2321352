package models.db;

import models.db.classes.MysqlConnection;
import models.db.interfaces.IDb;
import models.db.interfaces.IDbFactory;

public class DbMysql implements IDbFactory {
    @Override
    public IDb createDb() {
        return new MysqlConnection();
    }
}
