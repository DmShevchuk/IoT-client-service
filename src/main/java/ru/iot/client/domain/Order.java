package ru.iot.client.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order extends Identifiable {

    @Transient
    private ObjectMapper objectMapper = new ObjectMapper();

    @Column(name = "client_id", nullable = false)
    @Getter
    @Setter
    private UUID clientId;

    @Column(name = "products")
    private String products;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private OrderStatus status;

    @Column(name = "created_at")
    @Getter
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

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
