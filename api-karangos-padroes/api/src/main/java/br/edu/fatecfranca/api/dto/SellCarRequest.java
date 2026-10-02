package br.edu.fatecfranca.api.dto;

import java.math.BigDecimal;

public class SellCarRequest {

    private Long customerId;
    private BigDecimal price;

    public SellCarRequest() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
