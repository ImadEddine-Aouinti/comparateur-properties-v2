package ump.PropertiesComparator.history;

import ump.PropertiesComparator.model.ComparisonMetadata;

import java.time.LocalDateTime;
import java.util.List;

public interface PropertiesHistory {
    void saveComparison(ComparisonMetadata metadata);
    List<ComparisonMetadata> getHistory();
    ComparisonMetadata getComparisonById(String id);
    List<ComparisonMetadata> filterHistoryByDate(LocalDateTime start, LocalDateTime end);
}
