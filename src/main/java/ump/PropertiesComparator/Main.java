package ump.PropertiesComparator;

import ump.PropertiesComparator.comparator.ComparatorFactory;
import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.load.PropertiesLoader;
import ump.PropertiesComparator.model.ComparisonResult;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Chemins fixes des fichiers .properties
        String file1 = "src/main/resources/file1.properties";
        String file2 = "src/main/resources/config.properties";

        PropertiesLoader loader = new PropertiesLoader();
        ComparatorFactory factory = new ComparatorFactory();

        try {
            // Chargement des fichiers .properties
            Map<String, String> props1 = loader.loadProperties(file1);
            Map<String, String> props2 = loader.loadProperties(file2);

            // Obtention du comparateur pour SimpleDiff
            PropertiesComparator comparator = factory.getComparator(ComparatorFactory.ComparisonType.SIMPLE);

            // Comparaison des deux maps
            ComparisonResult result = comparator.compare(props1, props2, file1, file2);

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
            System.exit(1);
        }
    }
}