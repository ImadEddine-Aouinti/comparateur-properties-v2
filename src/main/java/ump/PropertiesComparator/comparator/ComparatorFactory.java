package ump.PropertiesComparator.comparator;

import ump.PropertiesComparator.comparator.impl.AdvancedDiff;
import ump.PropertiesComparator.comparator.impl.FuzzyDiff;
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
        PropertiesComparator simpleDiff = new SimpleDiff();
        PropertiesComparator advancedDiff = new AdvancedDiff();
        PropertiesComparator fuzzyDiff = new FuzzyDiff();

        comparatorMap = new EnumMap<>(ComparisonType.class);
        comparatorMap.put(ComparisonType.SIMPLE,()->simpleDiff);
        comparatorMap.put(ComparisonType.ADVANCED,()->advancedDiff);
        comparatorMap.put(ComparisonType.FUZZY,()->fuzzyDiff);
    }

    public PropertiesComparator getComparator(ComparisonType type) {
        Supplier<PropertiesComparator> comparatorSupplier = comparatorMap.get(type);
        return comparatorSupplier.get();
    }
}
