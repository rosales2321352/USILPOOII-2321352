package controllers.controller;

import models.login.Authenticate;

public class Controller {
    private Authenticate authenticate;

    public Controller() {
        authenticate = new Authenticate();
    }

    public boolean iniciarSesion(String usuario, String contrasena) {
        // Llamar al modelo para verificar las credenciales
        return authenticate.autenticar(usuario, contrasena);
    }
}