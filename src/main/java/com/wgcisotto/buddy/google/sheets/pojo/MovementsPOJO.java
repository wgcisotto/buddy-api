package com.wgcisotto.buddy.google.sheets.pojo;

import com.fasterxml.jackson.annotation.*;


import java.util.List;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "range",
        "majorDimension",
        "values"
})
public class MovementsPOJO {

    @JsonProperty("range")
    private String range;
    @JsonProperty("majorDimension")
    private String majorDimension;
    @JsonProperty("values")
    private List<List<String>> values = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("range")
    public String getRange() {
        return range;
    }

    @JsonProperty("range")
    public void setRange(String range) {
        this.range = range;
    }

    @JsonProperty("majorDimension")
    public String getMajorDimension() {
        return majorDimension;
    }

    @JsonProperty("majorDimension")
    public void setMajorDimension(String majorDimension) {
        this.majorDimension = majorDimension;
    }

    @JsonProperty("values")
    public List<List<String>> getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(List<List<String>> values) {
        this.values = values;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}