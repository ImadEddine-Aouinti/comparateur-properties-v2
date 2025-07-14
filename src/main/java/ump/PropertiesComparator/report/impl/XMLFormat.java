package ump.PropertiesComparator.report.impl;

import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFormatter;

public class XMLFormat implements ReportFormatter {
    @Override
    public String format(ComparisonResult result){
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<comparator");
        xml.append("<titre>Comparaison entre :").append(result.getFile1()).append(" et ").append(result.getFile2()).append("</titre>\n");
        xml.append("<table>\n<tr>\n<th>Clé</th>\n<th>Différence</th>\n</tr>\n");
        result.getDiff().forEach((key,diff)->{
            xml.append("<tr>\n<td>").append(key).append("</td>\n<td>").append(diff).append("</td>\n</tr>\n");
        });
        xml.append("</table>\n");
        xml.append("</comparator>");
        return xml.toString();
    }

}
