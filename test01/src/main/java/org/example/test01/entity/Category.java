package org.example.test01.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List; // Cần thiết cho List<SubCategory>

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cate_code", unique = true, length = 20)
    private String cateCode;

    @Column(name = "cate_name", length = 50)
    private String cateName;

    // --- Mối quan hệ One-to-Many với SubCategory ---
    // Một danh mục có thể có nhiều danh mục con

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Thêm annotation này
    private List<SubCategory> subCategories;
}