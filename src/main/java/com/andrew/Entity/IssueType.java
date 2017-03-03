package com.andrew.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class IssueType {
    private String name;
    private String id;
    private ArrayList<String> issues;

    public IssueType(@JsonProperty("name") String name, @JsonProperty("id") String id, @JsonProperty("issues") ArrayList<String> issues) {
        this.name = name;
        this.id = id;
        this.issues = issues;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getIssues() {
        return issues;
    }
}
