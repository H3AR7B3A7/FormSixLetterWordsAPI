package be.dog.d.steven.sixletterwords.api;
import be.dog.d.steven.sixletterwords.logic.FormSixLetterWords;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Controller

public class SixLetterWordInputController {

    @PostMapping(value = "/api/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> readFile(@RequestParam("input") MultipartFile file) throws IOException {

        if (file.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File newFile = new File(String.valueOf(UUID.randomUUID()));
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        FormSixLetterWords formSixLetterWords = new FormSixLetterWords(newFile);
        Set<String> response = formSixLetterWords.getSixLetterWords();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
