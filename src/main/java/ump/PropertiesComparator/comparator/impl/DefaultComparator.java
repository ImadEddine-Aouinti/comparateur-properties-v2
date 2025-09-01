package ump.PropertiesComparator.comparator.impl;

import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.model.Difference;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultComparator implements PropertiesComparator {

    @Override
    public ComparisonResult compare(Map<String, String> props1, Map<String, String> props2, String file1, String file2) {
        Map<String, Difference> diff = new HashMap<>();

        diff.putAll(props1.entrySet().stream()
                .filter(entry -> {
                    String value2 = props2.get(entry.getKey());
                    return value2 == null || !entry.getValue().equals(value2);
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            String value2 = props2.get(entry.getKey());
                            if (value2 == null) {
                                return new Difference(entry.getValue(), null, "ADDED");
                            }
                            return new Difference(entry.getValue(), value2, "MODIFIED");
                        }
                )));

        diff.putAll(props2.entrySet().stream()
                .filter(entry -> !props1.containsKey(entry.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new Difference(null, entry.getValue(), "REMOVED")
                )));

        ComparisonResult result = new ComparisonResult(file1, file2, diff);
        result.setIdentical(diff.isEmpty());
        return result;
    }
}
