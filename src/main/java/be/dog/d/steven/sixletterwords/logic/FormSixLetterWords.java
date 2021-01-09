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

        if (parts == null) {
            return null;
        }

        List<String> fullWords = extractFullWords(parts);

        int n = parts.size();
        String current;

        for (int i = WORD_LENGTH-1; i > 1; i--) {
            String[] permutation = new String[i];
            int[] idx = new int[i];
            while (true) {

                // Build
                for (int j = 0; j < i; j++) {
                    permutation[j] = parts.get(idx[j]);
                }

                // Stringify
                current = String.join("", permutation);

                // Check & add to combinations
                if (current.length() == WORD_LENGTH && fullWords.contains(current)) {
                    String sum = String.join(" + ", permutation);
                    combinations.add(sum + " = " + current);
                }

                // Generate next permutation
                int j = idx.length - 1;
                for (; j >= 0; j--) {
                    idx[j]++;
                    if (idx[j] < n){
                        break;
                    }
                    idx[j] = 0;
                }

                // Done when first index wraps around
                if (j < 0) break;
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
