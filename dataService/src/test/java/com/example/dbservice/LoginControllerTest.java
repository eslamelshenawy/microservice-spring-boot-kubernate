package com.example.dbservice;

import com.example.dbservice.model.Employee;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.dbservice.constatnts.TestCommons.getHttpEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void loginSuccess() throws Exception {
        JSONObject requestJson = createNewDummyLoginRequestSuccess();
        HttpEntity<?> request = getHttpEntity(requestJson.toString(), "NOT FOUND", "AMERICAN");
        ResponseEntity<String> res = template.exchange("/Login", POST, request, String.class);
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
        assertEquals("{\"result\":\"1\",\"p_user_id\":1014804,\"employeeData\":null}", res.getBody());
    }

    @Test
    public void loginFailedValidate() throws Exception {
        JSONObject requestJson = createNewDummyLoginRequestFailed();
        HttpEntity<?> request = getHttpEntity(requestJson.toString(), "NOT FOUND", "ARABIC");
        ResponseEntity<String> res = template.exchange("/Login", POST, request, String.class);
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
        assertEquals("{\"result\":\"فشل تسجيل الدخول. رجاء التأكد من معلومات الدخول أو الاتصال بمسئول النظام\",\"p_user_id\":0}", res.getBody());
    }

    @Test
    public void SetSessionTest() throws Exception {
        HttpEntity<?> request = getHttpEntity("null", "ARABIC");
        ResponseEntity<Void> res = template.exchange("/alter?p_user_id=1014804", POST, request, Void.class);
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
    }

    @Test
    public void getInfoTest() throws Exception {
        SetSessionTest();
        HttpEntity<?> request = getHttpEntity("null", "ARABIC");
        Integer pUserId = 1014804;
        ResponseEntity<Employee> res = template.exchange("/info/" + pUserId, GET, request, new ParameterizedTypeReference<Employee>() {
        });
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
    }

    private JSONObject createNewDummyLoginRequestSuccess() throws JSONException {
        JSONObject loginRequest = new JSONObject();
        loginRequest.put("userName", "A.RADWAN");
        loginRequest.put("password", "R123456");
        return loginRequest;

    }

    private JSONObject createNewDummyLoginRequestFailed() throws JSONException {
        JSONObject loginRequest = new JSONObject();
        loginRequest.put("userName", "A.RADWAN789");
        loginRequest.put("password", "R123456");
        return loginRequest;

    }

}
