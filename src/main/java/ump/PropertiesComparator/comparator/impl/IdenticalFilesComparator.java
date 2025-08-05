package ump.PropertiesComparator.comparator.impl;

import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.model.ComparisonResult;

import java.util.Map;

public class IdenticalFilesComparator implements PropertiesComparator {
    private PropertiesComparator next;

    @Override
    public ComparisonResult compare(Map<String, String> props1, Map<String, String> props2, String file1, String file2) {
        ComparisonResult result = new ComparisonResult(file1, file2, null);
        if (props1.equals(props2)) {
            result.setIdentical(true);
            return result;
        }

        result.setIdentical(false);
        if (next != null) {
            return next.compare(props1, props2, file1, file2);
        }
        return result;
    }

    @Override
    public void setNext(PropertiesComparator next) {
        this.next = next;
    }

    @Override
    public Boolean verifierProperties(Map<String,String> props1,Map<String,String> props2){
        return props1.equals(props2);
    }
}