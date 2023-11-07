package models.db;

import models.db.classes.PsqlConnection;
import models.db.interfaces.IDb;
import models.db.interfaces.IDbFactory;

public class DbPsql implements IDbFactory {
    @Override
    public IDb createDb() {
        return new PsqlConnection();
    }
}
