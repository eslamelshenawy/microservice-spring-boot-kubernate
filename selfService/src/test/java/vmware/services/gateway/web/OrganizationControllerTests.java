package vmware.services.gateway.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import vmware.services.gateway.entity.Organization;
import vmware.services.gateway.response.Response;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@NotThreadSafe
public class OrganizationControllerTests extends AbstractTestWithTempBaseDir{

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testFindAll() {
        ResponseEntity<Response<List<Organization>>> res = template
                .exchange(
                        "/organization/all",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Response<List<Organization>>>(){});
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertTrue( res.getBody().getData().size() > 0 );

    }

}
