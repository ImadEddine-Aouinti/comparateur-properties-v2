package ump.PropertiesComparator.report;

import ump.PropertiesComparator.model.ComparisonResult;

public interface ReportFormatter {
    String format(ComparisonResult result);
}
