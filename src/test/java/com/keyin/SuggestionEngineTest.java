package com.keyin;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;import com.keyin.SuggestionEngine;

@ExtendWith(MockitoExtension.class)
public class SuggestionEngineTest {

    private SuggestionEngine suggestionEngine = new SuggestionEngine();

    @Mock
    private SuggestionsDatabase mockSuggestionDB;

    @Test
    public void testGenerateSuggestions() throws Exception {
        // Load the words from the words.txt file
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellw").contains("hello"));
    }

    @Test
    public void testGenerateSuggestionsForExistingWord() throws Exception {
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

        String existingWord = "test";
        String suggestions = suggestionEngine.generateSuggestions(existingWord);

        Assertions.assertEquals("", suggestions);
    }

    @Test
    public void testGenerateSuggestionsMisspelledWord() throws Exception {

        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

        String misspelledWord = "compiter";
        String suggestions = suggestionEngine.generateSuggestions(misspelledWord);

        Assertions.assertNotEquals("", suggestions);

        Assertions.assertTrue(suggestions.contains("computer"));
    }

    @Test
    public void testLoadDictionaryWords() throws Exception {

        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        Map<String, Integer> wordMap = suggestionEngine.getWordSuggestionDB();

        Assertions.assertFalse(wordMap.isEmpty());
    }

}
