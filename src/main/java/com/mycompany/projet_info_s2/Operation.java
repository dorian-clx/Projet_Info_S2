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

    public double getCout() {
        return cout;
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
          .append(" | Coût : ").append(cout).append("€")
          .append(" | Equipement associé : ");

        // Afficher le statut de l'équipement
        if (equipementAssocie == null) {
            sb.append("Aucun équipement associé");
        } else {
            sb.append(equipementAssocie.afficheEquipement());  // Appel à la méthode d'affichage d'Equipement
        }
        return sb.toString();
    }
}