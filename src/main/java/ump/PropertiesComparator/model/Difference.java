package ump.PropertiesComparator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Difference implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("value1")
    private String value1;

    @JsonProperty("value2")
    private String value2;

    @JsonProperty("status")
    private String status;

    @JsonCreator
    public Difference(@JsonProperty("value1") String value1,
                      @JsonProperty("value2") String value2,
                      @JsonProperty("status") String status) {
        this.value1 = value1;
        this.value2 = value2;
        this.status = status;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status + ": " + value1 + "->" + value2;
    }
}
