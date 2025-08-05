package ump.PropertiesComparator.report.impl;

import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFormatter;

import java.util.Map;
import java.util.stream.Collectors;

public class JSONFormat implements ReportFormatter {
    @Override
    public String format(ComparisonResult result) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        json.append("\"file1\":\"").append(result.getFile1()).append("\",");
        json.append("\"file2\":\"").append(result.getFile2()).append("\",");

        if (result.areIdentical()) {
            json.append("\"status\":\"identical\"");
        } else {
            String differencesJson = result.getDifferences().entrySet().stream()
                    .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                    .collect(Collectors.joining(","));
            json.append("\"differences\":{").append(differencesJson).append("}");
        }

        json.append("}");
        return json.toString();
    }
}