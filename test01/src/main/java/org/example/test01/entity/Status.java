package org.example.test01.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List; // Cần thiết cho List<Product>

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", unique = true, length = 100)
    private String statusName;

    // --- Mối quan hệ One-to-Many với Product ---
    // Một trạng thái có thể được gán cho nhiều sản phẩm
    // Thêm @JsonIgnore để ngăn lặp vô hạn
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;
}