package be.dog.d.steven.sixletterwords.logic;

import java.io.*;
import java.util.*;

public class FormSixLetterWords {

    private static final int WORD_LENGTH = 6;
    private final File file;
    private final Set<String> combinations = new HashSet<>();

    public FormSixLetterWords(File file){
        this.file = file;
    }

    public Set<String> getCombinations() {
        List<String> parts = getLines();

        if (parts == null){
            return null;
        }

        List<String> fullWords = extractFullWords(parts);
        List<String> nextParts = new ArrayList<>(parts);

        for (int i = 0; i < parts.size(); i++) {
            for (int j = 0; j < nextParts.size(); j++) {
                if(nextParts.get(j).length() == WORD_LENGTH - parts.get(i).length()){
                    if (fullWords.contains(parts.get(i) + nextParts.get(j))) {
                        combinations.add(parts.get(i) + " + " + nextParts.get(j) + " = " + parts.get(i) + nextParts.get(j));
                    }
                }
//                else if (parts.get(i).length() + nextParts.get(j).length() < WORD_LENGTH){
//                    parts.add(parts.get(i) + nextParts.get(j));
//                }
            }
        }

        System.out.println(combinations.size());
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
