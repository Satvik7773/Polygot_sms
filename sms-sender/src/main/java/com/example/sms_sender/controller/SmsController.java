package com.example.sms_sender.controller;

import com.example.sms_sender.model.SendSmsRequest;
import com.example.sms_sender.model.SmsEvent;
import com.example.sms_sender.service.BlockService;
import com.example.sms_sender.service.KafkaProducerService;
import com.example.sms_sender.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sms")
public class SmsController {

    private final BlockService blockService;
    private final VendorService vendorService;
    private final KafkaProducerService kafkaService;

    public SmsController(BlockService blockService,
                         VendorService vendorService,
                         KafkaProducerService kafkaService) {
        this.blockService = blockService;
        this.vendorService = vendorService;
        this.kafkaService = kafkaService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody SendSmsRequest req) throws Exception {

        if (blockService.isBlocked(req.getUserId())) {
            return ResponseEntity.status(403).body("User blocked");
        }

        String status = vendorService.sendSms();

        SmsEvent event = new SmsEvent(
                req.getUserId(),
                req.getPhoneNumber(),
                req.getMessage(),
                status
        );

        kafkaService.publish(event);
        return ResponseEntity.ok(event);
    }
}
