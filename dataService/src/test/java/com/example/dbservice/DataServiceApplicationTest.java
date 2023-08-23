package com.example.dbservice;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class DataServiceApplicationTest {
    @Test
    public void contextLoads() {

    }
//    @Autowired
//    private TestRestTemplate template;
//
//    @org.junit.Test
//    public void testFindAll() {
////        ResponseEntity<Response<List<Organization>>> res = template
////                .exchange(
////                        "/organization/all",
////                        HttpMethod.GET,
////                        null,
////                        new ParameterizedTypeReference<Response<List<Organization>>>(){});
//        assertEquals(HttpStatus.OK,200);
////        assertTrue( res.getBody().getData().size() > 0 );
//
//    }
//
//    @Configuration
//    public static class TestConfiguration {
//
//    }
//
//
}
