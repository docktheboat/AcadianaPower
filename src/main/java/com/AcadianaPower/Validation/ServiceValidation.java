package com.AcadianaPower.Validation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ServiceValidation {

    public static final String emptyField = "Fields must not be empty";
    public static final String nameError = "Names must be in between 2 and 50 characters";
    public static final String phoneError = "Phone number must be 10 digits";
    public static final String zipError = "The given zipcode is not a serviceable area";
    public static final String serviceError = "That service is not offered, please choose from" +
            "electric, gas, or internet";

    public static final List<String> offeredServices = Arrays.asList("ELECTRIC", "INTERNET", "GAS");

    public static final List<Integer> serviceRangeZipCodes = Arrays.asList(
            70506,70508,70501,70502,70503,70507,70583,70518,70517,70592,70520,70563,70560
    );

    public static boolean serviceCheck(String service) {;
        if(Optional.ofNullable(service).isPresent()){
            return offeredServices.contains(service.toUpperCase().trim());
        }
        return false;
    }

    public static boolean isServiceableArea(Integer zipCode) {
        if (Optional.ofNullable(zipCode).isPresent()) {
            return serviceRangeZipCodes.contains(zipCode);
        }
        return false;
    }

    public static String outageMessage(String outageType, String recoveryTime){
        return
                "Hello from Acadiana Power.\n" +
                "This is a message to notify you that you may experience " +
                "an outage in your " + outageType.toLowerCase() +
                " services.\nWe are sorry for the inconvenience.\n" +
                        "The expected recovery time is " + recoveryTime +
                        ".\nWe thank you for your patience.";

    }

}