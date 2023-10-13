package models.login;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static models.ModelSQL.*;

public class Authenticate {

    public static boolean authenticate(String user, String password) {
        try {
            ResultSet resultSet = Db.executeQuery(SQL_STMT_AUTHENTICATED, Arrays.asList(
                    new Parameter<>(1, user),
                    new Parameter<>(2, password)
            ));
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }
}
