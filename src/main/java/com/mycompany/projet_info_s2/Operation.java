/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;

/**
 *
 * @author doriancacheleux
 */
public class Operation {
    private String nomOp;
    private Equipement equipementAssocie; // Utilisation d'Equipement à la place de Poste
    private double duree;
    private double cout;
    private String typeOperation;         
    private int ordre;

    public String getNomOp() {
        return nomOp;
    }

    public void setNomOp(String nomOp) {
        this.nomOp = nomOp;
    }

    public Equipement getEquipementAssocie() {
        return equipementAssocie;
    }

    public void setEquipementAssocie(Equipement equipementAssocie) {
        this.equipementAssocie = equipementAssocie;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    // Getter pour récupérer le coût
    public double getCout() {
        return getCoutTotal(); // Utilise la méthode de calcul
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Operation(String nomOp, Equipement equipementAssocie, double duree, double cout, String typeOperation, int ordre) {
        this.nomOp = nomOp;
        this.equipementAssocie = equipementAssocie;
        this.duree = duree;
        this.cout = cout;
        this.typeOperation = typeOperation;
        this.ordre = ordre;
    }

    // Méthode pour afficher les caractéristiques d'une opération
    public String afficheOperation() {
        StringBuilder sb = new StringBuilder();
        sb.append("Opération #").append(ordre)
          .append(" [").append(typeOperation).append("] : ").append(nomOp)
          .append(" | Durée : ").append(duree).append("h")
          .append(" | Coût : ").append(getCoutTotal()).append("€")
          .append(" | Equipement associé : ");
        
        // Afficher le statut de l'équipement
        if (equipementAssocie == null) {
            sb.append("Aucun équipement associé");
        } else {
            sb.append(equipementAssocie.getRefEquipement()); // Pour éviter l'affichage complet de l'équipement
        }
        return sb.toString();
    }
    
    // Méthode pour calculer le coût de l'opération
    public double getCoutTotal() {
        double coutTotal = 0.0;
        
        if (equipementAssocie == null || equipementAssocie.getIsDeleted()) {
            return 0.0; // Si pas d'équipement ou supprimé, coût = 0
        }
        
        if (equipementAssocie instanceof Poste poste) {
            // Si c'est un poste, on calcule le coût en fonction des machines
            for (Machine machine : poste.getMachines()) {
                if (!machine.isDeleted() && 
                    machine.getEtat() != EtatMachine.EN_PANNE && 
                    machine.getEtat() != EtatMachine.EN_MAINTENANCE) {
                    // Ajouter le coût horaire de la machine multiplié par la durée
                    coutTotal += machine.getCout() * this.duree;
                }
            }
        } else if (equipementAssocie instanceof Machine machine) {
            // Si c'est une machine directe
            if (!machine.isDeleted() && 
                machine.getEtat() != EtatMachine.EN_PANNE && 
                machine.getEtat() != EtatMachine.EN_MAINTENANCE) {
                coutTotal += machine.getCout() * this.duree;
            }
        }
        
        return coutTotal;
    }
}