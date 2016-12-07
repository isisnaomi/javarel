/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;

/**
 *
 * @author RIKI
 */
public class Event {
    private ArrayList<String> parametersList = new ArrayList<>();
    
    public Event(){
        
    }
    
    public Event(ArrayList<String> parametersList){
        this.parametersList = parametersList;
    }
    
    public ArrayList<String> getParametersList(){
        return parametersList;
    }
    
    public void addParameter(String parameter){
        this.parametersList.add(parameter);
    }
}
