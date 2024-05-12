package ru.iot.client.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class Notification extends Identifiable{

    @Transient
    private ObjectMapper objectMapper = new ObjectMapper();

    @Column(name = "client_id", nullable = false)
    @Getter
    @Setter
    private UUID clientId;

    @Column(name = "products")
    private String products;

    @Column(name = "created_at")
    @Getter
    @Setter
    private LocalDateTime createdAt;

    public List<String> getProducts() {
        try {
            return objectMapper.readValue(this.products, new TypeReference<List<String>>() {
            });
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public void setProducts(List<String> productList) {
        try {
            this.products = objectMapper.writeValueAsString(productList);
        } catch (JsonProcessingException ignored) {
        }
    }
}
