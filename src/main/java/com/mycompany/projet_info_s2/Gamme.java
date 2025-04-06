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
    
    public boolean peutEtreRealisee() {
    for (Operation op : operations) {
        if (op.getPosteAssocie().getIsDeleted()) {
            return false;
        }
    }
    return true;
}

public String getStatusRealisation() {
    StringBuilder sb = new StringBuilder();
    int operationsNonRealisables = 0;
    int totalOperations = operations.size();
    
    sb.append("Statut de réalisation:\n");
    
    for (Operation op : operations) {
        if (op.getPosteAssocie().getIsDeleted()) {
            sb.append(" - Opération #").append(op.getOrdre())
              .append(" : NON RÉALISABLE (poste supprimé)\n");
            operationsNonRealisables++;
        } else {
            sb.append(" - Opération #").append(op.getOrdre())
              .append(" : Réalisable\n");
        }
    }
    
    // Verdict final avec plus de détails
    if (operationsNonRealisables == 0) {
        sb.append("Verdict: Gamme entièrement réalisable");
    } else if (operationsNonRealisables == totalOperations) {
        sb.append("Verdict: Gamme totalement non réalisable");
    } else {
        sb.append("Verdict: Gamme partiellement réalisable (")
          .append(totalOperations - operationsNonRealisables)
          .append(" opérations sur ")
          .append(totalOperations)
          .append(" sont réalisables)");
    }
    
    return sb.toString();
}

public double getPourcentageRealisation() {
    if (operations.isEmpty()) {
        return 0.0;
    }
    
    int operationsRealisables = 0;
    for (Operation op : operations) {
        if (!op.getPosteAssocie().getIsDeleted()) {
            operationsRealisables++;
        }
    }
    
    return (double) operationsRealisables / operations.size() * 100;
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
    sb.append("Pourcentage de réalisation : ").append(String.format("%.1f", getPourcentageRealisation())).append("%\n");
    sb.append("\n").append(getStatusRealisation()).append("\n");
    return sb.toString();
}
}
