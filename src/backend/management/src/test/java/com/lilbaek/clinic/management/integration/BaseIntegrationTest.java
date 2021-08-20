package com.lilbaek.clinic.management.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BaseIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected ResultActions doPost(Object payload, String urlTemplate, Object... uriVars) throws Exception {
        return mockMvc.perform(post(urlTemplate, uriVars)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk());
    }

    protected ResultActions doGet(String urlTemplate, Object... uriVars) throws Exception {
        return mockMvc.perform(get(urlTemplate, uriVars)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    protected ResultActions doPut(Object payload, String urlTemplate, Object... uriVars) throws Exception {
        return mockMvc.perform(put(urlTemplate, uriVars)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk());
    }

    protected <T> T doPostWithResult(Object payload, Class<T> valueType, String urlTemplate, Object... uriVars) throws Exception {
        var result = doPost(payload, urlTemplate, uriVars).andReturn();
        String content = result.getResponse().getContentAsString();
        return objectMapper.readValue(content, valueType);
    }

    protected <T> T doGetWithResult(Class<T> valueType, String urlTemplate, Object... uriVars) throws Exception {
        var result = doGet(urlTemplate, uriVars).andReturn();
        String content = result.getResponse().getContentAsString();
        return objectMapper.readValue(content, valueType);
    }

    protected ResultActions doDelete(String urlTemplate, Object... uriVars) throws Exception {
        return mockMvc.perform(delete(urlTemplate, uriVars)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
