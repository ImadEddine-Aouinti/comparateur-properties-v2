package ump.PropertiesComparator.facade;

import ump.PropertiesComparator.comparator.ComparatorFactory;
import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.load.PropertiesLoader;
import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFactory;
import ump.PropertiesComparator.report.ReportFormatter;

import java.util.HashMap;
import java.util.Map;

public class ComparatorManager {
    private final PropertiesLoader load ;
    private final ReportFactory report ;
    private final ComparatorFactory compart;
    public ComparatorManager(){
        this.load = new PropertiesLoader();
        this.report = new ReportFactory();
        this.compart = new ComparatorFactory();
    }
    public String resultat(String file1, String file2,ComparatorFactory.ComparisonType typeC,ReportFactory.FormatType typeF){
        try{
            Map<String,String> props1 = load.loadProperties(file1);
            Map<String,String> props2 = load.loadProperties(file2);

            PropertiesComparator comparator = compart.getComparator(typeC);
            ReportFormatter format = report.createFormat(typeF);

            ComparisonResult resultat = comparator.compare(props1,props2,file1,file2);
            return format.format(resultat);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
