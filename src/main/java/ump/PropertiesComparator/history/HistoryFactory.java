package ump.PropertiesComparator.history;

import ump.PropertiesComparator.history.impl.FileHistory;

import java.io.File;
import java.util.Map;
import java.util.function.Supplier;

public class HistoryFactory {
    public enum SaveType {
        FILE,DATABASE
    }
    private static final Map<SaveType, Supplier<PropertiesHistory>> HISTORY = Map.of(
            SaveType.FILE,FileHistory::new
    );
    public static PropertiesHistory creationHistory(SaveType type){
        return HISTORY.getOrDefault(type,()->null).get();

    }
}
