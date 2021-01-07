package be.dog.d.steven.sixletterwords.logic;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FormSixLetterWordsTest {

    @Test
    void test() throws IOException {
        File file = copyFileUsingStream(new File("test.txt"), new File(UUID.randomUUID().toString()));
        FormSixLetterWords formSixLetterWords = new FormSixLetterWords(new File(file.getName()));
        Set<String> expected = new HashSet<>(Arrays.asList("tomtom", "balbal", "bibibi", "bikast", "kastbi"));
        assertEquals(expected, formSixLetterWords.getSixLetterWords());
    }

    private static File copyFileUsingStream(File original, File copy) throws IOException {
        try (InputStream is = new FileInputStream(original); OutputStream os = new FileOutputStream(copy)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
        return copy;
    }
}