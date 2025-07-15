package ump.PropertiesComparator;

import ump.PropertiesComparator.comparator.ComparatorFactory;
import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.facade.ComparatorManager;
import ump.PropertiesComparator.load.PropertiesLoader;
import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFactory;
import ump.PropertiesComparator.report.ReportFormatter;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String file1 = "src/main/resources/file1.properties";
        String file2 = "src/main/resources/config.properties";


        try {
            ComparatorManager facade = new ComparatorManager();

            String json = facade.resultat(file1,file2,ComparatorFactory.ComparisonType.SIMPLE,ReportFactory.FormatType.HTML);
            System.out.println(json);
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Erreur inattendue: " + e.getMessage());
            System.exit(1);
        }
    }
}