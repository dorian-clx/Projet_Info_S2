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
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import modele.*;
import controleur.ControleurMachine;


public class VueMachine extends VBox {

    private TextField champRef;
    private TextField champDesignation;
    private TextField champType;
    private TextField champCout;
    private TextField champX;
    private TextField champY;
    private TableView<Machine> tableMachines;
    
    private Button boutonSupprimer;
    private Button boutonModifier;

    private Atelier atelier;
    private ObservableList<Machine> listeMachines;
    
    private Map<Machine, Node[]> formesMachines = new HashMap<>();
    private ControleurMachine controleur;

    public VueMachine(Atelier atelier) {
        this.atelier = atelier;
        this.listeMachines = FXCollections.observableArrayList();
        this.controleur = new ControleurMachine(atelier, listeMachines);
        this.formesMachines = new HashMap<>();
        this.tableMachines = new TableView<>(listeMachines);

        // Formulaire
        Label titre = new Label("Ajouter une machine");
        champRef = new TextField(); champRef.setPromptText("Référence");
        champDesignation = new TextField(); champDesignation.setPromptText("Désignation");
        champType = new TextField(); champType.setPromptText("Type");
        champCout = new TextField(); champCout.setPromptText("Coût horaire");
        champX = new TextField(); champX.setPromptText("X");
        champY = new TextField(); champY.setPromptText("Y");

        Button boutonAjouter = new Button("Ajouter la machine");
        boutonAjouter.setOnAction(e -> ajouterMachine());
        boutonModifier = new Button("Modifier la machine sélectionnée");
        boutonModifier.setOnAction(e -> modifierMachine());
        boutonSupprimer = new Button("Supprimer la machine sélectionnée");
        boutonSupprimer.setOnAction(e -> supprimerMachine());
       


        VBox formulaire = new VBox(5, titre, champRef, champDesignation, champType, champCout, champX, champY, boutonAjouter, boutonModifier, boutonSupprimer);
        formulaire.setPadding(new Insets(10));
        formulaire.setStyle("-fx-border-color: black; -fx-border-width: 1;");
        formulaire.setPrefWidth(250);

        // Vue du tableau
        
        tableMachines = new TableView<>(listeMachines);
        tableMachines.setPrefHeight(150);

        TableColumn<Machine, String> colRef = new TableColumn<>("Référence");
        colRef.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRefMachine()));

        TableColumn<Machine, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType()));

        TableColumn<Machine, String> colDes = new TableColumn<>("Désignation");
        colDes.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getdMachine()));

        TableColumn<Machine, Number> colCout = new TableColumn<>("Coût");
        colCout.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getCout()));

        TableColumn<Machine, String> colEtat = new TableColumn<>("État");
        colEtat.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEtat().toString()));

        tableMachines.getColumns().addAll(colRef, colType, colDes, colCout, colEtat);
        
        tableMachines.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        champRef.setText(newSelection.getRefMachine());
        champDesignation.setText(newSelection.getdMachine());
        champType.setText(newSelection.getType());
        champCout.setText(String.valueOf(newSelection.getCout()));
        champX.setText(String.valueOf(newSelection.getX()));
        champY.setText(String.valueOf(newSelection.getY()));
    }
});
        
       

tableMachines.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        champRef.setText(newSelection.getRefMachine());
        champDesignation.setText(newSelection.getdMachine());
        champType.setText(newSelection.getType());
        champCout.setText(String.valueOf(newSelection.getCout()));
        champX.setText(String.valueOf(newSelection.getX()));
        champY.setText(String.valueOf(newSelection.getY()));
    }
});

        // Organisation
        this.getChildren().addAll(formulaire, new Label("Machines enregistrées :"), tableMachines);
    }

private void ajouterMachine() {
    try {
        String ref = champRef.getText();
        String designation = champDesignation.getText();
        String type = champType.getText();
        double cout = Double.parseDouble(champCout.getText());
        float x = Float.parseFloat(champX.getText());
        float y = Float.parseFloat(champY.getText());
        
        if (estPositionOccupee(x, y)) {
        Alert alerte = new Alert(Alert.AlertType.WARNING);
        alerte.setHeaderText("Position occupée");
        alerte.setContentText("Une autre machine est déjà placée à cette position.");
        alerte.showAndWait();
        return;
    }
        
        // Vérification de saisie vide
        if (ref.isEmpty() || designation.isEmpty() || type.isEmpty()) {
            Alert alerte = new Alert(Alert.AlertType.WARNING);
            alerte.setHeaderText("Champs manquants");
            alerte.setContentText("Veuillez remplir tous les champs.");
            alerte.showAndWait();
            return;
        }
        
        boolean succes = controleur.ajouterMachine(ref, designation, type, cout, x, y);

        
        if (succes) {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("Succès");
            info.setContentText("La machine a bien été ajoutée !");
            info.showAndWait();
        } else {
            Alert erreur = new Alert(Alert.AlertType.ERROR);
            erreur.setHeaderText("Machine existante");
            erreur.setContentText("Une machine avec cette référence existe déjà.");
            erreur.showAndWait();
        }
        
        

    } catch (NumberFormatException e) {
        Alert erreur = new Alert(Alert.AlertType.ERROR);
        erreur.setHeaderText("Erreur de format");
        erreur.setContentText("Coordonnées ou coût invalide. Vérifiez vos valeurs.");
        erreur.showAndWait();
    }
    if (getScene() != null && getScene().getRoot() instanceof VuePrincipale vp) {
    vp.rafraichirToutesLesVues(atelier);
}

}





private void modifierMachine() {
    Machine machineSelectionnee = tableMachines.getSelectionModel().getSelectedItem();
    if (machineSelectionnee == null) {
        Alert alerte = new Alert(Alert.AlertType.WARNING);
        alerte.setHeaderText("Aucune sélection");
        alerte.setContentText("Veuillez sélectionner une machine à modifier.");
        alerte.showAndWait();
        return;
    }

    try {
        String nouvelleDesignation = champDesignation.getText();
        String nouveauType = champType.getText();
        double nouveauCout = Double.parseDouble(champCout.getText());
        float nouveauX = Float.parseFloat(champX.getText());
        float nouveauY = Float.parseFloat(champY.getText());

        if (nouvelleDesignation.isEmpty() || nouveauType.isEmpty()) {
            Alert alerte = new Alert(Alert.AlertType.WARNING);
            alerte.setHeaderText("Champs manquants");
            alerte.setContentText("Veuillez remplir tous les champs.");
            alerte.showAndWait();
            return;
        }

        // Vérification si au moins une valeur a changé
        boolean modifie = false;

        if (!nouvelleDesignation.equals(machineSelectionnee.getdEquipement())) {
            machineSelectionnee.setdEquipement(nouvelleDesignation);
            modifie = true;
        }
        if (!nouveauType.equals(machineSelectionnee.getType())) {
            machineSelectionnee.setType(nouveauType);
            modifie = true;
        }
        if (nouveauCout != machineSelectionnee.getCout()) {
            machineSelectionnee.setCout(nouveauCout);
            modifie = true;
        }
        if (nouveauX != machineSelectionnee.getX()) {
            machineSelectionnee.setX(nouveauX);
            modifie = true;
        }
        if (nouveauY != machineSelectionnee.getY()) {
            machineSelectionnee.setY(nouveauY);
            modifie = true;
        }

        if (modifie) {
            tableMachines.refresh();

            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setHeaderText("Modification réussie");
            confirmation.setContentText("La machine a été modifiée.");
            confirmation.showAndWait();

            if (getScene() != null && getScene().getRoot() instanceof VuePrincipale vp) {
                vp.rafraichirToutesLesVues(atelier);
            }
        } else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("Aucune modification");
            info.setContentText("Aucune donnée n'a été modifiée.");
            info.showAndWait();
        }

    } catch (NumberFormatException e) {
        Alert erreur = new Alert(Alert.AlertType.ERROR);
        erreur.setHeaderText("Erreur de format");
        erreur.setContentText("Vérifiez que les champs coût et coordonnées sont bien numériques.");
        erreur.showAndWait();
    }
}

    private void supprimerMachine() {
        Machine selection = tableMachines.getSelectionModel().getSelectedItem();
        if (selection != null) {
            boolean succes = controleur.supprimerMachine(selection);
            if (succes) {
                System.out.println("Machine supprimée.");
            }
        } else {
            System.out.println("Aucune machine sélectionnée.");
        }
        if (getScene() != null && getScene().getRoot() instanceof VuePrincipale vp) {
            vp.rafraichirToutesLesVues(atelier);
        }


    }
    
    
    public void rafraichirDepuis(Atelier nouvelAtelier) {
        this.atelier = nouvelAtelier;
        this.listeMachines = FXCollections.observableArrayList(nouvelAtelier.getMachines());
        this.tableMachines.setItems(listeMachines);

}
    
    private boolean estPositionOccupee(float x, float y) {
        double largeur = 60;
        double hauteur = 30;

        for (Machine m : atelier.getMachines()) {
            double mx = m.getX();
            double my = m.getY();

            boolean chevauchement = x < mx + largeur &&
                                    x + largeur > mx &&
                                    y < my + hauteur &&
                                    y + hauteur > my;

            if (chevauchement) return true;
        }
        return false;
}

    
}





