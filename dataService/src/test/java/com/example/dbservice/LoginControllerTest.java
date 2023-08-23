package com.example.dbservice;

import com.example.dbservice.model.Language;
import com.example.dbservice.model.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.dbservice.constatnts.TestCommons.getHttpEntity;
import static com.example.dbservice.constatnts.TestCommons.json;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void hello() throws Exception {

        JSONObject requestJson = createNewDummyLoginRequest();

        HttpEntity<?> request = getHttpEntity(requestJson.toString(), "NOT FOUND","ARABIC");

        ResponseEntity<User> res =
                template.exchange("/Login/"
                        , POST
                        , request
                        , User.class);

        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
    }


    private JSONObject createNewDummyLoginRequest() throws JSONException {
        JSONObject loginRequest = new JSONObject();
        loginRequest.put("username", "A.RADWAN");
        loginRequest.put("password", "R123456");
        return loginRequest;

    }

}
