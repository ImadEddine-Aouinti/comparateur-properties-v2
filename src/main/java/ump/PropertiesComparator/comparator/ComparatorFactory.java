package ump.PropertiesComparator.comparator;

import ump.PropertiesComparator.comparator.impl.DefaultComparator;

public class ComparatorFactory {

    public DefaultComparator getComparator() {
        return new DefaultComparator();
    }
}