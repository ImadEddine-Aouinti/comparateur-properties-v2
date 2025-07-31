package ump.PropertiesComparator.report.impl;

import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFormatter;

import static java.util.FormatProcessor.FMT;

public class XMLFormat implements ReportFormatter {
    @Override
    public String format(ComparisonResult result){
        StringBuilder xml = new StringBuilder();
        result.getDifferences().forEach((key, diff) ->
                xml.append(FMT."<tr><td>\{key}</td><td>\{diff}</td></tr>\n")
                );
        return FMT."""
                <?xml version=\\"1.0\\" encoding=\\"UTF-8\\"?>
                <comparator>
                <titre>Comparaison entre \{result.getFile1()} et \{result.getFile2()} :</titre>
                <table>
                \{xml}
                </table>
                </comparator>
                """;
    }

}
