package ump.PropertiesComparator.comparator.impl;

import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.model.ComparisonResult;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AdvancedDiff implements PropertiesComparator {
    private PropertiesComparator next ;

    @Override
    public ComparisonResult  compare(Map<String,String> props1, Map<String,String> props2, String file1, String file2){
        Map<String,String> diff =  new HashMap<>();

        diff.putAll(props1.entrySet().stream()
                .filter(e -> {
                         String value2 = props2.get(e.getKey());
                         return value2 == null || !e.getValue().equals(value2);})

                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> {
                            String value2 = props2.get(e.getKey());
                            if(value2 == null){
                                return "Introuvable :Valeur dans "+file1+" est : "+e.getValue()+", Introuvable dans "+file2 ;
                            }
                            return "Difference :Valeur dans " + file1 + ": " + e.getValue() + ", Valeur dans " + file2 + ": " + value2;

                        })));

        diff.putAll(props2.entrySet().stream()
                .filter(e -> !props1.containsKey(e.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> "Introuvable dans " + file1 + ", Valeur dans " + file2 + ": " + e.getValue()
                )));


        ComparisonResult result = new ComparisonResult(file1, file2, diff);
        result.setIdentical(diff.isEmpty());

        if (next != null && !result.areIdentical()) {
            return next.compare(props1, props2, file1, file2);
        }
        return result;
    }

}
