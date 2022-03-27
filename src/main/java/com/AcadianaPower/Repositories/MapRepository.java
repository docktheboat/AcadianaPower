package com.AcadianaPower.Repositories;


import com.AcadianaPower.Models.MapModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MapRepository extends JpaRepository<MapModel, Long> {

    @Query(value = "SELECT * FROM census_tract WHERE zip_code = ?1;")
    Optional<List<MapModel>> tractByZip(Integer zipCode);
}
