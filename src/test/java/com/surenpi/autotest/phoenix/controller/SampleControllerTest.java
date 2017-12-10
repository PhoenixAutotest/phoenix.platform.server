package com.surenpi.autotest.phoenix.controller;

import com.surenpi.autotest.phoenix.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.annotation.SecurityTestExecutionListeners;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SampleController.class, secure = false)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ContextConfiguration(classes = {PersistenceContext.class})
//@TestExecutionListeners({TransactionalTestExecutionListener.class})
//@SecurityTestExecutionListeners
public class SampleControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void root() throws Exception
    {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andDo(document("home"));
    }

    @Test
    public void crud() throws Exception
    {
        mockMvc.perform(get("/crud?name=d").session(getMockLoginSession()))
                .andExpect(status().isOk());
    }

    private MockHttpSession getMockLoginSession() throws Exception
    {
        return (MockHttpSession) getLoginSession();
    }

    private HttpSession getLoginSession() throws Exception
    {
        MvcResult result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();
        return result.getRequest().getSession();
    }
}