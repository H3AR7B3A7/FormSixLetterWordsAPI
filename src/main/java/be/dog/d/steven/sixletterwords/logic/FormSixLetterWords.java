package be.dog.d.steven.sixletterwords.logic;

import java.io.*;
import java.util.*;

public class FormSixLetterWords {

    private static final int WORD_LENGTH = 6;
    private final File file;
    private final Set<String> combinations = new HashSet<>();

    public FormSixLetterWords(File file) {
        this.file = file;
    }

    public Set<String> getCombinations() {
        List<String> parts = getLines();
        List<String> fullWords = extractFullWords(parts); // Files are checked by API

        String current = "";

        for (String word : fullWords) {
            List<List<String>> result = findAllSubstrings(word);
            for (List<String> list : result) {
                for (String part : list) {
                    if (!parts.contains(part)) {
                        current = "";
                        break;
                    } else if (current.length() > 0) {
                        current += " + " + part;
                    } else {
                        current += part;
                    }
                }
                if (current.length() > 0) {
                    combinations.add(current + " = " + word);
                    current = "";
                }
            }
        }
        return combinations;
    }

    private List<String> extractFullWords(List<String> firstParts) {
        List<String> fullWords = new ArrayList<>();

        for (int i = 0; i < firstParts.size(); i++) {
            if (firstParts.get(i).length() == WORD_LENGTH) {
                fullWords.add(firstParts.get(i));
                firstParts.remove(i--);
            }
        }
        return fullWords;
    }

    private List<String> getLines() {
        ArrayList<String> wordParts = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(file.getName()))) {
            while (scanner.hasNext()) {
                wordParts.add(scanner.nextLine());
            }
            return wordParts;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<List<String>> findAllSubstrings(String input) {

        if (input.length() == 1) {
            return Collections.singletonList(Collections.singletonList(input));
        }

        List<List<String>> result = new ArrayList<>();

        // Recurse
        for (List<String> subResult : findAllSubstrings(input.substring(1))) {

            // Don't split
            List<String> l2 = new ArrayList<>(subResult);
            l2.set(0, input.charAt(0) + l2.get(0));
            result.add(l2);

            // Split
            List<String> l = new ArrayList<>(subResult);
            l.add(0, input.substring(0, 1));
            result.add(l);
        }
        return result;
    }
}
