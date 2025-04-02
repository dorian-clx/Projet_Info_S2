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

    
    //constructeur 
    public Produit(int codePro, String desPro) {
        this.codePro = codePro;
        this.desPro = desPro;
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
    
    //méthode afficher produit 
    public String afficherProduit (){
        return "Produit [code produit : " + this.codePro + 
               ", désignation produit : " + this.desPro + "]";
    }
          
}
