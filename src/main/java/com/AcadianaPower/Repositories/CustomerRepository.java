package com.AcadianaPower.Repositories;

import com.AcadianaPower.Models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    @Modifying
    @Query(value = "DELETE FROM customers WHERE customers.email = ?1", nativeQuery = true )
    void deleteCustomerByEmail(String email);

    @Query(value = "SELECT * FROM customers WHERE zip_code = ?1", nativeQuery = true )
    Optional<List<CustomerModel>> getCustomersByZipCode(Integer zipCode);

    @Query(value = "SELECT * FROM customers WHERE email = ?1", nativeQuery = true )
    Optional<CustomerModel> getCustomerByEmail(String email);

    @Query(value = "(SELECT last_name, email, phone_number, address, services_used FROM customers" +
            " JOIN outages ON customers.zip_code = outages.zip_code AND" +
            " customers.services_used = outages.outage_type);", nativeQuery = true)
    Optional<List<String>> customersAffectedAllOutages();

    @Query(value =
            "((SELECT email, phone_number FROM customers WHERE zip_code = ?1) " +
                    "INTERSECT " +
                    "(SELECT email, phone_number FROM customers WHERE services_used = ?2));"
            , nativeQuery = true)
    Optional<List<String>> customersAffectedByNewOutage(Integer zipCode, String outageType);
}
