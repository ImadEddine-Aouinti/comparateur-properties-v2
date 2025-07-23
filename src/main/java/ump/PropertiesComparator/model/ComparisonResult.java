package ump.PropertiesComparator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Map;

public class ComparisonResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("file1")
    private String file1;

    @JsonProperty("file2")
    private String file2;

    @JsonProperty("differences")
    private Map<String, String> differences;

    @JsonCreator
    public ComparisonResult(@JsonProperty("file1") String file1,
                            @JsonProperty("file2") String file2,
                            @JsonProperty("differences") Map<String, String> differences) {
        this.file1 = file1;
        this.file2 = file2;
        this.differences = differences;
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

    public Map<String, String> getDifferences() {
        return differences;
    }

    public void setDifferences(Map<String, String> differences) {
        this.differences = differences;
    }

    @Override
    public String toString() {
        return "ComparisonResult{" +
                "file1='" + file1 + '\'' +
                ", file2='" + file2 + '\'' +
                ", differences=" + differences +
                '}';
    }
}