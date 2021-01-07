package be.dog.d.steven.sixletterwords.logic;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FormSixLetterWords {

    private static final int WORD_LENGTH = 6;
    private final File file;
    private final Set<String> sixLetterWords = new HashSet<>();

    public FormSixLetterWords(File file){
        this.file = file;
    }

    public Set<String> getSixLetterWords() throws IOException {
        List<String> firstParts = getLines();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true), 100000);

        assert firstParts != null;
        List<String> nextParts = new ArrayList<>(firstParts);

        for (int i = 0; i < firstParts.size(); i++) {
            if (firstParts.get(i).length() == WORD_LENGTH) {
                sixLetterWords.add(firstParts.get(i));
            }
            if (firstParts.get(i).length() < WORD_LENGTH) {
                for (String nextPart : nextParts) {
                    if (nextPart.length() == WORD_LENGTH - firstParts.get(i).length()) {
                        sixLetterWords.add(firstParts.get(i) + nextPart);
                        writer.newLine();
                        writer.write(firstParts.get(i) + nextPart);
                    }
                    else if (firstParts.get(i).length() + nextPart.length() < WORD_LENGTH) {
                        firstParts.add(firstParts.get(i) + nextPart);  // OutOfMemoryError on larger files
                    }
                }
            }
        }
        writer.close();
        System.out.println(sixLetterWords.size());
        return sixLetterWords;
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
