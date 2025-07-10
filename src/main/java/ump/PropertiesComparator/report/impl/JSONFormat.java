package ump.PropertiesComparator.report.impl;

import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFormatter;

import java.util.Map;
import java.util.stream.Collectors;

public class JSONFormat implements ReportFormatter {
    @Override
    public String format(ComparisonResult result) {
        String differencesJson = result.getDiff().entrySet().stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));

        return "{"
                + "\"file1\":\"" + result.getFile1() + "\","
                + "\"file2\":\"" + result.getFile2() + "\","
                + "\"differences\":{" + differencesJson + "}"
                + "}";
    }
}
