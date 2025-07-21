package ump.PropertiesComparator.history.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ump.PropertiesComparator.history.PropertiesHistory;
import ump.PropertiesComparator.model.ComparisonMetadata;
import ump.PropertiesComparator.model.ComparisonResult;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class FileHistory implements PropertiesHistory {
    private static final String HISTORY_FILE = "history.json";
    private final ObjectMapper mapper ;
    private List<ComparisonMetadata> history ;

    public FileHistory() {
        this.mapper = new ObjectMapper() ;
        this.mapper.registerModule(new JavaTimeModule());
        this.history = historyFromFile();
    }
    private List<ComparisonMetadata> historyFromFile(){
        File file = new File(HISTORY_FILE);
        if(!file.exists()){
            return new ArrayList<>();
        }
        try {
            ComparisonMetadata[] obj = mapper.readValue(file, ComparisonMetadata[].class);
            return new ArrayList<>(List.of(obj));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'historique, démarrage avec une liste vide :"+e.getMessage());
            return new ArrayList<>();
        }
    }


    private void saveHistotyToFile(){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(HISTORY_FILE),history);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveComparison(ComparisonMetadata metadata){
        if(metadata != null){
            history.add(metadata);
            saveHistotyToFile();
        }
    }



}
