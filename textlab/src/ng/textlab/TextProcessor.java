package ng.textlab;

import java.util.ArrayList;
import java.util.List;

public class TextProcessor {
//    counCharacters
//    countWords
//            countSentences

    public int countCharacters(String str) {
        return  str.length();
    }

    public int countCharactersWithoutWhitespace(String text) {
        return text.replaceAll("\\s", "").length();
    }

    public int countWords(String str) {
        if(str == null || str.isBlank()) return 0;

        String[] words = str.split("[^a-zA-Z0-9]+");
        int count = 0;
        for (String word : words) {
            if(!word.isEmpty()){
                count++;
            }
        }
        return count;
    }



    public int countSentences(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }

        String cleanedText = text.trim();

        String[] sentences = cleanedText.split("[.!?]+");

        int count = 0;

        for (String sentence : sentences) {
            if (!sentence.isBlank()) {
                count++;
            }
        }

        return count;
    }


    public String normalizeWhitespace(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }

        return text.trim().replaceAll("\\s+", " ");
    }


    public int countWordOccurrences(String text, String searchWord) {
        if (text == null || searchWord == null
                || text.isBlank() || searchWord.isBlank()) {
            return 0;
        }

        String[] words = text.split("[^a-zA-Z0-9]+");

        int count = 0;

        for (String word : words) {
            if (word.equalsIgnoreCase(searchWord)) {
                count++;
            }
        }

        return count;
    }


    public String replaceWord(String text, String searchWord, String replacement) {
        if (text == null || searchWord == null || replacement == null
                || searchWord.isBlank()) {
            return text;
        }

        StringBuilder result = new StringBuilder();

        String[] words = text.split("(?<=\\W)|(?=\\W)");

        for (String word : words) {
            if (word.equalsIgnoreCase(searchWord)) {
                result.append(replacement);
            } else {
                result.append(word);
            }
        }

        return result.toString();
    }


    public boolean isPalindrome(String text) {
        if (text == null || text.isBlank()) {
            return false;
        }

        String cleanedText = text
                .toLowerCase()
                .replaceAll("[^a-zA-Z0-9]", "");

        String reversedText = new StringBuilder(cleanedText)
                .reverse()
                .toString();

        return cleanedText.equals(reversedText);
    }


    public List<String> findLongestWords(String text) {
        List<String> longestWords = new ArrayList<>();

        if (text == null || text.isBlank()) {
            return longestWords;
        }

        String[] words = text.split("[^a-zA-Z0-9]+");

        int maxLength = 0;

        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
                longestWords.clear();
                longestWords.add(word);
            } else if (word.length() == maxLength
                    && !longestWords.contains(word)) {
                longestWords.add(word);
            }
        }

        return longestWords;
    }


}
