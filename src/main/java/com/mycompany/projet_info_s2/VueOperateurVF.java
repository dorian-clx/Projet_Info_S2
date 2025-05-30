/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

/**
 *
 * @author jolan
 */

import controleur.ControleurOperateur;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.VBox;
import modele.Atelier;
import modele.Machine;
import modele.Operateur;

public class VueOperateur extends VBox {

    private Atelier atelier;
    private ControleurOperateur controleur;

    private TextField champCode;
    private TextField champNom;
    private TextField champPrenom;

    private ListView<Machine> listeMachines;
    private TableView<Operateur> tableOperateurs;
    private ObservableList<Operateur> listeOperateurs;
    private Button boutonModifier;

    public VueOperateur(Atelier atelier) {
        this.atelier = atelier;
        this.listeOperateurs = FXCollections.observableArrayList(atelier.getOperateurs());
        this.controleur = new ControleurOperateur(atelier, listeOperateurs);

        this.setSpacing(10);
        this.setPadding(new Insets(10));

        // Champs texte
        champCode = new TextField();
        champCode.setPromptText("Code opérateur");

        champNom = new TextField();
        champNom.setPromptText("Nom");

        champPrenom = new TextField();
        champPrenom.setPromptText("Prénom");

        // Liste des machines (compétences)
        listeMachines = new ListView<>(FXCollections.observableArrayList(atelier.getMachines()));
        listeMachines.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listeMachines.setPrefHeight(100);

        // Boutons
        Button boutonAjouter = new Button("Ajouter opérateur");
        boutonAjouter.setOnAction(e -> ajouterOperateur());
        
        boutonModifier = new Button("Modifier opérateur");
        boutonModifier.setOnAction(e -> modifierOperateur());

        Button boutonSupprimer = new Button("Supprimer opérateur");
        boutonSupprimer.setOnAction(e -> supprimerOperateur());

        // Table des opérateurs
        tableOperateurs = new TableView<>(listeOperateurs);
        tableOperateurs.setPrefHeight(250);

        TableColumn<Operateur, String> colCode = new TableColumn<>("Code");
        colCode.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCode()));

        TableColumn<Operateur, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));

        TableColumn<Operateur, String> colPrenom = new TableColumn<>("Prénom");
        colPrenom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPrenom()));

        TableColumn<Operateur, Boolean> colOccupe = new TableColumn<>("Occupé");
        colOccupe.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isEstOccupe()));
        colOccupe.setCellFactory(CheckBoxTableCell.forTableColumn(colOccupe));
        colOccupe.setEditable(false);
        
        TableColumn<Operateur, String> colCompetences = new TableColumn<>("Compétences");
        colCompetences.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(String.join(", ", data.getValue().getCompetences()))
        );


        tableOperateurs.getColumns().addAll(colCode, colNom, colPrenom, colCompetences, colOccupe);

        this.getChildren().addAll(
                new Label("Ajouter un opérateur :"),
                champCode, champNom, champPrenom,
                new Label("Machines maîtrisées :"),
                listeMachines,
                boutonAjouter, boutonModifier, boutonSupprimer,
                new Label("Opérateurs enregistrés :"),
                tableOperateurs
        );
    }

    private void ajouterOperateur() {
        String code = champCode.getText();
        String nom = champNom.getText();
        String prenom = champPrenom.getText();
        var machines = listeMachines.getSelectionModel().getSelectedItems();

        if (code.isEmpty() || nom.isEmpty() || prenom.isEmpty() || machines.isEmpty()) {
            Alert alerte = new Alert(Alert.AlertType.WARNING);
            alerte.setHeaderText("Champs manquants");
            alerte.setContentText("Veuillez remplir tous les champs et sélectionner au moins une machine.");
            alerte.showAndWait();
            return;
        }

        boolean succes = controleur.ajouterOperateur(code, nom, prenom, machines);
        if (succes) {
            champCode.clear();
            champNom.clear();
            champPrenom.clear();
            listeMachines.getSelectionModel().clearSelection();

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("Opérateur ajouté");
            info.setContentText("L'opérateur a bien été enregistré.");
            info.showAndWait();
        } else {
            Alert erreur = new Alert(Alert.AlertType.ERROR);
            erreur.setHeaderText("Code existant");
            erreur.setContentText("Un opérateur avec ce code existe déjà.");
            erreur.showAndWait();
        }
    }
    
    private void modifierOperateur() {
    Operateur operateurSelectionne = tableOperateurs.getSelectionModel().getSelectedItem();
    if (operateurSelectionne == null) {
        Alert alerte = new Alert(Alert.AlertType.WARNING);
        alerte.setHeaderText("Aucune sélection");
        alerte.setContentText("Veuillez sélectionner un opérateur à modifier.");
        alerte.showAndWait();
        return;
    }

    String nouveauCode = champCode.getText();
    String nouveauNom = champNom.getText();
    String nouveauPrenom = champPrenom.getText();

    var machinesSelectionnees = listeMachines.getSelectionModel().getSelectedItems();

    if (nouveauCode.isEmpty() || nouveauNom.isEmpty() || nouveauPrenom.isEmpty() || machinesSelectionnees.isEmpty()) {
        Alert alerte = new Alert(Alert.AlertType.WARNING);
        alerte.setHeaderText("Champs manquants");
        alerte.setContentText("Veuillez remplir tous les champs et sélectionner au moins une machine.");
        alerte.showAndWait();
        return;
    }

    // Conversion des machines sélectionnées en liste de String (références)
    List<String> competencesStrings = machinesSelectionnees.stream()
        .map(Machine::getRefEquipement)  // adapte selon ta méthode dans Machine
        .toList();

    // Mise à jour des données de l'opérateur
    operateurSelectionne.setCode(nouveauCode);
    operateurSelectionne.setNom(nouveauNom);
    operateurSelectionne.setPrenom(nouveauPrenom);
    operateurSelectionne.setCompetences(competencesStrings);

    // Rafraîchir le tableau
    tableOperateurs.refresh();

    Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
    confirmation.setHeaderText("Modification réussie");
    confirmation.setContentText("L'opérateur a été modifié.");
    confirmation.showAndWait();

    if (getScene() != null && getScene().getRoot() instanceof VuePrincipale vp) {
        vp.rafraichirToutesLesVues(atelier);
    }
}

    private void supprimerOperateur() {
        Operateur selection = tableOperateurs.getSelectionModel().getSelectedItem();
        if (selection != null) {
            controleur.supprimerOperateur(selection);
        } else {
            Alert alerte = new Alert(Alert.AlertType.WARNING);
            alerte.setHeaderText("Aucun opérateur sélectionné");
            alerte.setContentText("Veuillez sélectionner un opérateur à supprimer.");
            alerte.showAndWait();
        }
    }

    public void rafraichirDepuis(Atelier nouvelAtelier) {
        this.atelier = nouvelAtelier;
        this.listeOperateurs.setAll(atelier.getOperateurs());
        this.listeMachines.setItems(FXCollections.observableArrayList(atelier.getMachines()));
    }
}

