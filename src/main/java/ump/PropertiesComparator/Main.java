package ump.PropertiesComparator;

import ump.PropertiesComparator.comparator.ComparatorFactory;
import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFactory;
import ump.PropertiesComparator.report.ReportFormatter;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Création de données de test simulant deux fichiers .properties
        Map<String, String> props1 = new HashMap<>();
        props1.put("key1", "value1");
        props1.put("key2", "value2");
        props1.put("key3", "value3");

        Map<String, String> props2 = new HashMap<>();
        props2.put("key1", "value1");
        props2.put("key2", "different_value");
        props2.put("key4", "value4");

        // Initialisation des factories
        ComparatorFactory comparatorFactory = new ComparatorFactory();
        ReportFactory reportFactory = new ReportFactory();

        try {
            // Obtention du comparateur pour SimpleDiff
            PropertiesComparator comparator = comparatorFactory.getComparator(ComparatorFactory.ComparisonType.SIMPLE);

            // Comparaison des deux maps
            ComparisonResult result = comparator.compare(props1, props2, "file1.properties", "file2.properties");

            // Obtention du formateur pour JSON
            ReportFormatter formatter = reportFactory.createFormat(ReportFactory.FormatType.XML);

            // Formatage et affichage du rapport
            String report = formatter.format(result);
            System.out.println("Rapport JSON :");
            System.out.println(report);
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}