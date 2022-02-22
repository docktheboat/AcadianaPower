package com.AcadianaPower.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Services {

    public static final List<String> offeredServices = Arrays.asList("ELECTRIC", "INTERNET", "GAS");

    public static final List<Integer> serviceRangeZipCodes = Arrays.asList(
            70506,70508,70501,70502,70503,70507,70583,70518,70517,70592,70520,70563,70560
    );

    public static boolean serviceCheck(String service) {;
        if(Optional.ofNullable(service).isPresent()){
            return offeredServices.contains(service.toUpperCase());
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
                "a(n) " + outageType +
                " outage. We are sorry for the inconvenience.\n" +
                        "The expected recovery date/time is " + recoveryTime;

    }

}