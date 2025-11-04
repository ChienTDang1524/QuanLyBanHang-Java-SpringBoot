package org.example.test01.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List; // Cần thiết cho List<ProductBrand>

@Entity // Entity JPA
@Table(name = "product") // Bảng "product"
@Data // Lombok annotations
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    @Column(name = "produce_name", length = 100) // Cột "produce_name"
    private String produceName;

    @Column(name = "color", length = 50) // Cột "color"
    private String color;

    @Column(name = "quantity") // Cột "quantity" - kiểu bigint
    private Long quantity;

    @Column(name = "sell_price") // Cột "sell_price" - kiểu double
    private Double sellPrice;

    @Column(name = "origin_price") // Cột "origin_price" - kiểu double
    private Double originPrice;

    @Column(name = "description", length = 1000) // Cột "description", tối đa 1000 ký tự
    private String description;

    // --- Mối quan hệ Many-to-One với SubCategory ---
    @ManyToOne(fetch = FetchType.LAZY) // LAZY: chỉ load SubCategory khi cần
    @JoinColumn(name = "subcate_id", nullable = false) // Foreign key "subcate_id" tham chiếu tới bảng sub_category
    private SubCategory subCategory;

    // --- Mối quan hệ Many-to-One với Status ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false) // Foreign key "status_id" tham chiếu tới bảng status
    private Status status;

    @ManyToMany
    @JoinTable(name = "product_brand" ,joinColumns = @JoinColumn(name = "product_id") , inverseJoinColumns = @JoinColumn(name = "brand_id"))
    private List<Brand> brands ;

}