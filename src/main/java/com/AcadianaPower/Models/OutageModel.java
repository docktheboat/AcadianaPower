package com.AcadianaPower.Models;

import com.AcadianaPower.Keys.OutageCompositeKey;
import com.AcadianaPower.Validation.ServiceValidation;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "outages")
@IdClass(OutageCompositeKey.class)
public class OutageModel{

    @Id
    @NotEmpty(message = ServiceValidation.serviceError)
    private String outageType;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime recoveryTime;
    @Id
    @NotNull(message = ServiceValidation.zipError)
    private Integer zipCode;
    @Transient
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");


    public OutageModel() {
    }

    public OutageModel(String outageType, Integer zipCode) {
        setOutageType(outageType);
        setZipCode(zipCode);
    }


    public String getOutageType() {
        return outageType;
    }

    public void setOutageType(String outageType) {
        if(ServiceValidation.serviceCheck(outageType)) {
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
        int recovery = (int)ChronoUnit.MINUTES.between(LocalDateTime.now(),recoveryTime);
        int minutes = recovery % 60;
        int hours = (recovery - minutes) / 60;
        if((hours + minutes) <= 0) {
            return "0";
        }
        return hours + " hours and " + minutes + " minutes";
    }

    public void setRecoveryTime(LocalDateTime recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        if(ServiceValidation.isServiceableArea(zipCode)) {
            this.zipCode = zipCode;
        }
    }

    @Override
    public String toString() {
        return "OutageModel{" +
                ", outageType=" + outageType +
                ", creationTime='" + createdAt + '\'' +
                ", recoveryTime='" + recoveryTime + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }

}