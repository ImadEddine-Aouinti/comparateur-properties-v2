package ump.PropertiesComparator.comparator;

import ump.PropertiesComparator.model.ComparisonResult;

import java.util.Map;

public interface PropertiesComparator {
    ComparisonResult compare(Map<String,String> props1,Map<String,String> props2,String file1,String file2);
    void setNext(PropertiesComparator next);
}

