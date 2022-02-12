package com.AcadianaPower.Services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SmsService {

    @Value("${sms.sid}")
    private String ACCOUNT_SID;

    @Value("${sms.auth.token}")
    private String AUTH_TOKEN;

    @Value("${company.phone}")
    private String companyPhone;


    public void smsNotifyOutage(String customerPhone, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new com.twilio.type.PhoneNumber(
                        "+1"+customerPhone.replaceAll("-","")),
                new PhoneNumber(companyPhone),
                message).create();
    }
}


