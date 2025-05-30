/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

/**
 *
 * @author jolan
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import modele.Atelier;
import modele.Machine;
import modele.Poste;
import controleur.ControleurPoste;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VuePoste extends VBox {

    private TextField champRef;
    private TextField champDesignation;
    private ListView<Machine> listeMachinesDisponibles;
    private ObservableList<Machine> machines;
    private Atelier atelier;
    private TableView<Poste> tablePostes;
    private ObservableList<Poste> listePostes;
    private Button boutonSupprimer;
    private Button boutonModifier;
    private ControleurPoste controleur;



    public VuePoste(Atelier atelier) {
        this.atelier = atelier; 
        this.listePostes = FXCollections.observableArrayList();
        this.controleur = new ControleurPoste(atelier, listePostes);
        this.machines = FXCollections.observableArrayList(atelier.getMachines()); // liste temporaire vide

        champRef = new TextField();
        champRef.setPromptText("Référence du poste");

        champDesignation = new TextField();
        champDesignation.setPromptText("Désignation du poste");

        listeMachinesDisponibles = new ListView<>(machines);
        listeMachinesDisponibles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listeMachinesDisponibles.setPrefHeight(150);

        Button boutonCreer = new Button("Créer le poste");
        boutonCreer.setOnAction(e -> creerPoste());
        boutonSupprimer = new Button("Supprimer le poste sélectionné");
        boutonSupprimer.setOnAction(e -> supprimerPoste());
        
        boutonModifier = new Button("Modifier le poste sélectionné");
        boutonModifier.setOnAction(e -> modifierPoste());

        VBox formulaire = new VBox(10, new Label("Créer un poste"),
                champRef, champDesignation,
                new Label("Sélectionnez les machines associées :"), listeMachinesDisponibles, boutonCreer, boutonModifier, boutonSupprimer);
        formulaire.setPadding(new Insets(15));
        formulaire.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        // Vue du tableau
        tablePostes = new TableView<>(listePostes);
        tablePostes.setPrefHeight(200);

        // Colonne 1 : Référence
        TableColumn<Poste, String> colRef = new TableColumn<>("Référence");
        colRef.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRefEquipement()));

        // Colonne 2 : Désignation
        TableColumn<Poste, String> colDes = new TableColumn<>("Désignation");
        colDes.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getdEquipement()));

        // Colonne 3 : Nombre de machines
        TableColumn<Poste, Number> colNbMachines = new TableColumn<>("Nb Machines");
        colNbMachines.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMachines().size()));

        tablePostes.getColumns().addAll(colRef, colDes, colNbMachines);

        this.getChildren().addAll(formulaire, new Label("Postes créés :"), tablePostes);
    }

    private void creerPoste() {
        String ref = champRef.getText();
        String designation = champDesignation.getText();
        List<Machine> machinesSelectionnees = listeMachinesDisponibles.getSelectionModel().getSelectedItems();

        if (champRef.getText().isEmpty() || champDesignation.getText().isEmpty()) {
            Alert alerte = new Alert(Alert.AlertType.WARNING);
            alerte.setHeaderText("Champs vides");
            alerte.setContentText("Veuillez remplir tous les champs.");
            alerte.showAndWait();
            return;
        }

        if (machinesSelectionnees.isEmpty()) {
            Alert alerte = new Alert(Alert.AlertType.WARNING);
            alerte.setHeaderText("Aucune machine sélectionnée");
            alerte.setContentText("Veuillez sélectionner au moins une machine.");
            alerte.showAndWait();
            return;
        }

        boolean succes = controleur.ajouterPoste(ref, designation, machinesSelectionnees);

        if (succes) {
            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setHeaderText("Poste ajouté");
            confirmation.setContentText("Le poste a bien été créé.");
            confirmation.showAndWait();
        } else {
            Alert erreur = new Alert(Alert.AlertType.ERROR);
            erreur.setHeaderText("Poste existant");
            erreur.setContentText("Un poste avec ce nom existe déjà.");
            erreur.showAndWait();
        }
        // Notifie l'interface de se rafraîchir
        if (getScene() != null && getScene().getRoot() instanceof VuePrincipale vp) {
            vp.rafraichirToutesLesVues(atelier);
        }

    }
    
    private void modifierPoste() {
    Poste posteSelectionne = tablePostes.getSelectionModel().getSelectedItem();
    if (posteSelectionne == null) {
        Alert alerte = new Alert(Alert.AlertType.WARNING);
        alerte.setHeaderText("Aucune sélection");
        alerte.setContentText("Veuillez sélectionner un poste à modifier.");
        alerte.showAndWait();
        return;
    }

    String nouvelleRef = champRef.getText();
    String nouvelleDesignation = champDesignation.getText();
    Set<Machine> machinesSelectionnees = new HashSet<>(listeMachinesDisponibles.getSelectionModel().getSelectedItems());

    if (nouvelleDesignation.isEmpty() || machinesSelectionnees.isEmpty() || nouvelleRef.isEmpty()) {
        Alert alerte = new Alert(Alert.AlertType.WARNING);
        alerte.setHeaderText("Champs manquants");
        alerte.setContentText("Veuillez remplir tous les champs et sélectionner des machines.");
        alerte.showAndWait();
        return;
    }

    posteSelectionne.setdEquipement(nouvelleDesignation);
    posteSelectionne.setMachines(machinesSelectionnees);
    posteSelectionne.setRefEquipement(nouvelleRef);


    tablePostes.refresh();

    Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
    confirmation.setHeaderText("Modification réussie");
    confirmation.setContentText("Le poste a été modifié.");
    confirmation.showAndWait();

    if (getScene() != null && getScene().getRoot() instanceof VuePrincipale vp) {
        vp.rafraichirToutesLesVues(atelier);
    }
}
    
    private void supprimerPoste() {
        Poste selection = tablePostes.getSelectionModel().getSelectedItem();
        if (selection != null) {
            boolean succes = controleur.supprimerPoste(selection);
            if (succes) {
                System.out.println("Poste " + selection.getRefPoste() + " supprimé.");
                if (getScene() != null && getScene().getRoot() instanceof VuePrincipale vp) {
                    vp.rafraichirToutesLesVues(atelier);
}

            }
        } else {
            System.out.println("Aucun poste sélectionné.");
        }
    }

    
    public void rafraichirMachines() {
    machines.setAll(atelier.getMachines());
}
    

public void rafraichirDepuis(Atelier nouvelAtelier) {
    this.atelier = nouvelAtelier;
    this.machines.setAll(atelier.getMachines());
    this.listePostes.setAll(atelier.getPostes());
}


}

