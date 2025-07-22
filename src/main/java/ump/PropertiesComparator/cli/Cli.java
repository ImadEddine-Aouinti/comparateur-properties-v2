package ump.PropertiesComparator.cli;

import ump.PropertiesComparator.comparator.ComparatorFactory;
import ump.PropertiesComparator.facade.ComparatorManager;
import ump.PropertiesComparator.report.ReportFactory;

public class Cli {
    public void run(String[] args){
        if(args.length != 2){
            System.err.println("Usage: java -jar app.jar <file1> <file2>");
            System.exit(1);
        }
        String file1 = args[0];
        String file2 = args[1];

        ComparatorManager facade = new ComparatorManager();
        String res = facade.resultat(
                file1,
                file2,
                ComparatorFactory.ComparisonType.SIMPLE,
                ReportFactory.FormatType.XML);
        System.out.println(res);

    }
}
