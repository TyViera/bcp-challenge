package com.bcp.bcpchallengebackend;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@CucumberContextConfiguration
@SpringBootTest(classes = BcpChallengeBackendApplication.class,
    webEnvironment = WebEnvironment.DEFINED_PORT)
public class CucumberSpringConfiguration {

}
