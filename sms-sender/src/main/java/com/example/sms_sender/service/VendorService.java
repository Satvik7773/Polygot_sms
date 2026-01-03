package com.example.sms_sender.service;

import org.springframework.stereotype.Service;

@Service
public class VendorService {
    public String sendSms() {
        return Math.random() > 0.2 ? "SUCCESS" : "FAIL";
    }
}
