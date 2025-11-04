package org.example.test01.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List; // Cần thiết cho List<Product>

@Entity
@Table(name = "sub_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_cate_code", unique = true, length = 20)
    private String subCateCode;

    @Column(name = "sub_cate_name", length = 50)
    private String subCateName;

    // --- Mối quan hệ Many-to-One với Category ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_id", nullable = false) // Khóa ngoại tới bảng category
    private Category category;

    // --- Mối quan hệ One-to-Many với Product ---
    // Một danh mục con có thể có nhiều sản phẩm
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}