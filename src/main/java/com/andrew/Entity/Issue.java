package com.andrew.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Issue {
    private String id;
    private String issuetype;
    private String description;
    private int estimate;

    public Issue(@JsonProperty("id") String id, @JsonProperty("issuetype") String issuetype, @JsonProperty("description") String description, @JsonProperty("estimate") String estimate) {
        this.id = id;
        this.issuetype = issuetype;
        this.description = description;
        this.estimate = Integer.parseInt(estimate);
    }

    public String getId() {
        return id;
    }

    public String getIssuetype() {
        return issuetype;
    }

    public String getDescription() {
        return description;
    }

    public int getEstimate() {
        return estimate;
    }
}
