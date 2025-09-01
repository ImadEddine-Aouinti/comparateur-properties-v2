 package ump.PropertiesComparator.report.impl;

import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.model.Difference;
import ump.PropertiesComparator.report.ReportFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class JSONFormat implements ReportFormatter {

    @Override
    public String format(ComparisonResult result) {
        StringBuilder consoleOutput = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        consoleOutput.append(String.format("Comparaison entre %s et %s\n", result.getFile1(), result.getFile2()));

        try {
            Map<String, Object> jsonMap = new TreeMap<>();
            jsonMap.put("file1", result.getFile1());
            jsonMap.put("file2", result.getFile2());

            if (result.areIdentical()) {
                jsonMap.put("status", "identical");
                consoleOutput.append("Les fichiers sont identiques\n");
            } else {
                TreeMap<String, Difference> sortedDifferences = new TreeMap<>(result.getDifferences());
                Map<String, Map<String, String>> differencesJson = sortedDifferences.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> {
                                    Map<String, String> diffMap = new TreeMap<>();
                                    diffMap.put("value1", entry.getValue().getValue1() != null ? entry.getValue().getValue1() : "");
                                    diffMap.put("value2", entry.getValue().getValue2() != null ? entry.getValue().getValue2() : "");
                                    diffMap.put("status", entry.getValue().getStatus() != null ? entry.getValue().getStatus() : "");
                                    return diffMap;
                                }
                        ));

                jsonMap.put("differences", differencesJson);

                consoleOutput.append("Différences trouvées :\n");
                consoleOutput.append(String.format("%-25s | %-35s | %-35s | %-10s\n", "Clé", "Valeur fichier 1", "Valeur fichier 2", "Statut"));
                consoleOutput.append(String.format("%-25s | %-35s | %-35s | %-10s\n", "-".repeat(25), "-".repeat(35), "-".repeat(35), "-".repeat(10)));

                if (differencesJson.isEmpty()) {
                    consoleOutput.append("Aucune différence trouvée (vérifiez la logique de comparaison)\n");
                } else {
                    sortedDifferences.forEach((key, diff) -> {
                        String value1 = diff.getValue1() != null ? diff.getValue1() : "";
                        String value2 = diff.getValue2() != null ? diff.getValue2() : "";
                        String status = diff.getStatus() != null ? diff.getStatus() : "";
                        String truncatedValue1 = value1.length() > 35 ? value1.substring(0, 32) + "..." : value1;
                        String truncatedValue2 = value2.length() > 35 ? value2.substring(0, 32) + "..." : value2;
                        consoleOutput.append(String.format("%-25s | %-35s | %-35s | %-10s\n", key, truncatedValue1, truncatedValue2, status));
                    });
                }
            }

            try (FileWriter writer = new FileWriter("resultats.json")) {
                writer.write(mapper.writeValueAsString(jsonMap));
                consoleOutput.append("Rapport JSON généré avec succès dans resultats.json\n");
            } catch (IOException e) {
                System.err.println("Erreur lors de l'écriture du fichier JSON : " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de la génération du JSON : " + e.getMessage());
        }

        return consoleOutput.toString();
    }
}
