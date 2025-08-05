package ump.PropertiesComparator.report.impl;

import java.util.FormatProcessor.* ;
import static java.util.FormatProcessor.FMT;
import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFormatter;

public class HTMLFormat implements ReportFormatter {

    @Override
    public String format(ComparisonResult result) {
        StringBuilder html = new StringBuilder();

        if (result.areIdentical()) {
            html.append("<p>Les fichiers sont identiques</p>\n");
        } else {
            result.getDifferences().forEach((key, diff) ->
                    html.append(FMT."<tr><td>\{key}</td><td>\{diff}</td></tr>\n")
            );
        }

        return FMT."""
            <html>
            <body>
              <h1>Comparaison entre \{result.getFile1()} et \{result.getFile2()}</h1>

              <table border='1'>
                <tr><th>Clé</th><th>Différence</th></tr>
                \{html}
              </table>

            </body>
            </html>
            """;
    }
}