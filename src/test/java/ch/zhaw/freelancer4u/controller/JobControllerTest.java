package ch.zhaw.freelancer4u.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import ch.zhaw.freelancer4u.repository.CompanyRepository;
import ch.zhaw.freelancer4u.security.TestSecurityConfig;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    private static String createdJobId;

    private String getCompanyId() {
        return companyRepository.findAll().get(0).getId();
    }

    @Test
    @Order(1)
    void createJobTest() throws Exception {
        String json = "{"
                + "\"title\":\"Test Job\","
                + "\"description\":\"Test Description\","
                + "\"jobType\":\"IMPLEMENT\","
                + "\"earnings\":99.0,"
                + "\"companyId\":\"" + getCompanyId() + "\""
                + "}";

        MvcResult result = mockMvc.perform(post("/api/job")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        Pattern pattern = Pattern.compile("\"id\"\\s*:\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            createdJobId = matcher.group(1);
        }
    }

    @Test
    @Order(2)
    void getCreatedJobTest() throws Exception {
        mockMvc.perform(get("/api/job/" + createdJobId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Job"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.companyId").value(getCompanyId()));
    }

    @Test
    @Order(3)
    void deleteCreatedJobTest() throws Exception {
        mockMvc.perform(delete("/api/job/" + createdJobId))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void getDeletedJobTest() throws Exception {
        mockMvc.perform(get("/api/job/" + createdJobId))
                .andExpect(status().isNotFound());
    }
}