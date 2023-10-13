package controllers.login;

import core.models.Auth;
import models.login.Authenticate;
import views.admin.AdminView;
import views.dashboard.Dashboard;
import views.login.LoginView;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginController {

    final private LoginView panel;
    public LoginController(LoginView panel){
        this.panel = panel;
    }

    private boolean validate(){
        String userName = this.panel.txtUserName.getText();
        String password = new String(this.panel.txtPassword.getPassword());
        if(userName.trim().isEmpty()){
            return false;
        }
        if(password.trim().isEmpty()){
            return false;
        }
        return true;
    }

    public void onClickBtnLogin(ActionEvent e){
        if(validate()) {
            String userName = this.panel.txtUserName.getText();
            String password = new String(this.panel.txtPassword.getPassword());

            if(Authenticate.authenticate(userName,password)){
                var auth = Auth.getInstance();
                auth.setAuthenticated(true);
                Dashboard d = new Dashboard();
                this.panel.dispose();
            }
        }
    }



}
