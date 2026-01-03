package com.example.sms_sender.model;

import lombok.Data;

@Data
public class SendSmsRequest {
    private String userId;
    private String phoneNumber;
    private String message;
}
