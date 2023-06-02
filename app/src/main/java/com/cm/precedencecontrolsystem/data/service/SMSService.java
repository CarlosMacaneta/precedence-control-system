package com.cm.precedencecontrolsystem.data.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SMSService {

    public static String sendSMS() {
        Twilio.init(
                "AC1f94b049ab64e86796161366d685f29c",
                "3fa233f56f94fc101e5a508a52ebbcb7"
        );
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+258842236551"),
                        new com.twilio.type.PhoneNumber("+12057971745"),
                        "A sua incrição foi paga, com sucesso.")
                .create();

        return message.getSid();
    }
}