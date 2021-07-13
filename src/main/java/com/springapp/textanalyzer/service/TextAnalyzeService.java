package com.springapp.textanalyzer.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TextAnalyzeService {

    private String text;
    private LinkedHashMap<String, Integer> textMap;

    private int totalWordCount;


    @Autowired
    public TextAnalyzeService() {
    }

    public void init(String text){
        this.text = text;
        this.textMap = new LinkedHashMap<>();

        convertTextToMap();
    }

    private void convertTextToMap(){

        String[] allWordsInText = text.trim().
                replaceAll("[^a-zA-Z0-9\\sıİşŞöÖçÇğĞüÜ]", "").
                split(" ");

        totalWordCount = allWordsInText.length;

        for(String word : allWordsInText)
        {
            if(textMap.containsKey(word))
            {
                int frequency = textMap.get(word);
                textMap.put(word,++frequency);
            }
            else
                textMap.put(word,1);
        }
    }

    public String getLanguage(){
        boolean isEnglish = text.matches(".*[WwXxQq].*");
        boolean isTurkish = text.matches(".*[ŞşÖöÇçİıĞğÜü].*");

        if(isEnglish && !isTurkish) return "en";
        else if (!isEnglish && isTurkish) return "tr";
        else return "undecidable";
    }

    public String getMaxLengthWord()
    {
        if(textMap.isEmpty()) return "";

        String wordFound = this.textMap.keySet().iterator().next();
        for(Map.Entry<String, Integer> pair : this.textMap.entrySet())
            if(pair.getKey().length() > wordFound.length())
                wordFound = pair.getKey();



        return wordFound;
    }

    public String getMinLengthWord()
    {
        if(textMap.isEmpty()) return "";

        String wordFound = this.textMap.keySet().iterator().next();

        for(Map.Entry<String, Integer> pair : this.textMap.entrySet())
            if(pair.getKey().length() < wordFound.length())
                wordFound = pair.getKey();

        return wordFound;
    }

    public String getMostRepeatedWord(){
        if(textMap.isEmpty()) return "";

        String wordFound = this.textMap.keySet().iterator().next();

        for(Map.Entry<String, Integer> pair : this.textMap.entrySet())
            if(pair.getValue() > this.textMap.get(wordFound))
                wordFound = pair.getKey();

        return wordFound;
    }

    public String getLeastRepeatedWord(){
        if(textMap.isEmpty()) return "";

        String wordFound = this.textMap.keySet().iterator().next();

        for(Map.Entry<String, Integer> pair : this.textMap.entrySet())
            if(pair.getValue() < this.textMap.get(wordFound))
                wordFound = pair.getKey();

        return wordFound;
    }

    public int getTotalWordLength(){
        return totalWordCount;
    }
    public int getUniqueWordCount(){
        return textMap.keySet().size();
    }
    public int getMaxLWordLength(){
        return this.getMaxLengthWord().length();
    }
    public int getMinLWordLength()
    {
        return this.getMinLengthWord().length();
    }
    public int getCountOfMostRepeatedWord(){
        return this.textMap.get(this.getMostRepeatedWord());
    }
    public int getCountOfLeastRepeatedWord(){
        return this.textMap.get(this.getLeastRepeatedWord());
    }

}
