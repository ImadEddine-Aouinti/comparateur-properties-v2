package ump.PropertiesComparator.comparator.impl;

import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.model.ComparisonResult;

import java.util.*;

public class simpleDiff implements PropertiesComparator {
    @Override
    public ComparisonResult compare(Map<String, String> props1, Map<String, String> props2, String file1, String file2) {
        Map<String, String> diff = new HashMap<>();
        for (String key : props1.keySet()) {
            String valeur1 = props1.get(key);
            String valeur2 = props2.get(key);

            if (valeur2 == null) {
                diff.put(key, "Introuvable dans le fichier 2");
            } else if (!valeur1.equals(valeur2)) {
                diff.put(key, valeur1 + " != " + valeur2);
            }
        }
        for (String key : props2.keySet()) {
            if (!props1.containsKey(key)) {
                diff.put(key, "Introuvable dans le fichier 1");
            }
        }
        return new ComparisonResult(file1, file2, diff);
    }
}
