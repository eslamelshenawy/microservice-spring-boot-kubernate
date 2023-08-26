package vmware.services.gateway.web;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import vmware.services.gateway.OrganizationApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = OrganizationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureMockMvc
public abstract class AbstractTestWithTempBaseDir {

}

