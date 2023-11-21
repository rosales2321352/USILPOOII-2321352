package models.user;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import javax.print.DocFlavor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private Integer user_id;
    private String username;
    private String password;
    private Integer outstanding;


    public User() {
    }

    public Integer getUser_id() {
        return this.user_id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Integer getOutstanding() {
        return this.outstanding;
    }


    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOutstanding(Integer outstanding) {
        this.outstanding = outstanding;
    }

    public List<User> getListUsers(String query){
        try {
            String sql = query.trim().isEmpty()? ModelSQL.SQL_STMT_GET_USER:ModelSQL.SQL_STMT_GET_USER_BY_LIKE;
            ResultSet result;
            if(query.trim().isEmpty()) {
                result = Db.executeQuery(sql);
            }else{
                query = "%"+query+"%";
                result = Db.executeQuery(sql, Arrays.asList(
                        new Parameter<>(1,query),
                        new Parameter<>(2,query),
                        new Parameter<>(3,query),
                        new Parameter<>(4,query)
                ));
            }

            List<User> userList = new ArrayList<>();
            while (result.next()){
                var user = new User();
                user.setUser_id(result.getInt("user_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                //ROL
                userList.add(user);
            }
            result.getStatement().close();
            result.close();
            return userList;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public User getUser(int user_id){
        try {
            var result = Db.executeQuery(ModelSQL.SQL_STMT_GET_USER_ID,List.of(
                    new Parameter<>(1,user_id)
            ));
            User user = new User();
            while (result.next()){
                user.setUser_id(result.getInt("user_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                //FALTA ROL
            }
            return user;
        }catch (Exception e){
            return new User();
        }
    }

    public int save(){
        try{
            String query = this.user_id==0? ModelSQL.SQL_STMT_INSERT_USER:ModelSQL.SQL_STMT_UPDATE_USER;
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1,this.username));
            parameters.add(new Parameter<>(2,this.password));
            parameters.add(new Parameter<>(3,this.outstanding));
            //parameters.add(new Parameter<>(1,this.rol_id));
            if(this.user_id !=0) parameters.add(new Parameter<>(4,this.user_id));
            return Db.executeUpdate(query,parameters);
        }catch (Exception e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,e);
            return 0;
        }
    }

    public int delete(){
        try{
            List<Parameter> parameters = List.of(
                    new Parameter<>(1,this.user_id)
            );
            return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_USER,parameters);
        }catch (Exception e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,e);
            return 0;
        }
    }

}

