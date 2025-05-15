/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;

/**
 *
 * @author cypri
 */
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class AnalyseurMaintenance {

    public static void analyserFiabilite(String cheminFichier) {
        Map<String, StatistiqueMachine> stats = new HashMap<>();
        Map<String, LocalDateTime> dernierArret = new HashMap<>();
        Set<LocalDate> joursUtilisation = new HashSet<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH :mm", Locale.FRANCE);

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;

            while ((ligne = reader.readLine()) != null) {
                if (ligne.trim().equals("End Of File")) break;
                if (ligne.trim().isEmpty()) continue;

                String[] parties = ligne.trim().split("\\s+");
                String dateStr = parties[0];
                String heureStr = parties[1] + " " + parties[2];
                String machine = parties[3];
                String evenement = parties[4];

                LocalDateTime horodatage = LocalDateTime.parse(dateStr + " " + heureStr, formatter);
                LocalDate jour = horodatage.toLocalDate();
                joursUtilisation.add(jour);

                stats.putIfAbsent(machine, new StatistiqueMachine(machine));

                if (evenement.equals("A")) {
                    dernierArret.put(machine, horodatage);
                } else if (evenement.equals("D")) {
                    if (dernierArret.containsKey(machine)) {
                        Duration arret = Duration.between(dernierArret.get(machine), horodatage);
                        stats.get(machine).ajouterArret(arret.toMinutes());
                        dernierArret.remove(machine);
                    }
                }
            }

            for (StatistiqueMachine stat : stats.values()) {
                stat.ajouterJourObservation(); // 1 jour = 14h d'observation
            }

            List<StatistiqueMachine> listeStats = new ArrayList<>(stats.values());
            listeStats.sort(Comparator.comparingDouble(StatistiqueMachine::getFiabilite).reversed());

            System.out.println("\n--- Fiabilités des machines ---");
            for (StatistiqueMachine stat : listeStats) {
                System.out.println(stat);
            }

        } catch (IOException | DateTimeParseException e) {
            e.printStackTrace();
        }
    }
}

