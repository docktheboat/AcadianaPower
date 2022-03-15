package com.AcadianaPower.Repositories;

import com.AcadianaPower.Models.OutageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OutageRepository extends JpaRepository<OutageModel, Long> {

    @Query(value = "SELECT * " +
            "FROM outages " +
            "WHERE zip_code = ?1", nativeQuery = true)
    Optional<List<OutageModel>> getOutagesByZip(Integer zipCode);

    @Modifying
    @Query(value = "DELETE " +
            "FROM outages " +
            "WHERE zip_code = ?1 " +
            "AND outage_type = ?2", nativeQuery = true)
    void deleteOutage(Integer zipCode, String type);

    @Query(value = "SELECT * " +
            "FROM outages " +
            "ORDER BY recovery_time;", nativeQuery = true)
    Optional<List<OutageModel>> outagesByRecovery();

    @Query(value = "SELECT * " +
            "FROM outages " +
            "WHERE zip_code = ?1 " +
            "AND outage_type = ?2", nativeQuery = true)
    Optional<OutageModel> getSpecificOutage(Integer zipCode, String type);

   /* @Query(value = "SELECT * " +
            "FROM outages " +
            "ORDER BY created_at;", nativeQuery = true)
    Optional<List<OutageModel>> outagesByCreation();*/

}
