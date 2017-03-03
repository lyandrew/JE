package com.andrew.Controller;

import com.andrew.Entity.Issue;
import com.andrew.Entity.IssueType;
import com.andrew.Exception.NotFoundException;
import com.andrew.Service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping(value = "/issues/{id}")
    public Issue getIssueById(@PathVariable("id") String id) {
        Issue issue = issueService.getIssueById("/issues/" + id);
        if (issue == null) {
            throw new NotFoundException(id);
        }
        return issue;
    }

    @GetMapping(value = "/issuetypes/{type}")
    public IssueType getIssuesByType(@PathVariable("type") String type) {
        IssueType issueType = issueService.getIssuesByType("/issuetypes/" + type);
        if (issueType == null) {
            throw new NotFoundException(type);
        }
        return issueType;
    }
}
