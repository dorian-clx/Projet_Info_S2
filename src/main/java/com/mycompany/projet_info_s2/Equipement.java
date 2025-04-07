/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;

/**
 *
 * @author doriancacheleux
 */
    public abstract class Equipement {
        private String refEquipement;
        private String dEquipement;
        protected boolean isDeleted;

    public Equipement(String refEquipement, String dEquipement) {
        this.refEquipement = refEquipement;
        this.dEquipement = dEquipement;
        this.isDeleted = false;
    }

    public String getRefEquipement() {
        return refEquipement;
    }

    public void setRefEquipement(String refEqupement) {
        this.refEquipement = refEquipement;
    }

    public String getdEquipement() {
        return dEquipement;
    }

    public void setdEquipement(String dEquipement) {
        this.dEquipement = dEquipement;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }
    
    

    public boolean isDeleted() {
        return isDeleted;
    }

    public void supprimer() {
        this.isDeleted = true;
    }

    public void restaurer() {
        this.isDeleted = false;
    }

    // Méthode abstraite à implémenter par les sous-classes
    public abstract String afficheEquipement();

    @Override
    public String toString() {
        return refEquipement + dEquipement + " [" + (isDeleted ? "SUPPRIMÉ ❌" : "OPÉRATIONNEL ✅") + "]";
    }
}