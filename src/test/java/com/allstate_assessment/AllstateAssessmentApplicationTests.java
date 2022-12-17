package com.allstate_assessment;

import com.allstate_assessment.Entity.Address;
import com.allstate_assessment.Entity.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AllstateAssessmentApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
   private  TestRepository testRepository;

    private String baseURL = "http://localhost";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init()
    {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp(){
        baseURL=baseURL.concat(":").concat(port+"").concat("/phonebook");
    }

    public void testPerson(){

        Address address = new Address(1,"123","abcd","IND","DA15 1AD");

        Address address1 = new Address(2,"123","abcd","IND","DA15 1AD");
List<Address > addr = new ArrayList<>();

        Person person = new Person(1,"kushal","reddy","123456789","666725513",addr);
        Person response = restTemplate.postForObject(baseURL,person,Person.class);

    }

//    @Test
//    void contextLoads() {
//    }

}
