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
        List<String> parts = getLinesFromFile();
        List<String> fullWords = extractFullWords(parts); // Files are checked by API

        String current = "";

        for (String word : fullWords) {
            List<List<String>> listOfSubStrings = findAllSubstrings(word);
            for (List<String> subStrings : listOfSubStrings) {
                for (String string : subStrings) {
                    if (!parts.contains(string)) {
                        current = "";
                        break;
                    } else if (current.length() > 0) {
                        current += " + " + string;
                    } else {
                        current += string;
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

    private List<String> getLinesFromFile() {
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

        List<List<String>> listOfSubstrings = new ArrayList<>();

        for (List<String> subResult : findAllSubstrings(input.substring(1))) {

            List<String> noSplits = new ArrayList<>(subResult);
            noSplits.set(0, input.charAt(0) + noSplits.get(0));
            listOfSubstrings.add(noSplits);

            List<String> splits = new ArrayList<>(subResult);
            splits.add(0, input.substring(0, 1));
            listOfSubstrings.add(splits);
        }
        return listOfSubstrings;
    }
}
