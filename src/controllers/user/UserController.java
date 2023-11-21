package controllers.user;

import models.user.User;
import views.user.UserView;
import views.user.partials.UserAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class UserController {
    final private UserView panel;
    private int user_id=0;
    final String[] COLUMN_NAMES = { "Id","Usuario","Contraseña","Acciones"};
    public UserController(UserView panel) { this.panel = panel; }
    public void renderObjects() { this.panel.userList.makeTableHeader(COLUMN_NAMES);}

    public void loadDataTableAsync(String query){
        CompletableFuture<List<User>> futureTableUser = CompletableFuture.supplyAsync(() -> {
            return new User().getListUsers(query);
        });
        futureTableUser.thenAcceptAsync(users -> {
            Object[][] information = users.stream()
                    .map(user -> new Object[] { String.valueOf(user.getUser_id()),user.getUsername(),user.getPassword()})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.userList.makeTable(information,COLUMN_NAMES));
        });
    }

    /*public void loadDataComboBoxAsync(){
        CompletableFuture<List<DocumentType>> futurePerson = CompletableFuture.supplyAsync(new DocumentType()::getDocuments);


        futurePerson.thenAcceptAsync(documentTypes -> SwingUtilities.invokeLater(() -> {
            CustomComboBox<DocumentType> comboBoxModel = new CustomComboBox<>(documentTypes);
            panel.personEditor.cmbDocumentType.setModel((ComboBoxModel<DocumentType>) comboBoxModel);
        }));
    }*/

    public Integer saveUser(){
        //ROL
        String username = panel.userEditor.txtUsername.getText();
        String password = panel.userEditor.txtPassword.getText();

        User user = new User();
        user.setUser_id(this.user_id);
        user.setUsername(username);
        user.setPassword(password);
        user.setOutstanding(0);

        return user.save();
    }

    public Integer deleteUser(){
        User user = new User();
        user.setUser_id(this.user_id);
        return user.delete();
    }

    public void resetControls(){
        this.user_id=0;
        panel.userEditor.txtUsername.setText("");
        panel.userEditor.txtPassword.setText("");
    }

    public void onClickSearchPerson(ActionEvent e){
        String query = panel.userList.txtQuery.getText();
        if (query != null){
            this.loadDataTableAsync(query);
        }
    }

    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }


    public void onClickDeleteAction(ActionEvent e, Object id){
        this.user_id = (int)id;
        int response = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de eliminar el usuario" ,
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION){
            Integer rowsAffected = this.deleteUser();

            if(rowsAffected < 1){
                JOptionPane.showMessageDialog(null,
                        "",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,
                        "Usuario eliminado correctamente",
                        "Atencion",JOptionPane.INFORMATION_MESSAGE);
            }
            this.loadDataTableAsync("");
        }
        this.user_id = 0;

    }

    public void onClickEditAction(ActionEvent e, Object id){
        CompletableFuture<User> futureUser = CompletableFuture.supplyAsync(() -> new User().getUser((int) id));
        futureUser.thenAcceptAsync(user -> SwingUtilities.invokeLater(() -> {
            this.user_id = user.getUser_id();
            panel.userEditor.txtUsername.setText(user.getUsername());
            panel.userEditor.txtPassword.setText(user.getPassword());
        }));
        this.switchTab((JButton) e.getSource());
    }

    public boolean validateControlsValue(){
        String username = panel.userEditor.txtUsername.getText();
        String password = panel.userEditor.txtPassword.getText();

        if (username.trim().isEmpty()){
            return false;
        }
        if (password.trim().isEmpty()){
            return false;
        }

        return true;
    }

    public void onClickSaveAction(ActionEvent e) {
        if(this.validateControlsValue()) {
            String message = this.user_id == 0? "Estás seguro de crear el usuario?" : "Estás seguo de actualizar el usuario?";
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmacion", JOptionPane.YES_NO_OPTION);

            if (response== JOptionPane.YES_OPTION){
                Integer rowsAffected = saveUser();
                if (rowsAffected<1){
                    JOptionPane.showMessageDialog(
                            null,
                            "No se pudo guardar el usuario." ,
                            "Atencion",JOptionPane.INFORMATION_MESSAGE);
                }
                this.loadDataTableAsync("");
                this.resetControls();
                this.switchTab((JButton) e.getSource());
            }
        }
    }

    public void onClickCancelAction(ActionEvent e) {
        String message = "¿Está seguro de cancelar la operación?";
        int response = JOptionPane.showConfirmDialog(null, message, "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            this.resetControls();
            this.switchTab((JButton) e.getSource());
        }
    }

    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent,command);
    }

}
