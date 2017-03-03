package com.andrew.controller;

import com.andrew.Controller.IssueController;
import com.andrew.Entity.Issue;
import com.andrew.Entity.IssueType;
import com.andrew.Service.IssueService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(IssueController.class)
public class IssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssueService issueService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetIssueById() throws Exception {
        Issue issue = new Issue("/issues/11", "bug", "test bug", "6");
        BDDMockito.given(this.issueService.getIssueById("/issues/11")).willReturn(issue);

        this.mockMvc.perform(get("/issues/11").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("/issues/11"))
                .andExpect(jsonPath("$.issuetype").value("bug"))
                .andExpect(jsonPath("$.description").value("test bug"))
                .andExpect(jsonPath("$.estimate").value("6"));

    }

    @Test
    public void testBadGetIssueById() throws Exception {
        this.mockMvc.perform(get("/issues/621").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetIssuesByType() throws Exception {
        ArrayList arrayList = new ArrayList<>(Arrays.asList("/issues/12", "/issues/31", "/issues/52"));
        IssueType issueType = new IssueType("bug", "/issuetypes/bug", arrayList);
        BDDMockito.given(this.issueService.getIssuesByType("/issuetypes/bug")).willReturn(issueType);

        this.mockMvc.perform(get("/issuetypes/bug").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("/issuetypes/bug"))
                .andExpect(jsonPath("$.name").value("bug"))
                .andExpect(jsonPath("$.issues").value(arrayList));

    }

    @Test
    public void testBadGetIssuesByType() throws Exception {
        this.mockMvc.perform(get("/issuetypes/bugy").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
