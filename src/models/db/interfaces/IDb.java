package models.db.interfaces;

import java.sql.Connection;

public interface IDb {
    public abstract Connection getInstance();
}
