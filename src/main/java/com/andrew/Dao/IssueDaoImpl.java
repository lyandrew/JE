package com.andrew.Dao;

import com.andrew.Entity.Issue;
import com.andrew.Entity.IssueType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Repository
@Qualifier("sampleData")
public class IssueDaoImpl implements IssueDao {

    private static Map<String, Issue> issues;

    private static Map<String, IssueType> issuesTypes;

    IssueDaoImpl() {
        /**
         * Constructor to load the sample files into a map.
         */
        issues = new HashMap<String, Issue>();
        issuesTypes = new HashMap<String, IssueType>();
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

        try {
            Resource[] resources = resourceResolver.getResources("classpath:issues/*.json");

            for (Resource resource : resources) {
                ObjectMapper objectMapper = new ObjectMapper();
                Issue issue = objectMapper.readValue(resource.getInputStream(), Issue.class);
                issues.put(issue.getId(), issue);
            }
            resources = resourceResolver.getResources("classpath:issuestype/*.json");
            for (Resource resource : resources) {
                ObjectMapper objectMapper = new ObjectMapper();
                IssueType issueType = objectMapper.readValue(resource.getInputStream(), IssueType.class);
                issuesTypes.put(issueType.getId(), issueType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Issue getIssueById(String id) {
        return this.issues.get(id);
    }

    @Override
    public IssueType getIssuesByType(String id) {
        return this.issuesTypes.get(id);
    }
}
