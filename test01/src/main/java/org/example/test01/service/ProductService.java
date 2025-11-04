package org.example.test01.service;

import org.example.test01.entity.Brand;
import org.example.test01.entity.Category;
import org.example.test01.entity.Status;
import org.example.test01.model.response.ProductResponseDTO;
import java.util.List;

public interface ProductService {

    List<Brand> getAllBrands(); // Thay đổi từ List<String>
    List<Category> getAllCategories(); // Thay đổi từ List<String>
    List<Status> getAllStatuses(); // Thay đổi từ List<String>
    List<ProductResponseDTO> getAllProducts();
    void deleteProduct(Long id);
    List<ProductResponseDTO> searchProducts(String name, Long brandId, Long categoryId, Long statusId, Double price);
}