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

public class Atelier {

    // Attributs
    private List<Produit> produits;
    private List<Machine> machines;
    private List<Poste> postes;
    private List<Operateur> operateurs;
    private List<Gamme> gammes;

    // Constructeur
    public Atelier() {
        this.produits = new ArrayList<>();
        this.machines = new ArrayList<>();
        this.postes = new ArrayList<>();
        this.operateurs = new ArrayList<>();
        this.gammes = new ArrayList<>();
    }

    // Méthodes d'ajout
    public void ajouterProduit(Produit p) {
        produits.add(p);
    }

    public void ajouterMachine(Machine m) {
        machines.add(m);
    }

    public void ajouterPoste(Poste p) {
        postes.add(p);
    }

    public void ajouterOperateur(Operateur o) {
        operateurs.add(o);
    }

    public void ajouterGamme(Gamme g) {
        gammes.add(g);
    }

    // Méthode d'affichage complet de l'atelier
    public void afficherAtelier() {
        System.out.println("\n===== PRODUITS =====");
        for (Produit p : produits) {
            System.out.println(p.afficherProduit());
        }

        System.out.println("\n===== MACHINES =====");
        for (Machine m : machines) {
            System.out.println(m.afficheEquipement());
        }

        System.out.println("\n===== POSTES =====");
        for (Poste p : postes) {
            System.out.println(p.afficheEquipement());
        }

        System.out.println("\n===== OPERATEURS =====");
        for (Operateur o : operateurs) {
            System.out.println(o.afficherOperateur());
        }

        System.out.println("\n===== GAMMES DE FABRICATION =====");
        for (Gamme g : gammes) {
            System.out.println(g.afficheGamme());
        }
    }

    // Accesseurs si besoin (getteurs)
    public List<Produit> getProduits() { return produits; }
    public List<Machine> getMachines() { return machines; }
    public List<Poste> getPostes() { return postes; }
    public List<Operateur> getOperateurs() { return operateurs; }
    public List<Gamme> getGammes() { return gammes; }
}