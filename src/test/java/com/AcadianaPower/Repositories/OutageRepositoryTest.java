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

    private OutageModel fstOutage;
    private OutageModel sndOutage;

    @BeforeEach
    void init() throws InterruptedException {
        fstOutage = new OutageModel("ELECTRIC",70506);
        sndOutage = new OutageModel("INTERNET",70503);
        testOutageRepository.save(fstOutage);
        testOutageRepository.save(sndOutage);
    }

    @AfterEach
    void clean(){
        testOutageRepository.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({
            "70506,false",
            "70503,false",
            "70592,true"
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
        testOutageRepository.deleteOutage(
                fstOutage.getZipCode(),
                fstOutage.getOutageType());
        assertFalse(testOutageRepository.
                getSpecificOutage(
                        fstOutage.getZipCode(),
                        fstOutage.getOutageType()).isPresent());
    }

    @Test
    @DisplayName("Test outages by recovery")
    void outagesByRecovery() {
        testOutageRepository.outagesByRecovery().flatMap(
                list -> list.stream().findFirst()).ifPresent(firstOutage ->
                assertAll(
                        () -> assertEquals(
                                firstOutage.getOutageType(),
                                fstOutage.getOutageType()),
                        () -> assertEquals(
                                firstOutage.getZipCode(),
                                fstOutage.getZipCode())
                )
        );
    }

    @Test
    @DisplayName("Test get specific outage")
    void getSpecificOutage(){
        testOutageRepository.getSpecificOutage(
                        fstOutage.getZipCode(),
                        fstOutage.getOutageType())
                .ifPresent( o -> assertAll(
                        () -> assertEquals(
                                fstOutage.getOutageType()
                                ,o.getOutageType()),
                        () -> assertEquals(
                                fstOutage.getZipCode(),
                                o.getZipCode())
                        )
                );
    }

    @Test
    @DisplayName("Test get outages by their creation date")
    void getOutageByCreation(){
        testOutageRepository.outagesByCreation().flatMap(
                list -> list.stream().findFirst()).ifPresent(firstOutage ->
                assertAll(
                        () -> assertEquals(
                                firstOutage.getOutageType(),
                                sndOutage.getOutageType()),
                        () -> assertEquals(firstOutage.getZipCode(),
                                sndOutage.getZipCode())
                )
        );
    }
}