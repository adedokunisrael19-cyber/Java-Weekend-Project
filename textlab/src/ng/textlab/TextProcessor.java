package ng.textlab;

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


    public int countSentences(String str) {
        int count = 0;
        return   str.length();
    }
}
