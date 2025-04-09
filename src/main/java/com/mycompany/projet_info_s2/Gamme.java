/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *
 * @author doriancacheleux
 */
public class Gamme {
    
    // Attributs
    private String refGamme;
    private Produit produit;
    private List<Operation> listeOperations;
    private Set<Poste> listePostes;
    private Set<Machine> listeMachines;
    private boolean isDeleted;  // Indicateur pour savoir si la gamme est supprimée

    // Getters et setters
    public String getRefGamme() {
        return refGamme;
    }

    public void setRefGamme(String refGamme) {
        this.refGamme = refGamme;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<Operation> getListeOperations() {
        return listeOperations;
    }

    public void setListeOperations(List<Operation> listeOperations) {
        this.listeOperations = listeOperations;
    }
    
    public Set<Poste> getListePostes() {
        return listePostes;
    }
    
    public Set<Machine> getListeMachines() {
        return listeMachines;
    }
    
    public boolean isDeleted() {
        return isDeleted;
    }
    
    // Constructeur 
    public Gamme(String refGamme, Produit produit, List<Operation> listeOperations) {
        this.refGamme = refGamme;
        this.produit = produit;
        this.listeOperations = listeOperations;
        this.listePostes = new HashSet<>();
        this.listeMachines = new HashSet<>();
        this.isDeleted = false;
        
        // Extraire les postes et machines des opérations
        extractEquipements();
    }
    
    // Méthode pour extraire les équipements des opérations
    private void extractEquipements() {
    for (Operation op : listeOperations) {
        if (op.getEquipementAssocie() != null) {
            // Si l'équipement est un Poste, l'ajouter à la liste des postes
            if (op.getEquipementAssocie() instanceof Poste poste) {
                listePostes.add(poste);
                
                // Récupérer les machines associées au poste
                for (Machine machine : poste.getMachines()) {
                    if (!machine.isDeleted()) {
                        listeMachines.add(machine);
                    }
                }
            }
            // Si l'équipement est une Machine, l'ajouter à la liste des machines
            else if (op.getEquipementAssocie() instanceof Machine machine) {
                if (!machine.isDeleted()) {
                    listeMachines.add(machine);
                }
            }
        }
    }
}
    
    // Méthodes de gestion de gammes
    public void creerGamme() {
        if (isDeleted) {
            System.out.println("Impossible de créer la gamme, elle a été supprimée.");
            return;
        }
        
        // Logique pour créer/enregistrer la gamme
        System.out.println("Création de la gamme " + refGamme + " pour le produit " + produit.getDesPro());
        extractEquipements(); // S'assurer que les équipements sont à jour
    }
    
    public void modifierGamme(String newRefGamme, Produit newProduit) {
        if (isDeleted) {
            System.out.println("Impossible de modifier la gamme, elle a été supprimée.");
            return;
        }
        
        this.refGamme = newRefGamme;
        this.produit = newProduit;
        System.out.println("Gamme modifiée: " + refGamme);
        
        // Mettre à jour les équipements après modification
        listePostes.clear();
        listeMachines.clear();
        extractEquipements();
    }
    
    public void supprimerGamme() {
        this.isDeleted = true;
        System.out.println("Gamme " + refGamme + " supprimée");
    }
    
    // Méthodes utiles
    public void ajouterOperation(Operation op) {
        if (!isDeleted) {
            listeOperations.add(op);
            
            // Mettre à jour les équipements associés à l'opération
            updateEquipements(op);
        } else {
            System.out.println("Impossible d'ajouter une opération, la gamme est supprimée.");
        }
    }

    public void supprimerOperation(Operation op) {
        if (!isDeleted) {
            listeOperations.remove(op);
            
            // Recalculer les équipements nécessaires
            listePostes.clear();
            listeMachines.clear();
            extractEquipements();
        } else {
            System.out.println("Impossible de supprimer une opération, la gamme est supprimée.");
        }
    }

    // Méthode pour mettre à jour les équipements associés à une opération
private void updateEquipements(Operation op) {
    Equipement equip = op.getEquipementAssocie();
    if (equip != null && !equip.isDeleted()) {
        // Si l'équipement est un Poste, on l'ajoute à la liste des postes
        if (equip instanceof Poste poste) {
            if (!listePostes.contains(poste)) {
                listePostes.add(poste);
            }
            // Ajouter les machines du poste, en vérifiant qu'elles ne sont pas supprimées
            for (Machine machine : poste.getMachines()) {
                if (!machine.isDeleted() && !listeMachines.contains(machine)) {
                    listeMachines.add(machine);
                }
            }
        }
        // Si l'équipement est une Machine seule, on l'ajoute à la liste des machines
        else if (equip instanceof Machine machine) {
            if (!machine.isDeleted() && !listeMachines.contains(machine)) {
                listeMachines.add(machine);
            }
        }
    }
}

    // Méthodes de calcul
    // Méthode pour calculer la durée totale de la gamme
    public double dureeGamme() {
    if (isDeleted) {
        return 0.0;
    }

    double total = 0.0;
    for (Operation op : listeOperations) {
        Equipement equipement = op.getEquipementAssocie();

        // Ignorer les opérations dont l'équipement est supprimé
        if (equipement.getIsDeleted()) {
            continue;
        }

        boolean operationRealisable = true;

        if (equipement instanceof Poste poste) {
            // Vérifier les machines du poste
            for (Machine machine : poste.getMachines()) {
                if (machine.isDeleted() || 
                    machine.getEtat() == EtatMachine.EN_PANNE || 
                    machine.getEtat() == EtatMachine.EN_MAINTENANCE) {
                    operationRealisable = false;
                    break;
                }
            }
        } else if (equipement instanceof Machine machine) {
            // Vérifier la machine directement associée
            if (machine.isDeleted() || 
                machine.getEtat() == EtatMachine.EN_PANNE || 
                machine.getEtat() == EtatMachine.EN_MAINTENANCE) {
                operationRealisable = false;
            }
        }

        if (operationRealisable) {
            total += op.getDuree();
        }
    }
    return total;
}

    // Méthode mise à jour pour calculer le coût total de la gamme
public double coutGamme() {
    if (isDeleted) {
        return 0.0;
    }

    double total = 0.0;
    for (Operation op : listeOperations) {
        // Utilise la méthode getCoutTotal() de l'opération
        total += op.getCoutTotal();
    }

    return total;
}
    
    public boolean peutEtreRealisee() {
        if (isDeleted) {
            return false;
        }
        
        for (Operation op : listeOperations) {
            if (op.getEquipementAssocie().getIsDeleted()) {
                return false;
            }
        }
        return true;
    }

    public String getStatusRealisation() {
    if (isDeleted) {
        return "La gamme a été supprimée et ne peut pas être réalisée.";
    }

    StringBuilder sb = new StringBuilder();
    int operationsNonRealisables = 0;
    int totalOperations = listeOperations.size();

    sb.append("Statut de réalisation:\n");

    for (Operation op : listeOperations) {
        Equipement equipementAssocie = op.getEquipementAssocie(); // Récupère l'équipement associé à l'opération

        // Vérifie si l'équipement est supprimé
        if (equipementAssocie.getIsDeleted()) {
            sb.append(" - Opération #").append(op.getOrdre())
              .append(" : NON RÉALISABLE (équipement supprimé)\n");
            operationsNonRealisables++;
            continue; // Passe à la prochaine opération
        }

 
        // Vérifier si l'opération est réalisable
        else { if(op.getEquipementAssocie().getIsDeleted() || 
            op.getEquipementAssocie() instanceof Poste poste && poste.getIsDeleted()) {
            // Si le poste ou l'équipement est supprimé, on ignore l'opération
            continue;
        }

        // Vérifier l'état de la machine associée
        if (op.getEquipementAssocie() instanceof Poste poste) {
            for (Machine machine : poste.getMachines()) {
                if (machine.isDeleted() || machine.getEtat() == EtatMachine.EN_PANNE || machine.getEtat() == EtatMachine.EN_MAINTENANCE || machine.getEtat() == EtatMachine.OCCUPEE) {
                    operationsNonRealisables++;  // Une machine en panne ou en maintenance empêche l'opération
                    break;
                }
            }
        } else if (op.getEquipementAssocie() instanceof Machine machine) {
            // Si l'équipement est une machine indépendante, vérifier son état
            if (machine.isDeleted() || machine.getEtat() == EtatMachine.EN_PANNE || machine.getEtat() == EtatMachine.EN_MAINTENANCE || machine.getEtat() == EtatMachine.OCCUPEE) {
                operationsNonRealisables++;  // Une machine en panne ou en maintenance empêche l'opération
            }
        }
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

    // Méthode pour calculer le pourcentage de réalisation de la gamme
public double getPourcentageRealisation() {
    if (isDeleted || listeOperations.isEmpty()) {
        return 0.0;
    }

    int operationsRealisables = 0;
    for (Operation op : listeOperations) {
        // Vérifier si l'opération est réalisable
        if (op.getEquipementAssocie().getIsDeleted() || 
            op.getEquipementAssocie() instanceof Poste poste && poste.getIsDeleted()) {
            // Si le poste ou l'équipement est supprimé, on ignore l'opération
            continue;
        }

        // Vérifier l'état de la machine associée
        boolean operationRealisable = true;
        if (op.getEquipementAssocie() instanceof Poste poste) {
            for (Machine machine : poste.getMachines()) {
                if (machine.isDeleted() || machine.getEtat() == EtatMachine.EN_PANNE || machine.getEtat() == EtatMachine.EN_MAINTENANCE || machine.getEtat() == EtatMachine.OCCUPEE) {
                    operationRealisable = false;  // Une machine en panne ou en maintenance empêche l'opération
                    break;
                }
            }
 
        } else if (op.getEquipementAssocie() instanceof Machine machine) {
            // Si l'équipement est une machine indépendante, vérifier son état
            if (machine.isDeleted() || machine.getEtat() == EtatMachine.EN_PANNE || machine.getEtat() == EtatMachine.EN_MAINTENANCE || machine.getEtat() == EtatMachine.OCCUPEE) {
                operationRealisable = false;  // Une machine en panne ou en maintenance empêche l'opération
            }
        }


        if (operationRealisable) {
            operationsRealisables++;
        }
    }

    return (double) operationsRealisables / listeOperations.size() * 100;
}

    // Affichage de la gamme
    public String afficheGamme() {
    if (isDeleted) {
        return "La gamme " + refGamme + " a été supprimée.";
    }
    
    StringBuilder sb = new StringBuilder();
    sb.append("Gamme ref: ").append(refGamme).append(" (Produit: ").append(produit.getDesPro()).append(")\n");
    
    // Affichage des opérations
    sb.append("Opérations :\n");
    for (Operation op : listeOperations) {
        sb.append(" - ").append(op.afficheOperation()).append("\n");
    }
    
    // Affichage des équipements nécessaires (postes et machines)
    sb.append("\nListe des équipements nécessaires :\n");

    // Postes
    if (listePostes.isEmpty()) {
        sb.append(" - Aucun poste enregistré\n");
    } else {
        for (Poste poste : listePostes) {
            String posteEtat = poste.getIsDeleted() ? "SUPPRIMÉ ❌" : "OPÉRATIONNEL ✅";
            sb.append(" - Poste: ").append(poste.getRefPoste())
              .append(" [État: ").append(posteEtat).append("]\n");
        }
    }

    // Machines
    if (listeMachines.isEmpty()) {
        sb.append(" - Aucune machine enregistrée\n");
    } else {
        for (Machine machine : listeMachines) {
            // Utilisation de la méthode estOperationnelle() pour simplifier l'affichage
            String machineEtat;
            if (machine.isDeleted()) {
                machineEtat = "SUPPRIMÉE ❌";
            } else {
                switch (machine.getEtat()) {
                    case DISPONIBLE:
                        machineEtat = "DISPONIBLE ✅";
                        break;
                    case OCCUPEE:
                        machineEtat = "OCCUPÉE ⏳";
                        break;
                    case EN_PANNE:
                        machineEtat = "EN PANNE ❌";
                        break;
                    case EN_MAINTENANCE:
                        machineEtat = "EN MAINTENANCE 🔧";
                        break;
                    default:
                        machineEtat = "ÉTAT INCONNU";
                        break;
                }
            }
            sb.append(" - Machine: ").append(machine.getRefMachine())
              .append(" [État: ").append(machineEtat).append("]\n");
        }
    }

    // Informations sur la durée et le coût
    sb.append("\nDurée totale : ").append(dureeGamme()).append("h\n");
    sb.append("Coût total : ").append(coutGamme()).append("€\n");
    sb.append("Pourcentage de réalisation : ").append(String.format("%.1f", getPourcentageRealisation())).append("%\n");
    
    // Statut de réalisation
    sb.append("\n").append(getStatusRealisation()).append("\n");
    
    return sb.toString();
}
}