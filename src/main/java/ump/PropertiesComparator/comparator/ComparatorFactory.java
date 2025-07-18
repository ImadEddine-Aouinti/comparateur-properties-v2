package ump.PropertiesComparator.comparator;

import ump.PropertiesComparator.comparator.impl.SimpleDiff;

public class ComparatorFactory {
    public enum ComparisonType {
        SIMPLE, ADVANCED, FUZZY
    }
    public PropertiesComparator getComparator(ComparisonType type){
        if(type==null){
            throw new IllegalArgumentException("Le type de comparaison ne peut pas être null ");
        }
        switch(type){
            case SIMPLE :
                return new SimpleDiff();
            default:
                throw new IllegalArgumentException("Type de comparaison inconnu : " + type);
        }
    }
}
