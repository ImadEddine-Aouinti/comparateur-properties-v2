package ump.PropertiesComparator;

import ump.PropertiesComparator.cli.Cli;
import ump.PropertiesComparator.comparator.ComparatorFactory;
import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.facade.ComparatorManager;
import ump.PropertiesComparator.load.PropertiesLoader;
import ump.PropertiesComparator.model.ComparisonResult;
import ump.PropertiesComparator.report.ReportFactory;
import ump.PropertiesComparator.report.ReportFormatter;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        new Cli().run(args);
    }
}