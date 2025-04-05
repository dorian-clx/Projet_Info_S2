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
    private boolean deleted;  // Indicateur pour savoir si la machine est supprimée
    
    
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
    
    
    // Méthodes pour afficher les informations de la machine
    public String afficheMachine() {
        if (deleted) {
            return "La machine " + refMachine + " a été supprimée.";
        } else {
            return "Machine [refMachine = " + this.getRefMachine() + 
                   ", dMachine = " + this.getdMachine() + 
                   ", type = " + this.getType() + 
                   ", cout = " + this.getCout() + 
                   ", x = " + this.getX() + 
                   ", y = " + this.getY() + "]";
        }
    }

    // Méthode pour supprimer une machine
    public void supprimerMachine() {
        this.deleted = true;
        System.out.println("La machine " + this.refMachine + " a été supprimée.");
    }

    // Méthode pour modifier une machine
    public void modifierMachine(String newRefMachine, String newDMachine, String newType, double newCout, float newX, float newY) {
        if (!deleted) {
            this.refMachine = newRefMachine;
            this.dMachine = newDMachine;
            this.type = newType;
            this.cout = newCout;
            this.x = newX;
            this.y = newY;
            System.out.println("Les informations de la machine ont été mises à jour.");
        } else {
            System.out.println("Impossible de modifier la machine, elle a été supprimée.");
        }
    }
    
    
}
