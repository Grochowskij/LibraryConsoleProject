package project;

public class Admins {
    private String username;
    private String password;
    
    public Admins(){
        username = null;
        password = null;
    }
    
    public Admins(String a, String b){
        username = a;
        password = b;
    }
    
    public boolean compareTo(String a, String b){
        boolean verify = false;
        if(username.equals(a)){
            if(password.equals(b)){
                verify = true;
            }
        }
        return verify;
    }
}
