/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.Event;

/**
 *
 * @author RIKI
 */
public interface Controller {
    
    public void create(Event event);
    
    public void update(Event event);
    
    public void destroy(Event event);
    
    public String[] read(Event event);
}
