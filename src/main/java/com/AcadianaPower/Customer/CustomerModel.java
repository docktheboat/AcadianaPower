package com.AcadianaPower.Customer;


import com.AcadianaPower.Services.Services;
import javax.persistence.*;
import java.time.LocalDate;

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
        setZipCode(zipCode);
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        setService(servicesUsed);
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
        if(Services.isServiceableArea(zipCode)) {
            this.zipCode = zipCode;
        }
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

    public Long getAccountNumber() {
        return accountID;
    }

    public void setAccountNumber(Long id) {
        this.accountID = id;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "accountNumber=" + accountID +
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
