package com.fetch.SpringBoootProject.controllers;


import com.example.receiptprocessor.model.Receipt;
import com.example.receiptprocessor.model.ReceiptsIdPointsGet200Response;
import com.example.receiptprocessor.model.ReceiptsProcessPost200Response;
import com.fetch.SpringBoootProject.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<ReceiptsProcessPost200Response> processReceipt(@RequestBody Receipt receipt) {
        String receiptId = receiptService.saveReceipt(receipt);
        return ResponseEntity.ok(new ReceiptsProcessPost200Response().id(receiptId));
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<ReceiptsIdPointsGet200Response> getPoints(@PathVariable String id) {
        long points = receiptService.calculatePoints(id);
        return ResponseEntity.ok(new ReceiptsIdPointsGet200Response().points(points));
    }
}

