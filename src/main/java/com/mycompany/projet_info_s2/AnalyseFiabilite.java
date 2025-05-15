/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AnalyseFiabilite {

    public static class Statistiques {
        public int nbPannes = 0;
        public int nbMaintenances = 0;
        public int nbUtilisations = 0;
        public double tempsArret = 0.0;
        public Set<LocalDate> joursObserves = new HashSet<>();

        public double getFiabilite() {
            double tempsObservation = joursObserves.size() * 14.0; // 14 heures par jour (6h à 20h)
            if (tempsObservation == 0) return 0.0;
            double tempsBon = tempsObservation - tempsArret;
            return (tempsBon / tempsObservation) * 100;
        }
    }

    public static class Evenement {
        public LocalDateTime dateHeure;
        public String typeAction;

        public Evenement(LocalDateTime dateHeure, String typeAction) {
            this.dateHeure = dateHeure;
            this.typeAction = typeAction;
        }
    }

    public static Map<String, Statistiques> analyserFichier(String cheminFichier) {
        Map<String, Statistiques> statsParMachine = new HashMap<>();
        Map<String, List<Evenement>> eventsParMachine = new HashMap<>();

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("ddMMyyyy");
        DateTimeFormatter formatterHeure = DateTimeFormatter.ofPattern("HH:mm");

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parties = ligne.split(";");
                if (parties.length < 6) continue;

                String dateStr = parties[0].trim();
                String heureStr = parties[1].trim();
                String idMachine = parties[2].trim();
                String type = parties[3].trim();
                // String idOperateur = parties[4]; // pas utilisé ici
                // String commentaire = parties[5];

                LocalDate date = LocalDate.parse(dateStr, formatterDate);
                LocalTime heure = LocalTime.parse(heureStr, formatterHeure);
                LocalDateTime dateHeure = LocalDateTime.of(date, heure);

                Statistiques stats = statsParMachine.getOrDefault(idMachine, new Statistiques());
                stats.joursObserves.add(date);

                if (type.equalsIgnoreCase("A")) {
                    stats.nbUtilisations++;
                } else if (type.equalsIgnoreCase("D")) {
                    // fin d'une panne
                }

                String commentaire = parties[5].trim().toLowerCase();
                if (commentaire.contains("panne")) {
                    stats.nbPannes++;
                } else if (commentaire.contains("maintenance")) {
                    stats.nbMaintenances++;
                }

                eventsParMachine.putIfAbsent(idMachine, new ArrayList<>());
                eventsParMachine.get(idMachine).add(new Evenement(dateHeure, type));

                statsParMachine.put(idMachine, stats);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        // Calcul du temps d'arrêt
        for (Map.Entry<String, List<Evenement>> entry : eventsParMachine.entrySet()) {
            String idMachine = entry.getKey();
            List<Evenement> events = entry.getValue();
            events.sort(Comparator.comparing(e -> e.dateHeure));

            Statistiques stats = statsParMachine.get(idMachine);
            LocalDateTime debutPanne = null;

            for (Evenement e : events) {
                if (e.typeAction.equalsIgnoreCase("A")) {
                    debutPanne = e.dateHeure;
                } else if (e.typeAction.equalsIgnoreCase("D") && debutPanne != null) {
                    Duration d = Duration.between(debutPanne, e.dateHeure);
                    stats.tempsArret += d.toMinutes() / 60.0;
                    debutPanne = null;
                }
            }
        }

        return statsParMachine;
    }

    public static void afficherStatistiques(Map<String, Statistiques> stats) {
        System.out.println("\n=== STATISTIQUES DE FIABILITÉ DES MACHINES ===");
        for (Map.Entry<String, Statistiques> entry : stats.entrySet()) {
            String idMachine = entry.getKey();
            Statistiques s = entry.getValue();
            double tempsObservation = s.joursObserves.size() * 14.0;

            System.out.printf(
                "Machine %s : %d utilisations, %d pannes, %d maintenances, %.1f heures d'arrêt, %.1f heures observées, Fiabilité = %.2f%%%n",
                idMachine, s.nbUtilisations, s.nbPannes, s.nbMaintenances, s.tempsArret, tempsObservation, s.getFiabilite()
            );
        }
    }
}


