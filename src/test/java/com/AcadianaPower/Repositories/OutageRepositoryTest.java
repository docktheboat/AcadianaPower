package com.AcadianaPower.Repositories;

import com.AcadianaPower.Models.OutageModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
        assertFalse(testOutageRepository.
                getSpecificOutage(70506,"ELECTRIC").isPresent());
    }

    @Test
    @DisplayName("Test outages by recovery")
    void outagesByRecovery() {
        OutageModel sndOutage = new OutageModel("INTERNET",70503);
        testOutageRepository.save(sndOutage);
        testOutageRepository.outagesByRecovery().flatMap(
                list -> list.stream().findFirst()).ifPresent(firstOutage ->
                assertAll(
                        () -> assertEquals(firstOutage.getOutageType(),"ELECTRIC"),
                        () -> assertEquals(firstOutage.getZipCode(), 70506)
                )
        );
    }

    @Test
    @DisplayName("Test get specific outage")
    void getSpecificOutage(){
        testOutageRepository.getSpecificOutage(70506,"ELECTRIC")
                .ifPresent( o -> assertAll(
                        () -> assertEquals("ELECTRIC",o.getOutageType()),
                        () -> assertEquals(70506,o.getZipCode())
                        )
                );
    }

    @Test
    @DisplayName("Test get outages by their creation date")
    void getOutageByCreation(){
        OutageModel sndOutage = new OutageModel("INTERNET",70503);
        testOutageRepository.save(sndOutage);
        testOutageRepository.outagesByCreation().flatMap(
                list -> list.stream().findFirst()).ifPresent(firstOutage ->
                assertAll(
                        () -> assertEquals(firstOutage.getOutageType(),"INTERNET"),
                        () -> assertEquals(firstOutage.getZipCode(), 70503)
                )
        );
    }
}