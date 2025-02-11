package com.receiptrewards.service;

import com.receiptrewards.model.Receipt;
import com.receiptrewards.model.Item;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptService {
    private final Map<String, Receipt> receiptStorage = new HashMap<>();

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receiptStorage.put(id, receipt);
        return id;
    }

    public int calculatePoints(Receipt receipt) {
        int points = 0;
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
        if (receipt.getTotal() % 1 == 0) {
            points += 50;
        }

        if (receipt.getTotal() % 0.25 == 0) {
            points += 25;
        }
        points += (receipt.getItems().size() / 2) * 5;

        for (Item item : receipt.getItems()) {
            String desc = item.getShortDescription().trim();
            if (desc.length() % 3 == 0) {
                points += Math.ceil(item.getPrice() * 0.2);
            }
        }

        int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (day % 2 != 0) {
            points += 6;
        }

        LocalTime time = LocalTime.parse(receipt.getPurchaseTime());
        if (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(16, 0))) {
            points += 10;
        }

        if(receipt.getTotal() > 10.00){
            points += 5;
        }

        return points;
    }
    public Integer getPoints(String id){
        Receipt receipt = receiptStorage.get(id);
        return (receipt != null) ? calculatePoints(receipt) : null;
    }
}

