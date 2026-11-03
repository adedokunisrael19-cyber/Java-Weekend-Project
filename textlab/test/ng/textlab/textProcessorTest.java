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

    @Test
    void shouldRemoveLeadingAndTrailingWhitespace() {
        TextProcessor processor = new TextProcessor();

        String text = "   Java is fun!   ";

        assertEquals("Java is fun!", processor.normalizeWhitespace(text));
    }

    @Test
    void shouldReplaceRepeatedWhitespaceWithSingleSpace() {
        TextProcessor processor = new TextProcessor();

        String text = "Java     is    fun";

        assertEquals("Java is fun", processor.normalizeWhitespace(text));
    }

    @Test
    void shouldNormalizeTabsAndNewLines() {
        TextProcessor processor = new TextProcessor();

        String text = "Java\t\tis\n\nfun";

        assertEquals("Java is fun", processor.normalizeWhitespace(text));
    }



    @Test
    void shouldCountWholeWordOccurrencesIgnoringCase() {
        TextProcessor processor = new TextProcessor();

        String text = "Java is powerful. I enjoy JAVA. JavaScript is popular.";

        assertEquals(2, processor.countWordOccurrences(text, "java"));
    }

    @Test
    void shouldNotMatchWordInsideAnotherWord() {
        TextProcessor processor = new TextProcessor();

        String text = "JavaScript is popular.";

        assertEquals(0, processor.countWordOccurrences(text, "Java"));
    }

    @Test
    void shouldReturnZeroWhenSearchWordIsEmpty() {
        TextProcessor processor = new TextProcessor();

        assertEquals(0, processor.countWordOccurrences("Java is fun", ""));
    }


    @Test
    void shouldReplaceAllOccurrencesIgnoringCase() {
        TextProcessor processor = new TextProcessor();

        String text = "Java is fun. I love JAVA and java.";

        assertEquals(
                "Python is fun. I love Python and Python.",
                processor.replaceWord(text, "Java", "Python")
        );
    }

    @Test
    void shouldNotReplacePartOfAnotherWord() {
        TextProcessor processor = new TextProcessor();

        String text = "JavaScript is popular. Java is powerful.";

        assertEquals(
                "JavaScript is popular. Python is powerful.",
                processor.replaceWord(text, "Java", "Python")
        );
    }


    @Test
    void shouldRecognizePalindromeIgnoringCaseAndPunctuation() {
        TextProcessor processor = new TextProcessor();

        String text = "A man, a plan, a canal: Panama!";

        assertEquals(true, processor.isPalindrome(text));
    }

    @Test
    void shouldRejectTextThatIsNotPalindrome() {
        TextProcessor processor = new TextProcessor();

        String text = "Java is fun";

        assertEquals(false, processor.isPalindrome(text));
    }


    @Test
    void shouldReturnAllLongestWords() {
        TextProcessor processor = new TextProcessor();

        String text = "bright clever simple";

        assertEquals(
                java.util.List.of("bright", "clever", "simple"),
                processor.findLongestWords(text)
        );
    }

    @Test
    void shouldNotReturnDuplicateLongestWords() {
        TextProcessor processor = new TextProcessor();

        String text = "wonderful wonderful excellent";

        assertEquals(
                java.util.List.of("wonderful", "excellent"),
                processor.findLongestWords(text)
        );
    }




}
