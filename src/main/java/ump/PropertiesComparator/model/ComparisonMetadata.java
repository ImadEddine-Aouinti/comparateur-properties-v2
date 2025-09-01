package ump.PropertiesComparator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ComparisonMetadata implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("comparisonId")
    private String comparisonId;

    @JsonProperty("file1")
    private String file1;

    @JsonProperty("file2")
    private String file2;

    @JsonProperty("formatType")
    private String formatType;

    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy 'à' HH:mm:ss", locale = "fr")
    private LocalDateTime timestamp;

    @JsonProperty("result")
    private ComparisonResult result;

    @JsonCreator
    public ComparisonMetadata(
            @JsonProperty("comparisonId") String comparisonId,
            @JsonProperty("file1") String file1,
            @JsonProperty("file2") String file2,
            @JsonProperty("formatType") String formatType,
            @JsonProperty("timestamp") LocalDateTime timestamp,
            @JsonProperty("result") ComparisonResult result) {
        this.comparisonId = comparisonId;
        this.file1 = file1;
        this.file2 = file2;
        this.formatType = formatType;
        this.timestamp = timestamp;
        this.result = result;
    }

    public String getComparisonId() {
        return comparisonId;
    }

    public String getFile1() {
        return file1;
    }

    public String getFile2() {
        return file2;
    }

    public String getFormatType() {
        return formatType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ComparisonResult getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ComparisonMetadata{" +
                "comparisonId='" + comparisonId + '\'' +
                ", file1='" + file1 + '\'' +
                ", file2='" + file2 + '\'' +
                ", formatType='" + formatType + '\'' +
                ", timestamp=" + timestamp +
                ", result=" + result +
                '}';
    }
}
