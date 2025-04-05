/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dcacheleux01
 */
public class Produit {
    //attributs
    private int codePro;
    private String desPro;
    private boolean isActive; //attribut pour indiquer si le produit est actif

    
    //constructeur 
    public Produit(int codePro, String desPro) {
        this.codePro = codePro;
        this.desPro = desPro;
        this.isActive = true;  // Le produit est actif par défaut
    }
    //getters setters 
    public int getCodePro() {
        return codePro;
    }

    public void setCodePro(int codePro) {
        this.codePro = codePro;
    }

    public String getDesPro() {
        return desPro;
    }

    public void setDesPro(String desPro) {
        this.desPro = desPro;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    // Méthode pour marquer le produit comme supprimé
    public void supprimerProduit() {
        this.isActive = false;
        System.out.println("Le produit " + this.codePro + " a été supprimé.");
    }
    
    //méthode afficher produit 
    public String afficherProduit (){
        if (isActive) {
            return "Produit [code produit : " + this.codePro + ", désignation produit : " + this.desPro + "]";
        } else {
            return "Produit supprimé.";
        }
    }
    
    //méthode ajouter produit 
    public void modifierProduit(int newCodePro, String newDesPro) {
        this.codePro = newCodePro;
        this.desPro = newDesPro;
    }
          
}
