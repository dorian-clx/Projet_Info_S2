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
public class Gamme {
    
    //attributs
    private String idGamme;
    private Produit produit;
    private List<Operation> operations; 

    //getters et setters
    public String getIdGamme() {
        return idGamme;
    }

    public void setIdGamme(String idGamme) {
        this.idGamme = idGamme;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
    
    //constructeur 
    public Gamme(String idGamme, Produit produit, List<Operation> operations) {
        this.idGamme = idGamme;
        this.produit = produit;
        this.operations = operations;
    }
    
    // Méthodes utiles
    public void ajouterOperation(Operation op) {
        operations.add(op);
    }

    public void supprimerOperation(Operation op) {
        operations.remove(op);
    }

    public double getDureeTotale() {
        double total = 0;
        for (Operation op : operations) {
            total += op.getDuree();
        }
        return total;
    }

    public double getCoutTotal() {
        double total = 0;
        for (Operation op : operations) {
            total += op.getCout();
        }
        return total;
    }
    
    public String afficherGamme() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gamme ID: ").append(idGamme).append(" (Produit: ").append(produit.getDesPro()).append(")\n");
        sb.append("Opérations :\n");
        for (Operation op : operations) {
            sb.append(" - ").append(op.afficheOperation()).append("\n");
        }
        sb.append("Durée totale : ").append(getDureeTotale()).append("h\n");
        sb.append("Coût total : ").append(getCoutTotal()).append("€\n");
        return sb.toString();
    }
}
