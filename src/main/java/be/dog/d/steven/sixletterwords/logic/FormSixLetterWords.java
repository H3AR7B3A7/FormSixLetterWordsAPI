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
        List<String> nextParts = new ArrayList<>(parts); // Only needed for part that is commented out

        for (int i = 0; i < parts.size(); i++) {
            for (String nextPart : nextParts) {
                if (nextPart.length() == WORD_LENGTH - parts.get(i).length()) {
                    if (fullWords.contains(parts.get(i) + nextPart)) {
                        combinations.add(parts.get(i) + " + " + nextPart + " = " + parts.get(i) + nextPart);
                    }
                }
//                else if (parts.get(i).length() + nextPart.length() < WORD_LENGTH) {
//                    parts.add(parts.get(i) + nextPart);
//                }
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
}
