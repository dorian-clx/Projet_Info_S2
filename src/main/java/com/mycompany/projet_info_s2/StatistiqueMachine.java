/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;

/**
 *
 * @author cypri
 */
public class StatistiqueMachine {
    private String idMachine;
    private double tempsArret = 0.0; // en minutes
    private double tempsObservation = 0.0; // en minutes
    private int joursActifs = 0;

    public StatistiqueMachine(String id) {
        this.idMachine = id;
    }

    public void ajouterArret(double minutes) {
        this.tempsArret += minutes;
    }

    public void ajouterJourObservation() {
        this.joursActifs++;
        this.tempsObservation = 840.0 * joursActifs; // 14h * 60min
    }

    public double getFiabilite() {
        if (tempsObservation == 0) return 1.0;
        return 1.0 - (tempsArret / tempsObservation);
    }

    public String getIdMachine() {
        return idMachine;
    }

    @Override
    public String toString() {
        return "Machine " + idMachine + " - Fiabilité : " + String.format("%.2f", getFiabilite() * 100) + "%";
    }
}

