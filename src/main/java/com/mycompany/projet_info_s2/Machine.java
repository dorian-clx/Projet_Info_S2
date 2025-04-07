/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;

/**
 *
 * @author jbugnet01
 */
public class Machine extends Equipement {
    
    // Attributs spécifiques à Machine
    private String type;
    private double cout;
    private float x;
    private float y;
    private EtatMachine etat;
    private Operateur operateurActuel; // l’opérateur qui utilise actuellement la machine
    private boolean isDeleted;

    // Constructeur
    public Machine(String refEquipement, String dEquipement, String type, double cout, float x, float y, boolean isDeleted) {
        super(refEquipement, dEquipement);
        this.type = type;
        this.cout = cout;
        this.x = x;
        this.y = y;
        this.etat = EtatMachine.DISPONIBLE;
        this.operateurActuel = null;
        this.isDeleted = false;
    }

    // Getters et Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getRefMachine() {
        return getRefEquipement();
    }
    
    public String getdMachine(){
        return getdMachine();
    }
    
    public EtatMachine getEtat() {
        return etat;
    }

    public Operateur getOperateurActuel() {
        return operateurActuel;
    }

// Méthode pour mettre la machine en panne
public void mettreEnPanne() {
    this.etat = EtatMachine.EN_PANNE;
    this.operateurActuel = null;
    System.out.println("Machine " + getRefMachine() + " mise en panne.");
}

// Méthode pour mettre en maintenance
public void mettreEnMaintenance() {
    this.etat = EtatMachine.EN_MAINTENANCE;
    this.operateurActuel = null;
    System.out.println("Machine " + getRefMachine() + " en maintenance.");
}

// Méthode pour remettre en état
public void remettreEnService() {
    this.etat = EtatMachine.DISPONIBLE;
    System.out.println("Machine " + getRefMachine() + " disponible.");
}

// Utiliser la machine
public boolean utiliserMachine(Operateur operateur) {
    if (etat == EtatMachine.DISPONIBLE && !isDeleted() && operateur.peutUtiliserMachine(getRefMachine())) {
        this.operateurActuel = operateur;
        this.etat = EtatMachine.OCCUPEE;
        operateur.setOccupe(true);
        System.out.println("Machine " + getRefMachine() + " utilisée par l’opérateur " + operateur.getNom());
        return true;
    } else {
        System.out.println("Machine " + getRefMachine() + " non disponible ou opérateur non qualifié.");
        return false;
    }
}

// Libérer la machine
public void libererMachine() {
    if (etat == EtatMachine.OCCUPEE) {
        if (operateurActuel != null) {
            operateurActuel.setOccupe(false);
            System.out.println("Opérateur " + operateurActuel.getNom() + " a libéré la machine " + getRefMachine());
        }
        this.operateurActuel = null;
        this.etat = EtatMachine.DISPONIBLE;
    }
}

    // Implémentation de la méthode abstraite
    @Override
    public String afficheEquipement() {
        if (isDeleted()) {
            return "La machine " + getRefEquipement() + " a été supprimée.";
        } else {
            return "Machine [refMachine = " + this.getRefEquipement() +
                   ", dMachine = " + this.getdEquipement() +
                   ", type = " + this.type +
                   ", cout = " + this.cout +
                   ", x = " + this.x +
                   ", y = " + this.y +
                   ", état = " + this.etat +
                    (operateurActuel != null ? ", opérateur = " + operateurActuel.getNom() : "") +
                   "]";
        }
    }

    // Suppression (hérite déjà de supprimer() via Equipement)
    public void supprimerMachine() {
        this.supprimer();
        System.out.println("La machine " + this.getRefEquipement() + " a été supprimée.");
    }

    public void modifierMachine(String newRefMachine, String newdMachine, String newType, double newCout, float newX, float newY, String newEtat) {
    if (!isDeleted()) {
        this.setRefEquipement(newRefMachine);
        this.setdEquipement(newdMachine);
        this.type = newType;
        this.cout = newCout;
        this.x = newX;
        this.y = newY;
        
        try {
            this.etat = EtatMachine.valueOf(newEtat.toUpperCase()); // conversion String -> Enum
        } catch (IllegalArgumentException e) {
            System.out.println("État invalide : " + newEtat + ". Aucun changement d’état effectué.");
        }

        System.out.println("Les informations de la machine ont été mises à jour.");
    } else {
        System.out.println("Impossible de modifier la machine, elle a été supprimée.");
    }
}
}
