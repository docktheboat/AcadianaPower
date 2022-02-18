package com.AcadianaPower.Outages;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OutageRepositoryTest {

    @Autowired
    private OutageRepository testOutageRepository;

    @BeforeEach
    void init(){
        testOutageRepository.save(new OutageModel(
                "ELECTRIC",70506
        ));
    }

    @AfterEach
    void clean(){
        testOutageRepository.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({
            "70506,false",
            "70503,true"
    })
    @DisplayName("Test outages by zip code")
    void getOutagesByZip(Integer zipCode, boolean expected) {
        testOutageRepository.getOutagesByZip(zipCode).ifPresent(
                list -> assertEquals(expected,list.isEmpty())
        );

    }

    @Test
    @DisplayName("Test delete outage")
    void deleteOutage() {
        testOutageRepository.deleteOutage(70506,"ELECTRIC");
        testOutageRepository.getOutagesByZip(70506).ifPresent(
                list -> assertTrue(list.isEmpty())
        );

    }

    @Test
    @DisplayName("Test outages by recovery")
    void outagesByRecovery() {
        OutageModel sndOutage = new OutageModel("INTERNET",70503);
        testOutageRepository.save(sndOutage);

        testOutageRepository.outagesByRecovery().ifPresent(
                list -> assertEquals("ELECTRIC", list.get(0).getOutageType())
        );
    }
}