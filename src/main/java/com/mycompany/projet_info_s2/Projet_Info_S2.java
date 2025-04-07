/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projet_info_s2;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author doriancacheleux
 */
public class Projet_Info_S2 {

    public static void main(String[] args) {
       System.out.println("=== DÉMARRAGE DU SYSTÈME DE GESTION DE FABRICATION ===\n");
        
        // Créer un atelier
        Atelier atelier = new Atelier();
        
        // 1. Créer des produits
        System.out.println("CRÉATION DES PRODUITS");
        Produit p1 = new Produit(1001, "Table basse");
        Produit p2 = new Produit(1002, "Armoire");
        Produit p3 = new Produit(1003, "Chaise");
        
        // Ajouter les produits à l'atelier
        atelier.ajouterProduit(p1);
        atelier.ajouterProduit(p2);
        atelier.ajouterProduit(p3);
        System.out.println("Produits créés : " + p1.afficherProduit() + ", " + p2.afficherProduit() + ", " + p3.afficherProduit());
        
        // 2. Créer des machines
        System.out.println("\nCRÉATION DES MACHINES");
        Machine m1 = new Machine("M001", "Scie circulaire", "Découpe", 50.0, 10.0f, 20.0f, false);
        Machine m2 = new Machine("M002", "Perceuse", "Perçage", 30.0, 15.0f, 25.0f, false);
        Machine m3 = new Machine("M003", "Ponceuse", "Finition", 25.0, 20.0f, 30.0f, false);
        Machine m4 = new Machine("M004", "Tour", "Usinage", 75.0, 25.0f, 35.0f, false);
        Machine m5 = new Machine("M005", "Fraiseuse", "Usinage", 80.0, 30.0f, 40.0f, false);
        
        // Ajouter les machines à l'atelier
        atelier.ajouterMachine(m1);
        atelier.ajouterMachine(m2);
        atelier.ajouterMachine(m3);
        atelier.ajouterMachine(m4);
        atelier.ajouterMachine(m5);
        System.out.println("Machines créées avec succès.");
        
        // 3. Créer des postes et assigner des machines
        System.out.println("\nCRÉATION DES POSTES");
        Poste poste1 = new Poste("P001", "Poste de découpe", false);
        poste1.ajouterMachine(m1);
        
        Poste poste2 = new Poste("P002", "Poste de perçage", false);
        poste2.ajouterMachine(m2);
        
        Poste poste3 = new Poste("P003", "Poste de finition", false);
        poste3.ajouterMachine(m3);
        
        Poste poste4 = new Poste("P004", "Poste d'usinage", false);
        poste4.ajouterMachine(m4);
        
        // Ajouter les postes à l'atelier
        atelier.ajouterPoste(poste1);
        atelier.ajouterPoste(poste2);
        atelier.ajouterPoste(poste3);
        atelier.ajouterPoste(poste4);
        System.out.println("Postes créés avec succès.");
        
        // 4. Créer des opérateurs
        System.out.println("\nCRÉATION DES OPÉRATEURS");
        Operateur op1 = new Operateur("OP001", "Dupont", "Jean", new ArrayList<>(Arrays.asList("M001", "M002")), false);
        Operateur op2 = new Operateur("OP002", "Martin", "Sophie", new ArrayList<>(Arrays.asList("M003", "M004")), false);
        Operateur op3 = new Operateur("OP003", "Bernard", "Paul", new ArrayList<>(Arrays.asList("M005", "M001", "M002")), false);
        m1.mettreEnPanne();
        
        // Ajouter les opérateurs à l'atelier
        atelier.ajouterOperateur(op1);
        atelier.ajouterOperateur(op2);
        atelier.ajouterOperateur(op3);
        System.out.println("Opérateurs créés avec succès.");
        System.out.println(op1.afficherOperateur());
        System.out.println(op2.afficherOperateur());
        System.out.println(op3.afficherOperateur());
        
        // 5. Créer des opérations pour la gamme de fabrication de la table basse
        System.out.println("\nCRÉATION DES OPÉRATIONS POUR LA GAMME DE FABRICATION DE LA TABLE BASSE");
        Operation op_table_1 = new Operation("Découpe des planches", poste1, 2.0, 100.0, "Découpe", 1);
        Operation op_table_2 = new Operation("Perçage des trous", poste2, 1.0, 50.0, "Perçage", 2);
        Operation op_table_3 = new Operation("Ponçage des surfaces", poste3, 1.5, 75.0, "Finition", 3);
        
        // Créer une liste d'opérations pour la gamme
        List<Operation> operations_table = new ArrayList<>();
        operations_table.add(op_table_1);
        operations_table.add(op_table_2);
        operations_table.add(op_table_3);
        
        // 6. Créer la gamme de fabrication pour la table basse
        Gamme gamme_table = new Gamme("G001", p1, operations_table);
        atelier.ajouterGamme(gamme_table);
        System.out.println("Gamme de fabrication pour la table basse créée avec succès.");
        
        // 7. Créer des opérations pour la gamme de fabrication de l'armoire
        System.out.println("\nCRÉATION DES OPÉRATIONS POUR LA GAMME DE FABRICATION DE L'ARMOIRE");
        Operation op_armoire_1 = new Operation("Découpe des panneaux", poste1, 3.0, 150.0, "Découpe", 1);
        Operation op_armoire_2 = new Operation("Usinage des jointures", poste4, 2.0, 160.0, "Usinage", 2);
        Operation op_armoire_3 = new Operation("Perçage pour étagères", poste2, 1.5, 75.0, "Perçage", 3);
        Operation op_armoire_4 = new Operation("Ponçage et finition", poste3, 2.5, 125.0, "Finition", 4);
        
        // Créer une liste d'opérations pour la gamme
        List<Operation> operations_armoire = new ArrayList<>();
        operations_armoire.add(op_armoire_1);
        operations_armoire.add(op_armoire_2);
        operations_armoire.add(op_armoire_3);
        operations_armoire.add(op_armoire_4);
        
        // Créer une opération avec une machine seule
        Operation op_armoire_5 = new Operation("Usinage avec machine seule", m5, 2.5, 125.0, "Usinage", 5);
        operations_armoire.add(op_armoire_5); // Ajout de l'opération avec machine seule
        
        // 8. Créer la gamme de fabrication pour l'armoire
        Gamme gamme_armoire = new Gamme("G002", p2, operations_armoire);
        atelier.ajouterGamme(gamme_armoire);
        System.out.println("Gamme de fabrication pour l'armoire créée avec succès.");
        
        // 9. Afficher l'état de l'atelier
        System.out.println("\n=== ÉTAT DE L'ATELIER ===");
        atelier.afficherAtelier();
        
        
        // 10. Tester les modifications et suppressions
        System.out.println("\n=== TESTS DE MODIFICATIONS ET SUPPRESSIONS ===");
        
        // Modifier un produit
        System.out.println("\nModification du produit 'Table basse':");
        p1.modifierProduit(1001, "Table basse design");
        System.out.println(p1.afficherProduit());
        
        // Supprimer un produit
        System.out.println("\nSuppression du produit 'Chaise':");
        p3.supprimerProduit();
        System.out.println(p3.afficherProduit());
        
        // Modifier une machine
        System.out.println("\nModification de la machine 'Scie circulaire':");
        m1.modifierMachine("M001", "Scie circulaire professionnelle", "Découpe avancée", 60.0, 10.0f, 20.0f, "opérationnel");
        System.out.println(m1.afficheEquipement());
        m1.mettreEnMaintenance();
        
        // Supprimer une machine
        System.out.println("\nSuppression de la machine 'Tour':");
        m4.supprimerMachine();
        System.out.println(m4.afficheEquipement());
        
        // Ajouter une opération à une gamme existante
        System.out.println("\nAjout d'une opération à la gamme de la table basse:");
        Operation op_table_4 = new Operation("Assemblage final", poste4, 1.0, 50.0, "Assemblage", 4);
        gamme_table.ajouterOperation(op_table_4);
        System.out.println("Opération ajoutée à la gamme G001.");
        
        // Supprimer un poste
        System.out.println("\nSuppression du poste 'Poste de finition':");
        poste3.supprimerPoste();
        
        // 11. Vérifier le statut de réalisation des gammes après suppression
        System.out.println("\n=== STATUT DE RÉALISATION DES GAMMES APRÈS SUPPRESSION ===");
        System.out.println("\nStatut de réalisation de la gamme 'Table basse':");
        System.out.println(gamme_table.getStatusRealisation());
        System.out.println("Pourcentage de réalisation: " + String.format("%.1f", gamme_table.getPourcentageRealisation()) + "%");
        
        System.out.println("\nStatut de réalisation de la gamme 'Armoire':");
        System.out.println(gamme_armoire.getStatusRealisation());
        System.out.println("Pourcentage de réalisation: " + String.format("%.1f", gamme_armoire.getPourcentageRealisation()) + "%");
        
        // 12. Afficher l'état final de l'atelier
        System.out.println("\n=== ÉTAT FINAL DE L'ATELIER ===");
        atelier.afficherAtelier();
        
        System.out.println("\n=== FIN DU TEST DU SYSTÈME DE GESTION DE FABRICATION ===");
        
        // Création d’un opérateur avec une compétence sur "M001"
        Operateur op5 = new Operateur("O005", "Dupont", "Alice", Arrays.asList("M005"), false);
        Operateur op6 = new Operateur("O006", "Martin", "Bob", Arrays.asList("M999"), false); // Non qualifié

        // Création d’une machine
        Machine m6 = new Machine("M006", "Tourneuse numérique", "CNC", 150.0, 10.5f, 20.0f, false);

        // Affichage
        System.out.println(m5.afficheEquipement());

        // Utilisation de la machine avec opérateur qualifié
        m5.utiliserMachine(op5);
        System.out.println(m1.afficheEquipement());

        // Libération de la machine
        m5.libererMachine();
        System.out.println(m5.afficheEquipement());

        // Utilisation avec opérateur non qualifié
        m5.utiliserMachine(op6);  // Devrait refuser
        System.out.println(m5.afficheEquipement());

        // Mise en panne
        m5.mettreEnPanne();
        System.out.println(m5.afficheEquipement());

        // Tentative d'utilisation pendant panne
        m5.utiliserMachine(op5);  // Devrait refuser

        // Remise en service
        m5.remettreEnService();

        // Mise en maintenance
        m1.mettreEnMaintenance();

        // Remise en service à nouveau
        m1.remettreEnService();

        // Modification des infos de la machine
        m1.modifierMachine("M001", "Tourneuse améliorée", "CNC", 180.0, 11.0f, 21.0f, "OCCUPEE"); // met l'état OCCUPEE directement
        System.out.println(m1.afficheEquipement());
    }
}

    



