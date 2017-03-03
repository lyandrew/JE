package com.andrew.Dao;

import com.andrew.Entity.Issue;
import com.andrew.Entity.IssueType;

public interface IssueDao {
    Issue getIssueById(String id);

    IssueType getIssuesByType(String id);
}
