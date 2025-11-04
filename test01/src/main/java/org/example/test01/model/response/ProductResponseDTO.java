package org.example.test01.model.response;

import java.util.List;

public class ProductResponseDTO {
    private Long id;
    private String produceName;
    private String color;
    private Long quantity;
    private Double sellPrice;
    private Double originPrice;
    private String description;
    private String brandName;

    private Long statusId;
    private Long brandId;

    // SubCategory info
    private Long subCategoryId;
    private String subCategoryName;
    private String subCategoryCode;

    // Category info
    private Long categoryId;
    private String categoryName;
    private String categoryCode;

    // Status info

    private String statusName;

    // Brands info
    private List<BrandInfo> brands;

    // Constructors
    public ProductResponseDTO() {
    }

    public ProductResponseDTO(String brandName) {
        this.brandName = brandName;
    }
    // Nested DTO for brand information
    public static class BrandInfo {
        private Long id;
        private String brandName;
        private String logoUrl;



        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }
    }
    // Getters and Setters
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
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
        this.quantity = quantity;
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
        this.description = description;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<BrandInfo> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandInfo> brands) {
        this.brands = brands;
    }
}