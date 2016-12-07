
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import view.Event;
import view.View;

/**
 *
 * @author RIKI
 */
public class Vista {
    private View v;
    
    public Vista(){
        v = new View();
    }
    
    public void metodoRandom(){
        Event ev = new Event();
        String a = "54";
        ev.addParameter(a);
        v.callService("azar", ev);
    }
}
