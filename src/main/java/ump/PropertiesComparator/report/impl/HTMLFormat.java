package ump.PropertiesComparator.report.impl;

import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.model.Difference;
import ump.PropertiesComparator.report.ReportFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.FormatProcessor;

import static java.util.FormatProcessor.FMT;

public class HTMLFormat implements ReportFormatter {

    @Override
    public String format(ComparisonResult result) {
        StringBuilder html = new StringBuilder();
        StringBuilder consoleOutput = new StringBuilder();

        consoleOutput.append(String.format("Comparaison entre %s et %s\n", result.getFile1(), result.getFile2()));

        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"fr\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <title>Résultat de la comparaison des fichiers Properties</title>\n");
        html.append("    <style>\n");
        html.append("        body { font-family: Arial, sans-serif; margin: 20px; }\n");
        html.append("        table { border-collapse: collapse; width: 100%; }\n");
        html.append("        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n");
        html.append("        th { background-color: #f2f2f2; }\n");
        html.append("        .added { background-color: #d4edda; }\n");
        html.append("        .removed { background-color: #f8d7da; }\n");
        html.append("        .modified { background-color: #fff3cd; }\n");
        html.append("        .identical { color: green; font-weight: bold; }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append(FMT."<h1>Comparaison entre \{result.getFile1()} et \{result.getFile2()}</h1>\n");

        StringBuilder tableContent = new StringBuilder();
        if (result.areIdentical()) {
            tableContent.append("<p class=\"identical\">Les fichiers sont identiques</p>\n");
            consoleOutput.append("Les fichiers sont identiques\n");
        } else {
            tableContent.append("<table>\n");
            tableContent.append(FMT."    <tr><th>Clé</th><th>Valeur : \{result.getFile1()} </th><th>Valeur : \{result.getFile2()}</th></tr>\n");
            consoleOutput.append("Différences trouvées :\n");
            consoleOutput.append("Clé | Valeur fichier 1 | Valeur fichier 2\n");

            if (result.getDifferences().isEmpty()) {
                tableContent.append("<tr><td colspan=\"3\">Aucune différence trouvée (vérifiez la logique de comparaison)</td></tr>\n");
                consoleOutput.append("Aucune différence trouvée (vérifiez la logique de comparaison)\n");
            } else {
                result.getDifferences().forEach((key, diff) -> {
                    String value1 = diff.getValue1() != null ? diff.getValue1() : "";
                    String value2 = diff.getValue2() != null ? diff.getValue2() : "";
                    String status = diff.getStatus() != null ? diff.getStatus().toUpperCase() : "";
                    String rowClass = switch (status) {
                        case "ADDED" -> "added";
                        case "REMOVED" -> "removed";
                        case "MODIFIED" -> "modified";
                        default -> "";
                    };
                    tableContent.append(FMT."""
                        <tr class="\{rowClass}"><td>\{key}</td><td>\{value1}</td><td>\{value2}</td></tr>\n
                        """);
                    consoleOutput.append(FMT."\{key} | \{value1} | \{value2} |\n");
                });
            }
            tableContent.append("</table>\n");
        }

        html.append(tableContent);
        html.append("</body>\n");
        html.append("</html>\n");

        System.out.println(consoleOutput.toString());

        try (FileWriter writer = new FileWriter("resultat.html")) {
            writer.write(html.toString());
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier HTML : " + e.getMessage());
        }

        return "Rapport HTML généré avec succès dans resultat.html. Ouvrez-le dans votre navigateur pour voir les résultats.";
    }
}
