package ump.PropertiesComparator.comparator;

import ump.PropertiesComparator.comparator.impl.IdenticalFilesComparator;
import ump.PropertiesComparator.comparator.impl.SimpleDiff;

public class ComparatorFactory {
    public enum ComparisonType {
        SIMPLE, ADVANCED, FUZZY
    }

    public PropertiesComparator getComparator(ComparisonType type) {
        if (type == null) {
            throw new IllegalArgumentException("Le type de comparaison ne peut pas être null");
        }

        PropertiesComparator identicalComparator = new IdenticalFilesComparator();
        PropertiesComparator simpleDiff = new SimpleDiff();
        identicalComparator.setNext(simpleDiff);

        switch (type) {
            case SIMPLE:
                return identicalComparator;
            default:
                throw new IllegalArgumentException("Type de comparaison inconnu : " + type);
        }
    }
}