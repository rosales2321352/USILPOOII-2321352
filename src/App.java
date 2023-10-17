import views.login.ViewLogin;

import javax.swing.*;
import javax.swing.text.View;

public class App {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewLogin view = new ViewLogin();
                view.setVisible(true);
            }
        });
    }
}