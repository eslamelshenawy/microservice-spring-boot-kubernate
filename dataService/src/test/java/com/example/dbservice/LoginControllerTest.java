package com.example.dbservice;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.dbservice.constatnts.TestCommons.getHttpEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void loginSuccess() throws Exception {
        JSONObject requestJson = createNewDummyLoginRequestSuccess();
        HttpEntity<?> request = getHttpEntity(requestJson.toString(), "NOT FOUND", "ARABIC");
        ResponseEntity<String> res = template.exchange("/Login/", POST, request, String.class);
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
        assertEquals("{\"result\":\"1\",\"p_user_id\":1014804}", res.getBody());
    }
    @Test
    void loginFailedValidate() throws Exception {
        JSONObject requestJson = createNewDummyLoginRequestFailed();
        HttpEntity<?> request = getHttpEntity(requestJson.toString(), "NOT FOUND", "ARABIC");
        ResponseEntity<String> res = template.exchange("/Login/", POST, request, String.class);
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
        assertEquals("فشل تسجيل الدخول. رجاء التأكد من معلومات الدخول أو الاتصال بمسئول النظام", res.getBody());
    }


    private JSONObject createNewDummyLoginRequestSuccess() throws JSONException {
        JSONObject loginRequest = new JSONObject();
        loginRequest.put("username", "A.RADWAN");
        loginRequest.put("password", "R123456");
        loginRequest.put("lang", "ARABIC");
        return loginRequest;

    }
    private JSONObject createNewDummyLoginRequestFailed() throws JSONException {
        JSONObject loginRequest = new JSONObject();
        loginRequest.put("username", "A.RADWAN789");
        loginRequest.put("password", "R123456");
        loginRequest.put("lang", "AMERICAN");
        return loginRequest;

    }

}
