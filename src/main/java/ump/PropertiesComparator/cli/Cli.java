package ump.PropertiesComparator.cli;

import ump.PropertiesComparator.comparator.ComparatorFactory;
import ump.PropertiesComparator.facade.ComparatorManager;
import ump.PropertiesComparator.report.ReportFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Cli {
    public void run(String[] args){
        if(args.length != 4){
            System.err.println("Usage: java --enable-preview -jar app-jar-with-dependencies.jar <file1.properties> <file2.properties> <formatType> <comparatorType>");
            System.exit(1);
        }
        String file1 = args[0];
        String file2 = args[1];
        String typeF = args[2];
        String typeC = args[3];

        try {
            checkFile(file1);
            checkFile(file2);
        } catch (IOException e) {
            System.err.println("Erreur fichier : " + e.getMessage());
            System.exit(1);
        }

        ComparatorFactory.ComparisonType ComparaionT;
        ReportFactory.FormatType FormatT ;
        try{
            ComparaionT = ComparatorFactory.ComparisonType.valueOf(typeC);
            FormatT = ReportFactory.FormatType.valueOf(typeF);
        }catch (IllegalArgumentException e){
            System.err.println("Type de comparaison ou format invalide. Types valides : SIMPLE | ADVANCED | FUZZY  et HTML | XML | JSON");
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
    private void checkFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("Fichier introuvable : " + path);
        }
        if (Files.size(Paths.get(path)) == 0) {
            throw new IOException("Fichier vide : " + path);
        }
    }
}
