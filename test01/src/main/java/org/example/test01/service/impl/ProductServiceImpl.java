package org.example.test01.service.impl;

import jakarta.persistence.criteria.Join;
import org.example.test01.entity.*;
import org.example.test01.exception.ResourceNotFoundException;
import org.example.test01.model.response.ProductResponseDTO;
import org.example.test01.repository.*;
import org.example.test01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        productRepository.delete(product);
    }

    @Override
    public List<ProductResponseDTO> searchProducts(String name, Long brandId, Long categoryId, Long statusId, Double price) {
        Specification<Product> spec = (root, query, cb) -> cb.conjunction();

        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("produceName")), "%" + name.toLowerCase() + "%"));
        }

        if (brandId != null) {
            spec = spec.and((root, query, cb) -> {
                Join<Product, Brand> brandJoin = root.join("brands");
                return cb.equal(brandJoin.get("id"), brandId);
            });
        }

        if (categoryId != null) {
            spec = spec.and((root, query, cb) -> {
                Join<Product, SubCategory> subCategoryJoin = root.join("subCategory");
                Join<SubCategory, Category> categoryJoin = subCategoryJoin.join("category");
                return cb.equal(categoryJoin.get("id"), categoryId);
            });
        }

        if (statusId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("status").get("id"), statusId));
        }

        if (price != null) {
            // Thay đổi từ tìm kiếm chính xác sang tìm kiếm giá gần nhất (trong khoảng ±10%)
            double lowerBound = price * 0.9;
            double upperBound = price * 1.1;
            spec = spec.and((root, query, cb) ->
                    cb.between(root.get("sellPrice"), lowerBound, upperBound));
        }

        return productRepository.findAll(spec).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductResponseDTO convertToDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setProduceName(product.getProduceName());
        dto.setColor(product.getColor());
        dto.setQuantity(product.getQuantity());
        dto.setSellPrice(product.getSellPrice());
        dto.setOriginPrice(product.getOriginPrice());
        dto.setDescription(product.getDescription());

        if (product.getBrands() != null && !product.getBrands().isEmpty()) {
            dto.setBrandName(product.getBrands().get(0).getBrandName());
            dto.setBrandId(product.getBrands().get(0).getId());
            List<ProductResponseDTO.BrandInfo> brandInfos = product.getBrands().stream()
                    .map(brand -> {
                        ProductResponseDTO.BrandInfo brandInfo = new ProductResponseDTO.BrandInfo();
                        brandInfo.setId(brand.getId());
                        brandInfo.setBrandName(brand.getBrandName());
                        brandInfo.setLogoUrl(brand.getLogoUrl());
                        return brandInfo;
                    })
                    .collect(Collectors.toList());
            dto.setBrands(brandInfos);
        }

        if (product.getSubCategory() != null) {
            dto.setSubCategoryId(product.getSubCategory().getId());
            dto.setSubCategoryName(product.getSubCategory().getSubCateName());
            dto.setSubCategoryCode(product.getSubCategory().getSubCateCode());

            if (product.getSubCategory().getCategory() != null) {
                dto.setCategoryId(product.getSubCategory().getCategory().getId());
                dto.setCategoryName(product.getSubCategory().getCategory().getCateName());
                dto.setCategoryCode(product.getSubCategory().getCategory().getCateCode());
            }
        }

        if (product.getStatus() != null) {
            dto.setStatusId(product.getStatus().getId());
            dto.setStatusName(product.getStatus().getStatusName());
        }

        return dto;
    }
}