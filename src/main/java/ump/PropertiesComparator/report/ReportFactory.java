package ump.PropertiesComparator.report;

import ump.PropertiesComparator.report.impl.HTMLFormat;
import ump.PropertiesComparator.report.impl.JSONFormat;
import ump.PropertiesComparator.report.impl.XMLFormat;

import java.util.Map;
import java.util.function.Supplier;

public class ReportFactory {

    public enum FormatType {
        JSON, HTML, XML
    }

    private static final Map<FormatType, Supplier<ReportFormatter>> FORMATTERS = Map.of(
            FormatType.JSON, JSONFormat::new,
            FormatType.HTML, HTMLFormat::new,
            FormatType.XML, XMLFormat::new
    );

    public ReportFormatter createFormat(FormatType type) {
        return FORMATTERS.getOrDefault(type,()->null).get();
    }
}
