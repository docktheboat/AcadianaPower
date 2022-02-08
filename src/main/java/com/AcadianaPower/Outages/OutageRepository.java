package com.AcadianaPower.Outages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutageRepository extends JpaRepository<OutageModel, Long> {

    //@Query("SELECT outage FROM OutageModel outage WHERE outage.zipCode = ?1" )
   // Optional<List<OutageModel>> getOutagesByZipCode(Integer zipCode);
}
