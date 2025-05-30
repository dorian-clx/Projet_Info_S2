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
import javafx.scene.layout.VBox;
import modele.Atelier;
import modele.Produit;
import controleur.ControleurProduit;

public class VueProduit extends VBox {

    private TextField champCode;
    private TextField champDesignation;
    private Button boutonAjouter;
    private Button boutonSupprimer;
    private Button boutonModifier;
    private Atelier atelier;


    private TableView<Produit> tableProduits;
    private ObservableList<Produit> listeProduits;

    private ControleurProduit controleur;


    public VueProduit(Atelier atelier) {
        this.atelier = atelier;
        this.listeProduits = FXCollections.observableArrayList(atelier.getProduits());
        this.controleur = new ControleurProduit(atelier, listeProduits);

        // Champ de saisie
        champCode = new TextField();
        champCode.setPromptText("Code produit");

        champDesignation = new TextField();
        champDesignation.setPromptText("Désignation");

        boutonAjouter = new Button("Ajouter le produit");
        boutonAjouter.setOnAction(e -> ajouterProduit());
        
        boutonModifier = new Button("Modifier le produit sélectionné");
        boutonModifier.setOnAction(e -> modifierProduit());

        boutonSupprimer = new Button("Supprimer le produit sélectionné");
        boutonSupprimer.setOnAction(e -> supprimerProduit());

        // TableView
        tableProduits = new TableView<>(listeProduits);
        tableProduits.setPrefHeight(200);

        TableColumn<Produit, Number> colCode = new TableColumn<>("Code");
        colCode.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCodePro()));

        TableColumn<Produit, String> colDes = new TableColumn<>("Désignation");
        colDes.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDesPro()));

        tableProduits.getColumns().addAll(colCode, colDes);

        // Layout
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.getChildren().addAll(
                new Label("Ajouter un produit"),
                champCode, champDesignation, boutonAjouter, boutonModifier, boutonSupprimer,
                new Label("Produits créés :"),
                tableProduits
        );
    }

private void ajouterProduit() {
    try {
        int code = Integer.parseInt(champCode.getText());
        String designation = champDesignation.getText();
        
        if (designation.isEmpty()) {
            Alert alerte = new Alert(Alert.AlertType.WARNING);
            alerte.setHeaderText("Champ vide");
            alerte.setContentText("Veuillez entrer une désignation.");
            alerte.showAndWait();
            return;
        }

        boolean succes = controleur.ajouterProduit(code, designation);
        if (succes) {
            champCode.clear();
            champDesignation.clear();
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("Produit ajouté");
            info.setContentText("Le produit a bien été enregistré.");
            info.showAndWait();
        } else {
            Alert erreur = new Alert(Alert.AlertType.ERROR);
            erreur.setHeaderText("Produit existant");
            erreur.setContentText("Un produit avec ce code existe déjà.");
            erreur.showAndWait();
        }

    } catch (NumberFormatException e) {
        Alert erreur = new Alert(Alert.AlertType.ERROR);
        erreur.setHeaderText("Code invalide");
        erreur.setContentText("Veuillez entrer un code entier valide.");
        erreur.showAndWait();
    }
    if (getScene() != null && getScene().getRoot() instanceof VuePrincipale vp) {
    vp.rafraichirToutesLesVues(atelier);
}

}

private void modifierProduit() {
    Produit produitSelectionne = tableProduits.getSelectionModel().getSelectedItem();
    if (produitSelectionne == null) {
        Alert alerte = new Alert(Alert.AlertType.WARNING);
        alerte.setHeaderText("Aucune sélection");
        alerte.setContentText("Veuillez sélectionner un produit à modifier.");
        alerte.showAndWait();
        return;
    }

    try {
        int nouveauCode = Integer.parseInt(champCode.getText());
        String nouvelleDesignation = champDesignation.getText();

        if (nouvelleDesignation.isEmpty()) {
            Alert alerte = new Alert(Alert.AlertType.WARNING);
            alerte.setHeaderText("Champ vide");
            alerte.setContentText("Veuillez entrer une désignation.");
            alerte.showAndWait();
            return;
        }

        // Met à jour les attributs du produit sélectionné
        produitSelectionne.setCodePro(nouveauCode);
        produitSelectionne.setDesPro(nouvelleDesignation);

        // Rafraîchit la table pour afficher les changements
        tableProduits.refresh();

        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setHeaderText("Modification réussie");
        confirmation.setContentText("Le produit a été modifié.");
        confirmation.showAndWait();

        // Rafraîchir les vues si besoin
        if (getScene() != null && getScene().getRoot() instanceof VuePrincipale vp) {
            vp.rafraichirToutesLesVues(atelier);
        }
    } catch (NumberFormatException e) {
        Alert erreur = new Alert(Alert.AlertType.ERROR);
        erreur.setHeaderText("Code invalide");
        erreur.setContentText("Veuillez entrer un code entier valide.");
        erreur.showAndWait();
    }
}

private void supprimerProduit() {
    Produit selection = tableProduits.getSelectionModel().getSelectedItem();
    if (selection != null) {
        controleur.supprimerProduit(selection);
        System.out.println("Produit " + selection.getCodePro() + " supprimé");
    } else {
        System.out.println("Aucun produit sélectionné.");
    }
}




public void rafraichirDepuis(Atelier nouvelAtelier) {
    this.atelier = nouvelAtelier;
    this.listeProduits.setAll(nouvelAtelier.getProduits());
}



}

