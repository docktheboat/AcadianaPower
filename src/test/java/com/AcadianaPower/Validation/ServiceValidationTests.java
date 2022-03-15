package com.AcadianaPower.Validation;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class ServiceValidationTests {


    @ParameterizedTest
    @CsvSource({
            "Internet,true",
            "Electric,true"
    })
    @DisplayName("Services, service check test")
    public void serviceCheckTest(String service, boolean expected){
        assertEquals(expected, ServiceValidation.serviceCheck(service));
    }


    @ParameterizedTest
    @CsvSource({
            "70506,true",
            "70592,true"
    })
    @DisplayName("Services, serviceable area test")
    public void isServiceableAreaTest(Integer zipCode, boolean expected){
        assertEquals(expected, ServiceValidation.isServiceableArea(zipCode));
    }
}
