package com.fetch.SpringBoootProject.service;

import com.example.receiptprocessor.model.Receipt;
import com.fetch.SpringBoootProject.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public String saveReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receiptRepository.save(id, receipt);
        return id;
    }

    public int calculatePoints(String id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receipt not found"));

        int points = 0;

        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // 50 points if the total is a round dollar amount with no cents.
        if (receipt.getTotal().matches("^\\d+\\.00$")) {
            points += 50;
        }

        // 25 points if the total is a multiple of 0.25.
        double total = Double.parseDouble(receipt.getTotal());
        if (total % 0.25 == 0) {
            points += 25;
        }

        // 5 points for every two items on the receipt.
        points += (receipt.getItems().size() / 2) * 5;

        // If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer.
        for (var item : receipt.getItems()) {
            String desc = item.getShortDescription().trim();
            if (desc.length() % 3 == 0) {
                double itemPrice = Double.parseDouble(item.getPrice());
                points += Math.ceil(itemPrice * 0.2);
            }
        }

        // 6 points if the day in the purchase date is odd.
        int purchaseDay = Integer.parseInt(receipt.getPurchaseDate().toString().split("-")[2]);
        if (purchaseDay % 2 != 0) {
            points += 6;
        }

        // 10 points if the time of purchase is after 2:00pm and before 4:00pm.
        String[] timeParts = receipt.getPurchaseTime().split(":");
        int hours = Integer.parseInt(timeParts[0]);
        if (hours >= 14 && hours < 16) {
            points += 10;
        }

        return points;

    }
}

