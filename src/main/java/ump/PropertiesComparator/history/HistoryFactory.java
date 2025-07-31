package ump.PropertiesComparator.history;

import ump.PropertiesComparator.history.impl.FileHistory;

import java.io.File;

public class HistoryFactory {
    public enum SaveType {
        FILE,DATABASE
    }
    public static PropertiesHistory creationHistory(SaveType type){
        switch (type){
            case FILE :
                return new FileHistory();
            default:
                throw new IllegalArgumentException("Type de history inconnu : " + type);
        }

    }
}
