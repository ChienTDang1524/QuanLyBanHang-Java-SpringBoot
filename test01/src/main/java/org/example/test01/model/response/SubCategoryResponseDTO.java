package org.example.test01.model.response;

public class SubCategoryResponseDTO {
    private Long id;
    private String subCateName;
    private String subCateCode;
    private Long categoryId;
    private String categoryName;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSubCateName() { return subCateName; }
    public void setSubCateName(String subCateName) { this.subCateName = subCateName; }
    public String getSubCateCode() { return subCateCode; }
    public void setSubCateCode(String subCateCode) { this.subCateCode = subCateCode; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}