/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.security;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminLoginJavarel {
    
//    Student currentStundent;
    
    DefaultUser currentUser;
    
    boolean isNew;
    
    String permiso;


    public AdminLoginJavarel(DefaultUser currentUser, boolean isNew, String permiso) {
        this.currentUser = currentUser;
        this.isNew = isNew;
        this.permiso = permiso;
    }

    public AdminLoginJavarel() {
    }
    
    

    public Object getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(DefaultUser currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public String getLicence() {
        return permiso;
    }

    public void setLicence(String permiso) {
        this.permiso = permiso;
    }

    
    
}
