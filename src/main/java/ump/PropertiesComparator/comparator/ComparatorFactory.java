package ump.PropertiesComparator.comparator;

import ump.PropertiesComparator.comparator.impl.AdvancedDiff;
import ump.PropertiesComparator.comparator.impl.FuzzyDiff;
import ump.PropertiesComparator.comparator.impl.IdenticalFilesComparator;
import ump.PropertiesComparator.comparator.impl.SimpleDiff;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class ComparatorFactory {
    public enum ComparisonType {
        SIMPLE, ADVANCED, FUZZY
    }

    private final Map<ComparisonType, Supplier<PropertiesComparator>> comparatorMap;

    public ComparatorFactory() {
        PropertiesComparator identicalComparator = new IdenticalFilesComparator();
        PropertiesComparator simpleDiff = new SimpleDiff();
        PropertiesComparator advancedDiff = new AdvancedDiff();
        PropertiesComparator fuzzyDiff = new FuzzyDiff();

        identicalComparator.setNext(simpleDiff);
        simpleDiff.setNext(advancedDiff);
        advancedDiff.setNext(fuzzyDiff);

        comparatorMap = new EnumMap<>(ComparisonType.class);
        comparatorMap.put(ComparisonType.SIMPLE,()->{
            identicalComparator.setNext(simpleDiff);
            return identicalComparator ;
        });
        comparatorMap.put(ComparisonType.ADVANCED,()->{
            simpleDiff.setNext(null);
            return simpleDiff;
        });
        comparatorMap.put(ComparisonType.FUZZY,()->fuzzyDiff);
    }

    public PropertiesComparator getComparator(ComparisonType type) {
        if (type == null) {
            throw new IllegalArgumentException("Comparison type cannot be null");
        }

        Supplier<PropertiesComparator> comparatorSupplier = comparatorMap.get(type);
        if (comparatorSupplier == null) {
            throw new IllegalArgumentException("Unknown comparison type: " + type);
        }

        return comparatorSupplier.get();
    }
}
