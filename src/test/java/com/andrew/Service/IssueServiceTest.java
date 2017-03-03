package com.andrew.Service;


import com.andrew.Dao.IssueDao;
import com.andrew.Entity.Issue;
import com.andrew.Entity.IssueType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class IssueServiceTest {

    @Mock
    private IssueDao issueDao;

    @InjectMocks
    private IssueService issueService = new IssueService(issueDao);


    @Test
    public void testGetIssuesByType() {
        IssueType issueType = new IssueType("/issues/11", "bug", new ArrayList<>(Arrays.asList("/issues/12", "/issues/31", "/issues/52")));
        Mockito.when(issueDao.getIssuesByType("bug")).thenReturn(issueType);
        IssueType testIssue = issueService.getIssuesByType("bug");
        assertEquals(testIssue, issueType);
    }

    @Test
    public void testGetIssueById() {
        Issue issue = new Issue("/issues/11", "bug", "test bug", "6");
        Mockito.when(issueDao.getIssueById("/issues/1")).thenReturn(issue);
        Issue testIssue = issueService.getIssueById("/issues/1");
        assertEquals(testIssue, issue);
    }

}
