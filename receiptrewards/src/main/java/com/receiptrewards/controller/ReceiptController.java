package com.receiptrewards.controller;

import com.receiptrewards.model.Receipt;
import com.receiptrewards.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService){
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processReceipt(@RequestBody Receipt receipt){
        String id = receiptService.processReceipt(receipt);
        return ResponseEntity.ok("{\"id\": \"" + id + "\"}");
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable String id){
        Integer points = receiptService.getPoints(id);
        if (points == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("{\"points\": " + points + "}");
    }

}