package be.dog.d.steven.sixletterwords.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SixLetterWordInputControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void whenFileUploaded_thenVerifyStatus() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "input",
                "random_file_name.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "Hello, World!".getBytes()
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/api/file").file(file))
                .andExpect(status().isOk());
    }
}