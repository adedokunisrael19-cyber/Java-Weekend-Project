package ng.textlab;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextProcessorTest {

    @Test
    void shouldCountAllCharactersIncludingWhitespace() {
        TextProcessor processor = new TextProcessor();

        String text = "Java is fun";

        assertEquals(11, processor.countCharacters(text));
    }

    @Test
    void shouldCountCharactersExcludingWhitespace() {
        TextProcessor processor = new TextProcessor();

        String text = "Java is fun";

        assertEquals(9, processor.countCharactersWithoutWhitespace(text));
    }


    @Test
    void shouldCountWordsInText() {
        TextProcessor processor = new TextProcessor();

        String text = "Java is fun! Let's learn Java.";

        assertEquals(7, processor.countWords(text));
    }

    @Test
    void shouldReturnZeroForEmptyText() {
        TextProcessor processor = new TextProcessor();

        assertEquals(0, processor.countWords(""));
    }

    @Test
    void shouldReturnZeroForWhitespaceOnlyText() {
        TextProcessor processor = new TextProcessor();
        String text = "   \t   ";

        assertEquals(0, processor.countWords(text));
    }


    @Test
    void shouldCountSentences() {
        TextProcessor processor = new TextProcessor();

        String text = "Java is fun. I love coding! Do you?";

        assertEquals(3, processor.countSentences(text));
    }

    @Test
    void shouldTreatConsecutivePunctuationAsOneBoundary() {
        TextProcessor processor = new TextProcessor();

        String text = "Hello!!! How are you?! Fine.";

        assertEquals(3, processor.countSentences(text));
    }

    @Test
    void shouldCountFinalFragmentWithoutPunctuation() {
        TextProcessor processor = new TextProcessor();

        String text = "Java is fun. Learning Java";

        assertEquals(2, processor.countSentences(text));
    }


}
