package ump.PropertiesComparator.report.impl;

import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFormatter;

import java.util.Map;

public class HTMLFormat implements ReportFormatter {
    @Override
    public String format(ComparisonResult result) {
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h1>Comparaison entre ").append(result.getFile1()).append(" et ").append(result.getFile2()).append("</h1>");
        html.append("<table border='1'>");
        html.append("<tr><th>Clé</th><th>Différence</th></tr>");
        result.getDiff().forEach((key, diff) -> {
            html.append("<tr><td>").append(key).append("</td><td>").append(diff).append("</td></tr>");
        });
        html.append("</table></body></html>");
        return html.toString();
    }
}
