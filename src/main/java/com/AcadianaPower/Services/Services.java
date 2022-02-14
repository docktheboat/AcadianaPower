package com.AcadianaPower.Services;

import java.util.List;
import java.util.Optional;

public class Services {

    public static final List<String> offeredServices = List.of("ELECTRIC", "INTERNET", "GAS");

    public static final List<Integer> serviceRangeZipCodes = List.of(
            70506,70508,70501,70503,70507,70583,70518,70517,70592,70520,70563,70560
    );

    public static boolean serviceCheck(String service) {

        Optional<String> optional = Optional.ofNullable(service);

        if (optional.isPresent() && optional.get().length() > 0) {
            service = service.replaceAll(" ", "");
            String finalService = service;
            if(offeredServices.stream().anyMatch(str -> str.equalsIgnoreCase(finalService)) ){
                return true;
            }
        }
            return false;
    }

    public static boolean isServiceableArea(Integer zipCode) {
        Optional<Integer> optionalZip = Optional.ofNullable(zipCode);
        if (optionalZip.isPresent()) {
            return String.valueOf(zipCode).length() == 5 && serviceRangeZipCodes.contains(zipCode);
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