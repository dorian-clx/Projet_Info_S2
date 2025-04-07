/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 *
 * @author doriancacheleux
 */
public class Poste extends Equipement{
    //attributs
    
    // Attributs
    private Set<Machine> machines; // Ensemble des machines dans le poste

    // Constructeur
    public Poste(String refEquipement, String dEquipement, boolean isDeleted) {
        super(refEquipement, dEquipement);
        this.machines = new HashSet<>();
    }

    // Getters
    public String getRefPoste() {
        return getRefEquipement();
    }

    public Set<Machine> getMachines() {
        return machines;
    }

    public String getIdentifiant() {
        return "Poste-" + getRefEquipement();
    }

    // Ajout d'une machine
    public void ajouterMachine(Machine machine) {
        if (!isDeleted()) {
            machines.add(machine);
            System.out.println("Machine " + machine.getRefEquipement() + " ajoutée au poste " + getRefEquipement() + ".");
        } else {
            System.out.println("Impossible d'ajouter une machine, le poste est supprimé.");
        }
    }

    // Suppression d'une machine
    public void supprimerMachine(Machine machine) {
        if (!isDeleted()) {
            if (machines.remove(machine)) {
                System.out.println("Machine " + machine.getRefEquipement() + " supprimée du poste.");
            } else {
                System.out.println("La machine " + machine.getRefEquipement() + " n'est pas dans le poste.");
            }
        } else {
            System.out.println("Impossible de supprimer une machine, le poste est supprimé.");
        }
    }

    // Modifier la composition du poste
    public void modifierPoste(Machine machine, boolean ajouter) {
        if (!isDeleted()) {
            if (ajouter) {
                ajouterMachine(machine);
            } else {
                supprimerMachine(machine);
            }
        } else {
            System.out.println("Impossible de modifier la composition du poste, il est supprimé.");
        }
    }

    // Suppression du poste (et des machines associées)
    public void supprimerPoste() {
        supprimer(); // hérité de Equipement
        for (Machine machine : machines) {
            machine.supprimerMachine(); // Peut aussi être .supprimer()
        }
        System.out.println("Le poste " + getRefEquipement() + " et ses machines ont été supprimés.");
    }

    // Affichage
    @Override
    public String afficheEquipement() {
        if (isDeleted()) {
            return "Le poste " + getRefEquipement() + " a été supprimé.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Poste [refPoste = ").append(getRefEquipement())
              .append(", dPoste = ").append(getdEquipement())
              .append(", Machines :");

            if (machines.isEmpty()) {
                sb.append(" Aucune machine assignée.");
            } else {
                for (Machine m : machines) {
                    if (!m.isDeleted()) {
                        sb.append("\n - ").append(m.afficheEquipement());
                    }
                }
            }

            sb.append("]");
            return sb.toString();
        }
    }
}
   
