package classes.db;

import classes.db.classes.PsqlConnection;
import classes.db.interfaces.IDb;
import classes.db.interfaces.IDbFactory;

public class DbPsql implements IDbFactory {
    @Override
    public IDb createDb() {
        return new PsqlConnection();
    }
}
