package models.login;

import classes.db.Db;
import classes.db.classes.Parameter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Authenticate {

    public boolean autenticar(String user, String password) {


        try {
            // Consulta SQL para buscar el usuario y la contraseña en la base de datos
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            List<Parameter> params = new ArrayList<>();

                    params.add (new Parameter(1, user)); // Valor para username
                    params.add (new Parameter(2, password)); // Valor para password


            ResultSet resultSet = Db.executeQuery(sql, params);

            // Si se encuentra una fila, las credenciales son válidas
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de error de base de datos
            throw new RuntimeException(e); // Puedes mantener esta línea
        }

        return false; // Si no se encuentra un usuario con las credenciales, retorna falso
    }
}