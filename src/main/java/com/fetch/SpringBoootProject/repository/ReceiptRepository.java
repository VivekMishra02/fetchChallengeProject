package com.fetch.SpringBoootProject.repository;

import com.example.receiptprocessor.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ReceiptRepository {

    private final Map<String, Receipt> receiptStorage = new HashMap<>();

    public void save(String id, Receipt receipt) {
        receiptStorage.put(id, receipt);
    }

    public Optional<Receipt> findById(String id) {
        return Optional.ofNullable(receiptStorage.get(id));
    }
}
