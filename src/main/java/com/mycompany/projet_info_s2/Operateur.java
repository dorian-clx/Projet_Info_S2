/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author doriancacheleux
 */
public class Operateur {
    
    //attributs
    private String code;           // Identifiant unique de l'opérateur
    private String nom;
    private String prenom;
    private List<String> competences; // Références des machines que l'opérateur sait utiliser
    private boolean estOccupe;     // true si l'opérateur est en train de travailler
    
    //getters et setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }

    public boolean isEstOccupe() {
        return estOccupe;
    }

    public void setEstOccupe(boolean estOccupe) {
        this.estOccupe = estOccupe;
    }

    //constructeur
    public Operateur(String code, String nom, String prenom, List<String> competences, boolean estOccupe) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.competences = new ArrayList<>(competences);
        this.estOccupe = false;
    }
    
    //méthode afficher carastéristiques d'un opérateur
    public String afficherOperateur() {
        return "Opérateur [Code: " + code + ", Nom: " + nom + " " + prenom +
           ", Compétences: " + competences +
           ", Statut: " + (estOccupe ? "Occupé" : "Libre") + "]";
    }
    
    //vérifier si un opérateur est qualifié pour une machine donnée
    public boolean peutUtiliserMachine(String refMachine) {
        return competences.contains(refMachine);
    }
    
    //méthode pour changer le statut d'un opérateur
    public void setOccupe(boolean occupe) {
        this.estOccupe = occupe;
    }
    
    //méthode pour ajouter une compétence
    public void ajouterCompetence(String refMachine) {
        if (!competences.contains(refMachine)) {
            competences.add(refMachine);
        }
    }
}
