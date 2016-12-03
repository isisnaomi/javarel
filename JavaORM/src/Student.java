/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IsisNaomi
 */
public class Student extends Model{
    String name;
    int grade;



    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    
    
}
