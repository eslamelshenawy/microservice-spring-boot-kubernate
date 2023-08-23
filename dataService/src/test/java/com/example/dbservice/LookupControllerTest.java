package com.example.dbservice;

import com.example.dbservice.dto.LanguageDTO;
import com.example.dbservice.model.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LookupControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void hello() throws Exception {
        ResponseEntity<List<Language>> res = template.exchange("/lookup/languages", HttpMethod.GET, null, new ParameterizedTypeReference<List<Language>>() {
        });

        List<Language> languages = res.getBody();
        assertEquals(2, languages.size());
        Language language1 = languages.get(0);
        assertEquals("AMERICAN", language1.getValue());
        assertEquals("American", language1.getId());
        Language language2 = languages.get(1);
        assertEquals("ARABIC", language2.getValue());
        assertEquals("Arabic", language2.getId());
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
    }

}
