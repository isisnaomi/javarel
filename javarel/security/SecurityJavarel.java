/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javarel.security;

import javarel.security.exceptions.UserDoesntExist;
import javarel.security.exceptions.PasswortGivenNotSafe;

import java.lang.reflect.Method;
import javarel.ORM.Model;

import javarel.Log.exceptions.ClassNotFoundException;
import javarel.security.exceptions.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jasypt.util.password.BasicPasswordEncryptor;


public class SecurityJavarel {
    
    int countAttempts;
    
    int maxAttemps;

    public SecurityJavarel(int maxAttemps) {
        this.countAttempts = countAttempts;
        this.maxAttemps = maxAttemps;
    }

    public int getCountAtempts() {
        return countAttempts;
    }

    public void setCountAtempts(int countAtempts) {
        this.countAttempts = countAtempts;
    }

    public int getMaxAttemps() {
        return maxAttemps;
    }

    public void setMaxAttemps(int maxAttemps) {
        this.maxAttemps = maxAttemps;
    }
    
    public void resetCountAttemp(){
        this.countAttempts = 0;
    }
    
    
    public boolean isSafty(String password) throws PasswortGivenNotSafe{
        
        try {
            
            if(password.length() >= 8){

                Pattern pat = Pattern.compile("^[\\w-]+");
                Matcher mat = pat.matcher(password);

                if (mat.find()) {
                    System.out.println("Contraseña valida");
                    return true;
                }else{
                    System.out.println("Contraseña no valida");
                    return false;
                }

            }else{
                System.out.println("contraseña no segura");
                return false;
            }
            
        } catch (Exception e) {
            throw new PasswortGivenNotSafe();
        }
    }
    
    public AdminLoginJavarel validateUser(String userType, String userName, String userPasswordInput)
            throws ClassNotFoundException, MaximumLimitOfAttemps{
        
        try {
            
            
            Class cls = Class.forName(userType);
            Object userObj = cls.newInstance();
            
            DefaultUser dUser = new DefaultUser();
            dUser.initialize();
            
            Class noparams[] = {};
            
            Model model = (Model) userObj;
            
            Method method = model.getClass().getMethod("initialize", noparams);
            
            method.invoke(model, noparams);
            
            Method methodAll = model.getClass().getMethod("all", noparams);
            
            ArrayList<Object> users = new ArrayList<Object>((ArrayList<Object>)methodAll.invoke(model, (Object[]) noparams));
            
            AdminLoginJavarel adminLoginJavarel2 = null;

            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

            String encryptedPasswordInput =passwordEncryptor.encryptPassword(userPasswordInput);

            for (Object user : users) {
                
                String userNameORM = ((DefaultUser) user).getName();
 
                    if (userNameORM.equals(userName)) {
                        
                        String userPasswordORM = ((DefaultUser) user).getPassword();

                        while (getCountAtempts() <= getMaxAttemps()) {                        
                            if (encryptedPasswordInput.equals(userPasswordORM)) {
                                adminLoginJavarel2 = new AdminLoginJavarel((DefaultUser)user, true, userName);
                                break;
                            }else{
                                System.out.println("Contraseña incorrecta, intentar de nuevo");
                                setCountAtempts(getCountAtempts() + 1);
                            }
                        }

                        if (getCountAtempts() == 3) {
                            adminLoginJavarel2 = null;
                            throw new MaximumLimitOfAttemps();
                        }

                        break;
                    }else{
                        System.out.println("El usuario no existe");
                        adminLoginJavarel2 = null;
                        throw new UserDoesntExist();
                    }
                }
            return adminLoginJavarel2;

        } catch (Exception e) {
            throw new ClassNotFoundException();
        }
    }
    
    
    
    
}
