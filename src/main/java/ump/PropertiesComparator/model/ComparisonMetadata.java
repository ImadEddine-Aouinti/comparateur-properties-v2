package ump.PropertiesComparator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ComparisonMetadata implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("comparisonId")
    private String idComparaison;

    @JsonProperty("sourceFile")
    private String file1;

    @JsonProperty("targetFile")
    private String file2;

    @JsonProperty("comparatorType")
    private String comparatorType;

    @JsonProperty("formatType")
    private String formatType;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("result")
    private ComparisonResult result;

    public ComparisonMetadata() {
    }

    public ComparisonMetadata(String idComparaison, String file1, String file2, String comparatorType,
                              String formatType, LocalDateTime timestamp, ComparisonResult result) {
        this.idComparaison = idComparaison;
        this.file1 = file1;
        this.file2 = file2;
        this.comparatorType = comparatorType;
        this.formatType = formatType;
        this.timestamp = timestamp;
        this.result = result;
    }

    public String getIdComparaison() {
        return idComparaison;
    }

    public void setIdComparaison(String idComparaison) {
        this.idComparaison = idComparaison;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public String getComparatorType() {
        return comparatorType;
    }

    public void setComparatorType(String comparatorType) {
        this.comparatorType = comparatorType;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ComparisonResult getResult() {
        return result;
    }

    public void setResult(ComparisonResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ComparisonMetadata{" +
                "idComparaison='" + idComparaison + '\'' +
                ", file1='" + file1 + '\'' +
                ", file2='" + file2 + '\'' +
                ", comparatorType='" + comparatorType + '\'' +
                ", formatType='" + formatType + '\'' +
                ", timestamp=" + timestamp +
                ", result=" + result +
                '}';
    }
}