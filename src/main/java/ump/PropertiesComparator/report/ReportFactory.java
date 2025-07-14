package ump.PropertiesComparator.report;

import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.impl.HTMLFormat;
import ump.PropertiesComparator.report.impl.JSONFormat;

public class ReportFactory {
    public enum FormatType{
        JSON,HTML,XML
    }
    public ReportFormatter createFormat(FormatType type){
        switch(type){
            case JSON :
                return new JSONFormat() ;
            case HTML :
                return new HTMLFormat() ;
            default:
                throw new IllegalArgumentException("Type de format inconnu : " + type);
        }
    }
}
