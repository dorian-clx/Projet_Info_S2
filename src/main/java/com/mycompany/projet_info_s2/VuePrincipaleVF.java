/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

/**
 *
 * @author jolan
 */

import controleur.ControleurFiabilite;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import modele.*;

import java.io.File;

public class VuePrincipale extends BorderPane {

    private GestionnaireAteliers gestionnaire;

    private VueMachine vueMachine;
    private VuePoste vuePoste;
    private VueProduit vueProduit;
    private VueGamme vueGamme;
    private VueAtelierGlobal vueAtelier;
    private VueFiabilite vueFiabilite;
    private VueOperateur vueOperateur;
    private ControleurFiabilite controleurFiabilite;



    private TabPane onglets;

    public VuePrincipale() {
        this.gestionnaire = new GestionnaireAteliers();
        Atelier atelier = new Atelier();
        this.gestionnaire.setAtelierCourant(atelier);
        this.vueAtelier = new VueAtelierGlobal(atelier);


        // Créer les vues
        this.vueMachine = new VueMachine(atelier);
        this.vuePoste = new VuePoste(atelier);
        this.vueProduit = new VueProduit(atelier);
        this.vueGamme = new VueGamme(atelier);
        this.vueOperateur = new VueOperateur(atelier);
        this.controleurFiabilite = new ControleurFiabilite(); // on crée d'abord le contrôleur
        this.vueFiabilite = new VueFiabilite(controleurFiabilite); // puis on crée la vue avec



        // Créer les onglets
        this.onglets = new TabPane();

        Tab tabMachines = new Tab("Machines", vueMachine);
        tabMachines.setClosable(false);

        Tab tabPostes = new Tab("Postes", vuePoste);
        tabPostes.setClosable(false);
        
        Tab tabOperateurs = new Tab("Opérateurs", vueOperateur);
        tabOperateurs.setClosable(false);

        Tab tabProduits = new Tab("Produits", vueProduit);
        tabProduits.setClosable(false);

        Tab tabGammes = new Tab("Gammes", vueGamme);
        tabGammes.setClosable(false);
        
        Tab tabAtelier = new Tab("Atelier", vueAtelier);
        tabAtelier.setClosable(false);
        
        Tab tabFiabilite = new Tab("Fiabilité", vueFiabilite);
        tabFiabilite.setClosable(false); 

        onglets.getTabs().addAll(tabMachines, tabPostes, tabOperateurs, tabProduits, tabGammes, tabAtelier, tabFiabilite);
        this.setCenter(onglets);

        // Barre de chargement/sauvegarde
        Button boutonSauvegarder = new Button("Sauvegarder l’atelier");
        Button boutonCharger = new Button("Charger un atelier");

        boutonSauvegarder.setOnAction(e -> sauvegarder());
        boutonCharger.setOnAction(e -> charger());

        HBox barreFichier = new HBox(10, boutonSauvegarder, boutonCharger);
        barreFichier.setPadding(new Insets(10));
        barreFichier.setStyle("-fx-background-color: #dddddd;");

        this.setTop(barreFichier);
    }

    private void sauvegarder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer l'atelier");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier texte", "*.txt"));
        File fichier = fileChooser.showSaveDialog(this.getScene().getWindow());

        if (fichier != null) {
            gestionnaire.sauvegarderAtelier(gestionnaire.getAtelierCourant(), fichier);
        }
    }

    private void charger() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un atelier");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier texte", "*.txt"));
        File fichier = fileChooser.showOpenDialog(this.getScene().getWindow());

        if (fichier != null) {
            gestionnaire.chargerAtelier(fichier);
            Atelier nouvelAtelier = gestionnaire.getAtelierCourant();
            rafraichirToutesLesVues(nouvelAtelier);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Atelier chargé");
            alert.setContentText("L’atelier a été chargé depuis : " + fichier.getName());
            alert.showAndWait();
        }
    }

    public void rafraichirToutesLesVues(Atelier nouvelAtelier) {
        this.vueMachine.rafraichirDepuis(nouvelAtelier);
        this.vuePoste.rafraichirDepuis(nouvelAtelier);
        this.vueProduit.rafraichirDepuis(nouvelAtelier);
        this.vueGamme.rafraichirDepuis(nouvelAtelier);
        this.vueAtelier.rafraichirDepuis(nouvelAtelier);
        this.vueOperateur.rafraichirDepuis(nouvelAtelier);
    }
}
