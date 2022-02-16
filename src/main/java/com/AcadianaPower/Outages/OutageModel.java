package com.AcadianaPower.Outages;

import com.AcadianaPower.Services.Services;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "outages")
@IdClass(OutageCompositeKey.class)
public class OutageModel{

    @Id
    private String outageType;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime recoveryTime;
    @Id
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
        if(Services.isServiceableArea(zipCode)) {
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
