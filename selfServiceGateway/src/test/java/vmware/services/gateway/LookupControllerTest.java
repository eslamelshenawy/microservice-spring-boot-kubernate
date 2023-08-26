package vmware.services.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vmware.services.gateway.entity.Language;
import vmware.services.gateway.response.Response;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LookupControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void getLanguages() throws Exception {
        ResponseEntity<Response<List<Language>>> res = template
                .exchange(
                        "/lookup/languages",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Response<List<Language>>>(){});
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertTrue( res.getBody().getData().size() > 0 );

//        ResponseEntity<List<Language>> res = template.exchange("/lookup/languages", HttpMethod.GET, null, new ParameterizedTypeReference<List<Language>>() {
//        });
//
//        List<Language> languages = res.getBody();
//        assertEquals(2, languages.size());
//        Language language1 = languages.get(0);
//        assertEquals("AMERICAN", language1.getValue());
//        assertEquals("American", language1.getId());
//        Language language2 = languages.get(1);
//        assertEquals("ARABIC", language2.getValue());
//        assertEquals("Arabic", language2.getId());
//        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
    }

}
