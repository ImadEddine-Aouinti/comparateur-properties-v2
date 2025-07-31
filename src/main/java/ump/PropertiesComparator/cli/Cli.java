package ump.PropertiesComparator.cli;

import ump.PropertiesComparator.comparator.ComparatorFactory;
import ump.PropertiesComparator.facade.ComparatorManager;
import ump.PropertiesComparator.report.ReportFactory;

public class Cli {
    public void run(String[] args){
        if(args.length != 4){
            System.err.println("Usage: java -jar app.jar <file1> <file2>");
            System.exit(1);
        }
        String file1 = args[0];
        String file2 = args[1];
        String typeC = args[2];
        String typeF = args[3];

        ComparatorFactory.ComparisonType ComparaionT;
        ReportFactory.FormatType FormatT ;
        try{
            ComparaionT = ComparatorFactory.ComparisonType.valueOf(typeC);
            FormatT = ReportFactory.FormatType.valueOf(typeF);
        }catch (IllegalArgumentException e){
            System.err.println("Type de comparaison ou format invalide. Types valides : SIMPLE  et HTML | XML | JSON");
            System.exit(1);
            return;
        }


        ComparatorManager facade = new ComparatorManager();
        String res = facade.resultat(
                file1,
                file2,
                ComparaionT,
                FormatT);
        System.out.println(res);
        System.out.println("Métadonnée sauvegardée automatiquement dans :");
        System.out.println("file:///C:/Users/Lenovo%20IDEAPAD%20Slim/Desktop/stage_SQLI/history/history.json");

    }
}
