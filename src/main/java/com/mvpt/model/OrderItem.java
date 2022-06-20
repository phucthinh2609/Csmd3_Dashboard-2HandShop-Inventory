package com.mvpt.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItem {
    private Long id;
    private Long productId;
    private Long itemId;
    private Long orderId;
    private BigDecimal price;
    private int quantity;
    private Date createdAt;
    private Date updatedAt;
    private String content;

    private Product product;
    private Item item;
    private Order order;


    public OrderItem(Long id, Long productId, Long itemId, Long orderId, BigDecimal price, int quantity, String content) {
        this.id = id;
        this.productId = productId;
        this.itemId = itemId;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.content = content;
    }

    public OrderItem() {

    }

    public OrderItem(long id, Long productId, Long itemId, Long orderId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.itemId = itemId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,",
                id,
                productId,
                itemId,
                orderId,
                price,
                quantity,
                content,
                createdAt,
                updatedAt
                );

    }
}
