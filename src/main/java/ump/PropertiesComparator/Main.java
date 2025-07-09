package ump.PropertiesComparator;

import ump.PropertiesComparator.comparator.ComparatorFactory;
import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.model.ComparisonResult;

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

        // Initialisation de la factory
        ComparatorFactory factory = new ComparatorFactory();

        try {
            // Obtention du comparateur pour simpleDiff
            PropertiesComparator comparator = factory.getComparator(ComparatorFactory.ComparisonType.SIMPLE);

            // Comparaison des deux maps
            ComparisonResult result = comparator.compare(props1, props2, "file1.properties", "file2.properties");

            // Affichage des résultats
            System.out.println("Comparaison entre " + result.getFile1() + " et " + result.getFile2() + ":");
            Map<String, String> differences = result.getDiff();
            if (differences.isEmpty()) {
                System.out.println("Aucune différence trouvée.");
            } else {
                differences.forEach((key, diff) -> System.out.println("Clé: " + key + " -> " + diff));
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur: " + e.getMessage());
        }
    }
}