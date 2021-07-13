package com.springapp.textanalyzer;

import com.springapp.textanalyzer.service.TextAnalyzeService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedHashMap;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextAnalyzeControllerIntegrationTests {


    @MockBean
    TextAnalyzeService service;

    @Autowired
    private WebApplicationContext webApp;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApp).build();

    }

    @Test
    public void overallAnalyzeControllerTest() throws Exception{

        given(service.getLanguage()).willReturn("en");
        given(service.getTotalWordLength()).willReturn(128);
        given(service.getCountOfLeastRepeatedWord()).willReturn(100);
        given(service.getMinLengthWord()).willReturn("min_word");
        given(service.getMaxLWordLength()).willReturn(90);
        given(service.getMostRepeatedWord()).willReturn("most_repeated");
        given(service.getMaxLengthWord()).willReturn("max_word");
        given(service.getLeastRepeatedWord()).willReturn("least_repeated");
        given(service.getMinLWordLength()).willReturn(13);
        given(service.getCountOfMostRepeatedWord()).willReturn(114);
        given(service.getUniqueWordCount()).willReturn(77);


        String content = new JSONObject(new LinkedHashMap<String,Object>(){{put("text","mehmet yz app app");}}).toString();

        MvcResult result = mockMvc.perform(post("/analyze/").contentType(MediaType.APPLICATION_JSON).
                content(content).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andExpect(jsonPath("$.language").value("en")).
                andReturn();

    }
    @Test
    public void getMaxLengthWordControllerTest() throws Exception {

        given(service.getMaxLengthWord()).willReturn("man");
        given(service.getMaxLWordLength()).willReturn(3);

        String content = new JSONObject(new LinkedHashMap<String,Object>(){{put("text","mehmet yz app app");}}).toString();

        mockMvc.perform(post("/analyze/maxlength").contentType(MediaType.APPLICATION_JSON).
                content(content).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.word").value("man"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length").value(3));

    }

    @Test
    public void getMinLengthWordControllerTest() throws Exception{
        given(service.getMinLengthWord()).willReturn("human");
        given(service.getMinLWordLength()).willReturn(5);

        String content = new JSONObject(new LinkedHashMap<String,Object>(){{put("text","mehmet yz app app");}}).toString();

        mockMvc.perform(post("/analyze/minlength").contentType(MediaType.APPLICATION_JSON).
                content(content).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.word").value("human"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length").value(5));
    }


    @Test
    public void getMostRepeatedWordControllerTest() throws Exception{
        given(service.getMostRepeatedWord()).willReturn("human");
        given(service.getCountOfMostRepeatedWord()).willReturn(3);

        String content = new JSONObject(new LinkedHashMap<String,Object>(){{put("text","mehmet yz app app");}}).toString();

        mockMvc.perform(post("/analyze/mostrepeated").contentType(MediaType.APPLICATION_JSON).
                content(content).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.word").value("human"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.repeated").value(3));
    }


    @Test
    public void getLeastRepeatedWordControllerTest() throws Exception{
        given(service.getLeastRepeatedWord()).willReturn("word");
        given(service.getCountOfLeastRepeatedWord()).willReturn(1);

        String content = new JSONObject(new LinkedHashMap<String,Object>(){{put("text","mehmet yz app app");}}).toString();

        mockMvc.perform(post("/analyze/leastrepeated").contentType(MediaType.APPLICATION_JSON).
                content(content).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.word").value("word"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.repeated").value(1));
    }

    @Test
    public void getLanguageControllerTest() throws Exception{
        given(service.getLanguage()).willReturn("en");
        String content = new JSONObject(new LinkedHashMap<String,Object>(){{put("text","mehmet yz app app");}}).toString();

        mockMvc.perform(post("/analyze/language").contentType(MediaType.APPLICATION_JSON).
                content(content).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.language").value("tr"));
    }


}