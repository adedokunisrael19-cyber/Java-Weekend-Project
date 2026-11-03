
package ng.textlab;

import java.util.List;
import java.util.Scanner;

public class TextLabApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TextProcessor processor = new TextProcessor();

        System.out.println("==================================");
        System.out.println("       WELCOME TO TEXTLAB");
        System.out.println("==================================");

        String originalText = readPassage(scanner);

        boolean running = true;

        while (running) {

            displayMenu();

            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1" -> {
                    System.out.println("Total characters: "
                            + processor.countCharacters(originalText));
                }

                case "2" -> {
                    System.out.println("Characters without whitespace: "
                            + processor.countCharactersWithoutWhitespace(originalText));
                }

                case "3" -> {
                    System.out.println("Total words: "
                            + processor.countWords(originalText));
                }

                case "4" -> {
                    System.out.println("Total sentences: "
                            + processor.countSentences(originalText));
                }

                case "5" -> {
                    System.out.println("Normalized text:");
                    System.out.println(processor.normalizeWhitespace(originalText));
                }

                case "6" -> {
                    System.out.print("Enter the word to search for: ");
                    String searchWord = scanner.nextLine();

                    int frequency = processor.countWordOccurrences(
                            originalText, searchWord
                    );

                    System.out.println("Occurrences: " + frequency);
                }

                case "7" -> {
                    System.out.print("Enter the word to replace: ");
                    String searchWord = scanner.nextLine();

                    System.out.print("Enter the replacement word: ");
                    String replacement = scanner.nextLine();

                    String result = processor.replaceWord(
                            originalText, searchWord, replacement
                    );

                    System.out.println("\nOriginal text:");
                    System.out.println(originalText);

                    System.out.println("\nModified text:");
                    System.out.println(result);
                }

                case "8" -> {
                    boolean result = processor.isPalindrome(originalText);

                    if (result) {
                        System.out.println("The passage is a palindrome.");
                    } else {
                        System.out.println("The passage is not a palindrome.");
                    }
                }

                case "9" -> {
                    List<String> longestWords =
                            processor.findLongestWords(originalText);

                    if (longestWords.isEmpty()) {
                        System.out.println("No words found.");
                    } else {
                        System.out.println("Longest word(s): "
                                + longestWords);
                    }
                }

                case "10" -> {
                    System.out.println("\nYour original passage:");
                    System.out.println(originalText);
                }

                case "11" -> {
                    System.out.println("Thank you for using TextLab.");
                    running = false;
                }

                default -> System.out.println(
                        "Invalid selection. Please choose between 1 and 11."
                );
            }
        }

        scanner.close();
    }

    private static String readPassage(Scanner scanner) {

        System.out.println("\nEnter your passage.");
        System.out.println("Press ENTER on an empty line when finished.");

        StringBuilder passage = new StringBuilder();

        while (true) {
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                break;
            }

            passage.append(line).append("\n");
        }

        return passage.toString();
    }

    private static void displayMenu() {

        System.out.println("\n========== TEXTLAB MENU ==========");
        System.out.println("1. Count All Characters");
        System.out.println("2. Count Characters Without Whitespace");
        System.out.println("3. Count Words");
        System.out.println("4. Count Sentences");
        System.out.println("5. Normalize Whitespace");
        System.out.println("6. Search for a Word");
        System.out.println("7. Replace a Word");
        System.out.println("8. Check Palindrome");
        System.out.println("9. Find Longest Words");
        System.out.println("10. Display Original Passage");
        System.out.println("11. Exit");
        System.out.println("==================================");
    }
}
