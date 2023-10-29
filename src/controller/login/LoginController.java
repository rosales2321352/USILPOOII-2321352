package controller.login;

import model.login.Authenticate;
import view.admin.AdminView;

public class LoginController {
    private Authenticate authenticate;

    public LoginController() {
        authenticate = new Authenticate();
    }

    public boolean iniciarSesion(String usuario, String contrasena) {
        boolean resultado = authenticate.autenticar(usuario, contrasena);
        if (resultado) {
            mostrarVistaDelAdministrador();
            // Inicio de sesión exitoso, realiza acciones adicionales aquí
            // Por ejemplo, abre una nueva ventana o cambia la vista.
            // También puedes comunicarte con la vista para realizar estos cambios.

        } else {

        }

        return resultado;
    }

    private void mostrarVistaDelAdministrador() {
        AdminView adminView = new AdminView();
        adminView.setVisible(true);
    }
}
