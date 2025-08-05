package ump.PropertiesComparator.comparator.impl;

import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.model.ComparisonResult;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleDiff implements PropertiesComparator {
    private PropertiesComparator next;

    @Override
    public ComparisonResult compare(Map<String, String> props1, Map<String, String> props2, String file1, String file2) {
        Map<String, String> diff = new HashMap<>();

        diff.putAll(props1.entrySet().stream()
                .filter(entry -> {
                    String valeur2 = props2.get(entry.getKey());
                    return valeur2 == null || !entry.getValue().equals(valeur2);
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> props2.get(entry.getKey()) == null 
                                 ? "Introuvable dans le fichier 2" 
                                 : "Difference"
                )));

        diff.putAll(props2.entrySet().stream()
                .filter(entry -> !props1.containsKey(entry.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> "Introuvable dans le fichier 1"
                )));

        return new ComparisonResult(file1, file2, diff);
    }

    @Override
    public Boolean verifierProperties(Map<String, String> props1, Map<String, String> props2) {
        return props1.equals(props2);
    }

    @Override
    public void setNext(PropertiesComparator next) {
        this.next = next;
    }
}
