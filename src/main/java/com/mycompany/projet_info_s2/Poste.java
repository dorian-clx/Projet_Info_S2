/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author doriancacheleux
 */
public class Poste {
    //attributs
    private int refPoste;
    private String desPoste;
    private Set<Machine> machines; //ensemble des machines au sein  d'un poste
    private boolean isDeleted; // Indicateur pour savoir si le poste est supprimé

    //constructeur 
    public Poste(int refPoste, String desPoste) {
        this.refPoste = refPoste;
        this.desPoste = desPoste;
        this.machines = new HashSet<>(); // Initialisation de l'ensemble de machines
        this.isDeleted = false; // Le poste n'est pas supprimé par défaut
    }
    
    //getters et setters
    public int getRefPoste() {
        return refPoste;
    }

    public void setRefPoste(int refPoste) {
        this.refPoste = refPoste;
    }

    public String getDesPoste() {
        return desPoste;
    }

    public void setDesPoste(String desPoste) {
        this.desPoste = desPoste;
    }
    
    // Méthode pour ajouter une machine au poste
    public void ajouterMachine(Machine machine) {
        if (!isDeleted) {
            machines.add(machine);
            System.out.println("Machine " + machine.getRefMachine() + " ajoutée au poste.");
        } else {
            System.out.println("Impossible d'ajouter une machine, le poste est supprimé.");
        }
    }

    // Méthode pour supprimer une machine du poste
    public void supprimerMachine(Machine machine) {
        if (!isDeleted) {
            if (machines.contains(machine)) {
                machines.remove(machine);
                System.out.println("Machine " + machine.getRefMachine() + " supprimée du poste.");
            } else {
                System.out.println("La machine " + machine.getRefMachine() + " n'est pas dans le poste.");
            }
        } else {
            System.out.println("Impossible de supprimer une machine, le poste est supprimé.");
        }
    }

    // Méthode pour afficher le poste et ses machines
    public void affichePoste() {
        if (!isDeleted) {
            System.out.println("Poste [Code : " + refPoste + ", Description : " + desPoste + "]");
            System.out.println("Machines dans le poste : ");
            if (machines.isEmpty()) {
                System.out.println("Aucune machine associée à ce poste.");
            } else {
                for (Machine machine : machines) {
                    System.out.println("- " + machine.getRefMachine());
                }
            }
        } else {
            System.out.println("Le poste a été supprimé.");
        }
    }

    // Méthode pour modifier la composition du poste (ajouter ou supprimer des machines)
    public void modifierPoste(Machine machine, boolean ajouter) {
        if (!isDeleted) {
            if (ajouter) {
                ajouterMachine(machine);
            } else {
                supprimerMachine(machine);
            }
        } else {
            System.out.println("Impossible de modifier la composition du poste, il est supprimé.");
        }
    }

    // Méthode pour supprimer un poste (marquer comme supprimé)
    public void supprimerPoste() {
        isDeleted = true;
        System.out.println("Le poste " + desPoste + " a été supprimé.");
    }
    
    
}
   
