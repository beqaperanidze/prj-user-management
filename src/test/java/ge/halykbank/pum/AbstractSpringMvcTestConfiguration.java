package ge.halykbank.pum;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@AutoConfigureMockMvc
public abstract class AbstractSpringMvcTestConfiguration {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    private final String username;
    private final String password;

    @Autowired
    MockMvc mockMvc;

    public AbstractSpringMvcTestConfiguration(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected ResultActions performPOSTAndExpectStatusOk(Object object, String url) throws Exception {
        return mockMvc.perform(getPOSTMockMvcBuilder(object, url)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    protected ResultActions performPOSTAndExpectStatusCreated(Object object, String url) throws Exception {
        return mockMvc.perform(getPOSTMockMvcBuilder(object, url)).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    protected ResultActions performPOSTAndExpectStatusBadRequest(Object object, String url) throws Exception {
        return mockMvc.perform(getPOSTMockMvcBuilder(object, url)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    protected void performPOSTAndExpectStatusOkAndExpect(Object object, Object expected, String url) throws Exception {
        performPOSTAndExpectStatusOk(object, url).andExpect(MockMvcResultMatchers.content().json(MAPPER.writeValueAsString(expected)));
    }
    protected void performPOSTAndExpectStatusCreatedAndExpect(Object object, Object expected, String url) throws Exception {
        performPOSTAndExpectStatusCreated(object, url).andExpect(MockMvcResultMatchers.content().json(MAPPER.writeValueAsString(expected)));
    }

    protected void performGETAndExpectStatusIsNotFound(String url) throws Exception {
        mockMvc.perform(getGETMockMvcBuilder(url)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    protected void performGETAndExpectStatusOkAndExpect(Object expected, String url) throws Exception {
        mockMvc.perform(getGETMockMvcBuilder(url)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(MAPPER.writeValueAsString(expected)));
    }

    protected void performGETAndExpectStatusOkAndExpect(String expected, String url) throws Exception {
        mockMvc.perform(getGETMockMvcBuilder(url)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }

    protected void performDELETEAndExpect5xx(String url) throws Exception {
        mockMvc.perform(getDELETEMockMvcBuilder(url)).andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    protected void performUpdateAndExpectStatusOk(Object object, String url) throws Exception {
        mockMvc.perform(getPUTMockMvcBuilder(object, url)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private <T> MockHttpServletRequestBuilder getPOSTMockMvcBuilder(T object, String url) throws Exception {
        return MockMvcRequestBuilders.post(url)
                .with(httpBasic(username, password))
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(object));
    }

    private MockHttpServletRequestBuilder getGETMockMvcBuilder(String url) throws Exception {
        return MockMvcRequestBuilders.get(url)
                .with(httpBasic(username, password))
                .contentType(MediaType.APPLICATION_JSON);
    }

    private <T> MockHttpServletRequestBuilder getPUTMockMvcBuilder(T object, String url) throws Exception {
        return MockMvcRequestBuilders.put(url)
                .with(httpBasic(username, password))
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(object));
    }

    private MockHttpServletRequestBuilder getDELETEMockMvcBuilder(String url) throws Exception {
        return MockMvcRequestBuilders.delete(url)
                .with(httpBasic(username, password))
                .contentType(MediaType.APPLICATION_JSON);
    }
}
