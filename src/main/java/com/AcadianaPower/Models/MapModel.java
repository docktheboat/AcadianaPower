package com.AcadianaPower.Models;


import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "census_tract")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MapModel {

    @Id
    @NotNull
    @Column(name = "geo_id" ,nullable = false)
    private Long geoID;
    @NotNull
    @Column(name = "region_name" ,nullable = false)
    private String regionName;
    @NotNull
    @Column(name = "region_code" ,nullable = false)
    private Integer regionCode;
    @NotNull
    @Column(name = "net_status" ,nullable = false)
    private String netStatus;
    @NotNull
    @Column(name = "elec_status" ,nullable = false)
    private String elecStatus;
    @NotNull
    @Column(name = "zip_code" ,nullable = false)
    private Integer zipCode;

}
