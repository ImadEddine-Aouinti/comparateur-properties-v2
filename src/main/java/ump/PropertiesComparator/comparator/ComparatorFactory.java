package ump.PropertiesComparator.comparator;

import ump.PropertiesComparator.comparator.impl.AdvancedDiff;
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
        PropertiesComparator advancedDiff = new AdvancedDiff();

        identicalComparator.setNext(simpleDiff);
        simpleDiff.setNext(advancedDiff);

        switch (type) {
            case SIMPLE:
                return identicalComparator;
            case ADVANCED:
                return advancedDiff;
            default:
                throw new IllegalArgumentException("Type de comparaison inconnu : " + type);
        }
    }
}
