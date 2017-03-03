package com.andrew.Service;

import com.andrew.Dao.IssueDao;
import com.andrew.Entity.Issue;
import com.andrew.Entity.IssueType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    @Autowired
    @Qualifier("sampleData")
    private IssueDao issueDao;

    public IssueService(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    public Issue getIssueById(String id) {
        return this.issueDao.getIssueById(id);
    }

    public IssueType getIssuesByType(String id) {
        return this.issueDao.getIssuesByType(id);
    }
}
