/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.test;

import java.util.ArrayList;

public class ConnectionTestImplementation {

    public static void main(String[] args) {
        
        try {
            Student std = new Student();
            std.initialize();
            ArrayList<Object> students = new ArrayList<Object>(std.all());

            System.out.println("El tamaño de estudents es " + students.size());
            for (Object student : students) {
                System.out.println(((Student) student).getName());
                System.out.println(((Student) student).getId());
            }
        } catch (Exception ex) {
           ex.printStackTrace();
            
        }
    }

}
