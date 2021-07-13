package com.springapp.textanalyzer.controller;

import com.springapp.textanalyzer.data.TextData;
import com.springapp.textanalyzer.service.TextAnalyzeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/analyze")
public class TextAnalyzeController {

    private final TextAnalyzeService service;

    public TextAnalyzeController(TextAnalyzeService service) {
        this.service = service;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public LinkedHashMap<String, Object> overallAnalyze(@RequestBody TextData data){

        service.init(data.getText());

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();

        response.put("totalWordCount",service.getTotalWordLength());
        response.put("totalUniqueWordCount",service.getUniqueWordCount());

        response.put("maxLengthWord",new LinkedHashMap<String, Object>(){{
            put("word",service.getMaxLengthWord());
            put("length",service.getMaxLWordLength());
        }});

        response.put("minLengthWord",new LinkedHashMap<String, Object>(){{
            put("word",service.getMinLengthWord());
            put("length",service.getMinLWordLength());
        }});

        response.put("mostRepeatedWord",new LinkedHashMap<String, Object>(){{
            put("word",service.getMostRepeatedWord());
            put("repeatedCount",service.getCountOfMostRepeatedWord());
        }});

        response.put("leastRepeatedWord",new LinkedHashMap<String, Object>(){{
            put("word",service.getLeastRepeatedWord());
            put("repeatedCount",service.getCountOfLeastRepeatedWord());
        }});

        response.put("language",service.getLanguage());
        return response;
    }

    @RequestMapping(value = "/maxlength")
    public LinkedHashMap<String, Object> getMaxLengthWord(@RequestBody TextData data) {
        service.init(data.getText());
        return new LinkedHashMap<String, Object>(){{
            put("word",service.getMaxLengthWord());
            put("length",service.getMaxLWordLength());
        }};
    }


    @RequestMapping(value = "/minlength")
    public LinkedHashMap<String, Object> getMinLengthWord(@RequestBody TextData data) {
        service.init(data.getText());
        return new LinkedHashMap<String, Object>(){{
            put("word",service.getMinLengthWord());
            put("length",service.getMinLWordLength());
        }};
    }

    @RequestMapping(value = "/mostrepeated")
    public LinkedHashMap<String, Object> getMostRepeatedWord(@RequestBody TextData data) {
        service.init(data.getText());
        return new LinkedHashMap<String, Object>(){{
            put("word",service.getMostRepeatedWord());
            put("repeated",service.getCountOfMostRepeatedWord());
        }};
    }

    @RequestMapping(value = "/leastrepeated")
    public LinkedHashMap<String, Object> getLeastRepeatedWord(@RequestBody TextData data) {
        service.init(data.getText());
        return new LinkedHashMap<String, Object>(){{
            put("word",service.getLeastRepeatedWord());
            put("repeated",service.getCountOfLeastRepeatedWord());
        }};
    }
    @RequestMapping(value = "/language")
    public LinkedHashMap<String, Object> getLanguage(@RequestBody TextData data) {
        service.init(data.getText());
        return new LinkedHashMap<String, Object>(){{
            put("language",service.getLanguage());
        }};
    }

}
