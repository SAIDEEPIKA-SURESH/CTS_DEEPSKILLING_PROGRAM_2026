package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.cognizant.springlearn.controller.CountryController;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
        assertNotNull(countryController);
    }

    @Test
    void testGetCountry() throws Exception {
        ResultActions actions = mvc.perform(get("/country").with(httpBasic("user", "pwd")));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").exists());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").exists());
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/country/az").with(httpBasic("user", "pwd")));
        // Assert status 404 (Not Found) and reason "Country not found" matching the ResponseStatus annotation on CountryNotFoundException
        actions.andExpect(status().isNotFound());
        actions.andExpect(status().reason("Country not found"));
    }

    @Test
    void testAuthenticateSuccess() throws Exception {
        ResultActions actions = mvc.perform(get("/authenticate").with(httpBasic("user", "pwd")));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.token").exists());
    }

    @Test
    void testAuthenticateFailure() throws Exception {
        ResultActions actions = mvc.perform(get("/authenticate").with(httpBasic("user", "wrongpwd")));
        actions.andExpect(status().isUnauthorized());
    }

    @Test
    void testGetCountriesWithUserRoleSuccess() throws Exception {
        ResultActions actions = mvc.perform(get("/countries").with(httpBasic("user", "pwd")));
        actions.andExpect(status().isOk());
    }

    @Test
    void testGetCountriesWithAdminRoleForbidden() throws Exception {
        ResultActions actions = mvc.perform(get("/countries").with(httpBasic("admin", "pwd")));
        actions.andExpect(status().isForbidden());
    }

    @Test
    void testGetCountriesUnauthenticated() throws Exception {
        ResultActions actions = mvc.perform(get("/countries"));
        actions.andExpect(status().isUnauthorized());
    }

    @Test
    void testGetCountriesWithJwtSuccess() throws Exception {
        // Step 1: Authenticate and get JWT token
        MvcResult result = mvc.perform(get("/authenticate").with(httpBasic("user", "pwd")))
                .andExpect(status().isOk())
                .andReturn();
        
        String responseBody = result.getResponse().getContentAsString();
        String token = new ObjectMapper().readTree(responseBody).get("token").asText();
        assertNotNull(token);

        // Step 2: Use JWT token as Bearer token to fetch countries
        ResultActions actions = mvc.perform(get("/countries").header("Authorization", "Bearer " + token));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$[0].code").value("IN"));
    }
}
