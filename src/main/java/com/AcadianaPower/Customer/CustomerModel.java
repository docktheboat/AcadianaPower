package com.AcadianaPower.Customer;


import com.AcadianaPower.Services.Services;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class CustomerModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID accountNumber;
    private String firstName;
    private String lastName;
    private String address;
    private Integer zipCode;
    private LocalDate dob;
    private String servicesUsed;
    private String phoneNumber;
    private String email;

    public CustomerModel(){}

    public CustomerModel(String firstName, String lastName, String address, Integer zipCode,
                         LocalDate dob, String phoneNumber,String servicesUsed, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.servicesUsed = servicesUsed;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZipCode(){
        return zipCode;
    }

    public void setZipCode(Integer zipCode){
        this.zipCode = zipCode;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getServicesUsed() {
        return servicesUsed;
    }

    public void setService(String service) {
        if(Services.serviceCheck(service)) {
                this.servicesUsed = service;
            }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(UUID id) {
        this.accountNumber = id;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "accountNumber=" + accountNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipCode + '\'' +
                ", dob=" + dob +
                ", servicesUsed=" + servicesUsed + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
