package core.models;

public class Auth {

    private static Auth instance;
    private boolean isAuthenticated;

    private Auth(){}

    public static Auth getInstance(){
        if(instance == null){
            instance = new Auth();
        }
        return  instance;
    }

    public boolean isAuthenticated(){
        return this.isAuthenticated;
    }
    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

}
