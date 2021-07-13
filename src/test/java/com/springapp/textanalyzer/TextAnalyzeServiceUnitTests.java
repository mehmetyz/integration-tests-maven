package com.springapp.textanalyzer;

import com.springapp.textanalyzer.service.TextAnalyzeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextAnalyzeServiceUnitTests {


    TextAnalyzeService service;

    @BeforeAll
    private void beforeAll()
    {
        String text ="Mehmet kedi kedi yildiz yildiz yildiz programmer programmer";
        service = new TextAnalyzeService();
        service.init(text);
    }



    @Test
    public void getMaxLengthWordTest(){

        String word = service.getMaxLengthWord();
        assertEquals("programmer",word);
    }

    @Test
    public void getMinLengthWordTest(){
        String word = service.getMinLengthWord();
        assertEquals("kedi",word);
    }

    @Test
    public void getMostRepeatedWordTest(){
        String word = service.getMostRepeatedWord();
        assertEquals("yildiz",word);
    }

    @Test
    public void getLeastRepeatedWordTest(){
        String word = service.getLeastRepeatedWord();
        assertEquals("Mehmet",word);
    }

    @Test
    public void getLanguageTest()
    {
        String language = service.getLanguage();
        assertEquals("undecidable",language);
    }

    @Test
    public void getLengthOfMaxLengthWordTest(){
        assertEquals(10,service.getMaxLWordLength());
    }

    @Test
    public void getLengthOfMinLengthWordTest(){
        assertEquals(4,service.getMinLWordLength());
    }

    @Test
    public void getMostRepeatedWordCountTest(){
        assertEquals(3,service.getCountOfMostRepeatedWord());
    }
    @Test
    public void getLeastRepeatedWordCountTest(){
        assertEquals(1,service.getCountOfLeastRepeatedWord());
    }

    @Test
    public void getTotalWordCountTest(){
        assertEquals(8,service.getTotalWordLength());
    }

    @Test
    public  void getUniqueWordCountTest(){
        assertEquals(4,service.getUniqueWordCount());
    }
}
