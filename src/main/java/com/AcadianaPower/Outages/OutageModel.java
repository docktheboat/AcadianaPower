package com.AcadianaPower.Outages;

import com.AcadianaPower.Services.Services;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "outages")
public class OutageModel {

    @Id
    @SequenceGenerator(
            name = "outages_id_seq",
            sequenceName = "outages_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "outages_id_seq"

    )
    private Long id;
    private String outageType;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime recoveryTime;
    private Integer zipCode;
    @Transient
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");


    public OutageModel() {
    }

    public OutageModel(String outageType, Integer zipCode) {
        setOutageType(outageType);
        this.zipCode = zipCode;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutageType() {
        return outageType;
    }

    public void setOutageType(String outageType) {
        if(Services.serviceCheck(outageType)) {
            this.outageType = outageType;
        }
    }


    public String getCreationTime() {
        return dtf.format(createdAt);
    }


    public void setCreationTime(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public String getRecoveryTime() {
        return dtf.format(recoveryTime);
    }

    public void setRecoveryTime(LocalDateTime recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "OutageModel{" +
                "id=" + id +
                ", outageType=" + outageType +
                ", creationTime='" + createdAt + '\'' +
                ", recoveryTime='" + recoveryTime + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }

}
