package com.AcadianaPower.Models;


import com.AcadianaPower.Validation.ServiceValidation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Entity
@Table(name = "customers")
public class CustomerModel {
    @Id
    @SequenceGenerator(
            name = "customers_accountid_seq",
            sequenceName = "customers_accountid_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customers_accountid_seq"
    )
    private Long accountID;

    @NotEmpty(message = ServiceValidation.emptyField)
    @Size(min = 2, max = 50, message = ServiceValidation.nameError)
    private String lastName;

    @NotEmpty(message = ServiceValidation.emptyField)
    private String address;

    @NotNull(message = ServiceValidation.zipError)
    private Integer zipCode;

    @NotEmpty(message = ServiceValidation.serviceError)
    private String servicesUsed;

    @NotEmpty(message = ServiceValidation.emptyField)
    @Size(min = 10, message = ServiceValidation.phoneError)
    private String phoneNumber;


    @NotEmpty(message = ServiceValidation.emptyField)
    @Email(message = "Your email is not valid")
    private String email;

    public CustomerModel(){}

    public CustomerModel(String lastName, String address, Integer zipCode,
                         String phoneNumber,String servicesUsed, String email) {
        this.lastName = lastName;
        this.address = address;
        setZipCode(zipCode);
        this.phoneNumber = phoneNumber;
        setService(servicesUsed);
        this.email = email;
    }

    public void setZipCode(Integer zipCode){
        if(ServiceValidation.isServiceableArea(zipCode)) {
            this.zipCode = zipCode;
        }
    }
    public void setService(String servicesUsed) {
        servicesUsed = servicesUsed.toUpperCase().trim();
        if(ServiceValidation.serviceCheck(servicesUsed)) {
                this.servicesUsed = servicesUsed;
            }
    }

}
