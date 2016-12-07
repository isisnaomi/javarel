/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.Controller;
import view.Event;

/**
 *
 * @author RIKI
 */
public class CtrlAlumnos implements Controller{

    @Override
    public void create(Event event) {
        int i = Integer.parseInt(event.getParametersList().get(0));
        System.out.println("El número es: ");
        System.out.println(i);
    }

    @Override
    public void update(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] read(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
