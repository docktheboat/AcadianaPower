package com.AcadianaPower.Customer;


import com.AcadianaPower.Models.CustomerModel;
import com.AcadianaPower.Models.OutageModel;
import com.AcadianaPower.Repositories.CustomerRepository;
import com.AcadianaPower.Repositories.OutageRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository testCustomerRepository;

    @Autowired
    private OutageRepository testOutageRepository;


    @BeforeEach
    void init(){
        testCustomerRepository.save(
        new CustomerModel(
                "Mandy",
                "Walsh",
                "707 Memory Lane",
                70503,
                LocalDate.of(1982,Month.AUGUST,22),
                "777-777-7777",
                "GAS",
                "testEmail@test.com"
        ));

        testOutageRepository.save(
                new OutageModel("GAS",70503)
        );
    }

    @AfterEach
    void clean(){
        testCustomerRepository.deleteAll();
        testOutageRepository.deleteAll();
    }


    @Test
    @DisplayName("Test delete customer")
    void deleteCustomerByEmail() {
        testCustomerRepository.deleteCustomerByEmail("testEmail@test.com");
        assertFalse(testCustomerRepository.
                getCustomerByEmail("testEmail@test.com").isPresent());

    }

    @ParameterizedTest
    @CsvSource({
            "70503,false",
            "70592,true"
    })
    @DisplayName("Test customers by zip code")
    void getCustomersByZipCode(Integer zipCode, boolean expected) {
        testCustomerRepository.getCustomersByZipCode(zipCode).ifPresent(
                list -> assertEquals(expected,list.isEmpty())
        );
    }

    @ParameterizedTest
    @CsvSource({
            "person@email.com,false",
            "testEmail@test.com,true"
    })
    @DisplayName("Test customers by email")
    void getCustomerByEmail(String email, boolean expected) {
        assertEquals(expected,testCustomerRepository.getCustomerByEmail(email).isPresent());
    }


    @Test
    @DisplayName("Test customers affected by all current outages")
    void customersAffectedAllOutages(){
        testCustomerRepository.customersAffectedAllOutages().ifPresent(
                list -> assertFalse(list.isEmpty())
        );
    }

    @ParameterizedTest
    @CsvSource({
            "70506,ELECTRIC,true",
            "70503,GAS,false"
    })
    @DisplayName("Test customers affected by new outage")
    void customersAffectedByNewOutage(Integer zipCode, String outage, boolean expected) {
        testCustomerRepository.
            customersAffectedByNewOutage(zipCode,outage).ifPresent(
                    list -> assertEquals(expected,list.isEmpty())
                );
    }
}