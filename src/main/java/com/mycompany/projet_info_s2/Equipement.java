/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;

/**
 *
 * @author doriancacheleux
 */
public class Equipement {
    private String refEquipement; 
    private String dEquipement;
    private boolean isDeleted;

    public String getRefEquipement() {
        return refEquipement;
    }

    public void setRefEquipement(String refEquipement) {
        this.refEquipement = refEquipement;
    }

    public String getDEquipement() {
        return dEquipement;
    }

    public void setDEquipement(String dEquipement) {
        this.dEquipement = dEquipement;
    }
    
    public boolean isDeleted() {
        return isDeleted;
    }

    public Equipement(String refEquipement, String dEquipement, boolean isDeleted) {
        this.refEquipement = refEquipement;
        this.dEquipement = dEquipement;
        this.isDeleted = isDeleted;
    }
    
    // Méthode pour afficher les informations de l'équipement
    public String afficheEquipement() {
        return "Equipement [Référence: " + refEquipement + ", État: " + (isDeleted ? "SUPPRIMÉ ❌" : "OPÉRATIONNEL ✅") + "]";
    }

    boolean getIsDeleted() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Iterable<Machine> getMachines() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
