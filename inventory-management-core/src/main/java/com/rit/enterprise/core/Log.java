package com.rit.enterprise.core;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Log {

    private int id;

    private Integer transactionId;
    private String description;
    private LocalDateTime timestamp;
    private Integer productId;
    private Integer amountChanged;

    public Log() {}

    public Log(int id,
               Integer transactionId,
               String description,
               LocalDateTime timestamp,
               Integer productId,
               Integer amountChanged) {
        this.id = id;
        this.transactionId = transactionId;
        this.description = description;
        this.timestamp = timestamp;
        this.productId = productId;
        this.amountChanged = amountChanged;
    }

    @JsonProperty
    public Integer getAmountChanged() {
        return amountChanged;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public Integer getTransactionId() {
        return transactionId;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @JsonProperty
    public Integer getProductId() {
        return productId;
    }

}
