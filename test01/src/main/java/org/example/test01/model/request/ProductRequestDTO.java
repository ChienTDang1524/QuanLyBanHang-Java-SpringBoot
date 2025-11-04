package org.example.test01.model.request;

import jakarta.validation.constraints.*;
import java.util.List;

public class ProductRequestDTO {
    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Product name must be less than 100 characters")
    private String productName;

    @Size(max = 50, message = "Color must be less than 50 characters")
    private String color;

    @PositiveOrZero(message = "Quantity must be positive or zero")
    private Long quantity = 0L; // Default value

    @NotNull(message = "Sell price is required")
    @Positive(message = "Sell price must be positive")
    private Double sellPrice;

    @NotNull(message = "Origin price is required")
    @Positive(message = "Origin price must be positive")
    private Double originPrice;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description = ""; // Default value

    @NotNull(message = "Subcategory ID is required")
    private Long subCategoryId;

    @NotNull(message = "Status ID is required")
    private Long statusId;

    private List<@Positive(message = "Brand ID must be positive") Long> brandIds;

    // Default constructor
    public ProductRequestDTO() {
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity != null ? quantity : 0L;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Double originPrice) {
        this.originPrice = originPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public List<Long> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<Long> brandIds) {
        this.brandIds = brandIds;
    }
}