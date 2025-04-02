/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;

/**
 *
 * @author jbugnet01
 */
public class Machine {
    
    // Attributs
    private String refMachine;
    private String dMachine;
    private String type;
    private double cout;
    private float x;
    private float y;
    
    
    // Getters et Setters
    public String getRefMachine() {
        return refMachine;
    }

    public void setRefMachine(String refMachine) {
        this.refMachine = refMachine;
    }

    public String getdMachine() {
        return dMachine;
    }

    public void setdMachine(String dMachine) {
        this.dMachine = dMachine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
    // Constructeur
    public Machine(String refMachine, String dMachine, String type, double cout, float x, float y) {
        this.refMachine = refMachine;
        this.dMachine = dMachine;
        this.type = type;
        this.cout = cout;
        this.x = x;
        this.y = y;
    }
    
    
    // Méthodes
    public String afficheMachine() {
            return "Machine [refMachine = " + this.getRefMachine() + 
                              ", dMachine = " + this.getdMachine() + 
                              ", type = " + this.getType() + 
                              ", cout = " + this.getCout() + 
                              ", x = " + this.getX() + 
                              ", y = " + this.getY() + "]";
        }
    
    
    
}
