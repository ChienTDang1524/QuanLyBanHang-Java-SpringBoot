package org.example.test01.controller;

import org.example.test01.entity.*;
import org.example.test01.exception.ResourceNotFoundException;
import org.example.test01.model.request.ProductRequestDTO;
import org.example.test01.model.response.*;
import org.example.test01.repository.*;
import org.example.test01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        try {
            List<ProductResponseDTO> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long statusId,
            @RequestParam(required = false) Double price) {
        try {
            List<ProductResponseDTO> products = productService.searchProducts(name, brandId, categoryId, statusId, price);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        try {
            List<Brand> brands = brandRepository.findAll();
            return ResponseEntity.ok(brands);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        try {
            List<CategoryResponseDTO> categories = categoryRepository.findAll().stream()
                    .map(this::convertToCategoryDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<StatusReponseDTO>> getAllStatuses() {
        try {
            List<StatusReponseDTO> statuses = statusRepository.findAll().stream()
                    .map(this::convertToStatusDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(statuses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/subcategories")
    public ResponseEntity<List<SubCategoryResponseDTO>> getAllSubCategories() {
        try {
            List<SubCategoryResponseDTO> subCategories = subCategoryRepository.findAll().stream()
                    .map(this::convertToSubCategoryDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(subCategories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDTO productRequest) {
        try {
            // Validate brand IDs
            if (productRequest.getBrandIds() != null && !productRequest.getBrandIds().isEmpty()) {
                List<Brand> brands = brandRepository.findAllById(productRequest.getBrandIds());
                if (brands.size() != productRequest.getBrandIds().size()) {
                    throw new ResourceNotFoundException("One or more brands not found");
                }
            }

            SubCategory subCategory = subCategoryRepository.findById(productRequest.getSubCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("SubCategory not found with id: " + productRequest.getSubCategoryId()));

            Status status = statusRepository.findById(productRequest.getStatusId())
                    .orElseThrow(() -> new ResourceNotFoundException("Status not found with id: " + productRequest.getStatusId()));

            Product product = new Product();
            product.setProduceName(productRequest.getProductName());
            product.setColor(productRequest.getColor());
            product.setQuantity(productRequest.getQuantity());
            product.setSellPrice(productRequest.getSellPrice());
            product.setOriginPrice(productRequest.getOriginPrice());
            product.setDescription(productRequest.getDescription());
            product.setSubCategory(subCategory);
            product.setStatus(status);

            if (productRequest.getBrandIds() != null && !productRequest.getBrandIds().isEmpty()) {
                List<Brand> brands = brandRepository.findAllById(productRequest.getBrandIds());
                product.setBrands(brands);
            }

            Product savedProduct = productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToProductDTO(savedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating product: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO productRequest) {
        try {
            Product existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

            // Validate brand IDs
            if (productRequest.getBrandIds() != null && !productRequest.getBrandIds().isEmpty()) {
                List<Brand> brands = brandRepository.findAllById(productRequest.getBrandIds());
                if (brands.size() != productRequest.getBrandIds().size()) {
                    throw new ResourceNotFoundException("One or more brands not found");
                }
            }

            SubCategory subCategory = subCategoryRepository.findById(productRequest.getSubCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("SubCategory not found with id: " + productRequest.getSubCategoryId()));

            Status status = statusRepository.findById(productRequest.getStatusId())
                    .orElseThrow(() -> new ResourceNotFoundException("Status not found with id: " + productRequest.getStatusId()));

            existingProduct.setProduceName(productRequest.getProductName());
            existingProduct.setColor(productRequest.getColor());
            existingProduct.setQuantity(productRequest.getQuantity());
            existingProduct.setSellPrice(productRequest.getSellPrice());
            existingProduct.setOriginPrice(productRequest.getOriginPrice());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setSubCategory(subCategory);
            existingProduct.setStatus(status);

            if (productRequest.getBrandIds() != null && !productRequest.getBrandIds().isEmpty()) {
                List<Brand> brands = brandRepository.findAllById(productRequest.getBrandIds());
                existingProduct.setBrands(brands);
            }

            Product updatedProduct = productRepository.save(existingProduct);
            return ResponseEntity.ok(convertToProductDTO(updatedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating product: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting product: " + e.getMessage());
        }
    }

    @GetMapping("/categories-for-search")
    public ResponseEntity<List<Category>> getCategoriesForSearch() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/statuses-for-edit")
    public ResponseEntity<List<Status>> getStatusesForEdit() {
        try {
            List<Status> statuses = statusRepository.findAll();
            return ResponseEntity.ok(statuses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Conversion methods remain the same
    private CategoryResponseDTO convertToCategoryDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setCateName(category.getCateName());
        dto.setCateCode(category.getCateCode());
        return dto;
    }

    private StatusReponseDTO convertToStatusDTO(Status status) {
        StatusReponseDTO dto = new StatusReponseDTO();
        dto.setId(status.getId());
        dto.setStatusName(status.getStatusName());
        return dto;
    }

    private SubCategoryResponseDTO convertToSubCategoryDTO(SubCategory subCategory) {
        SubCategoryResponseDTO dto = new SubCategoryResponseDTO();
        dto.setId(subCategory.getId());
        dto.setSubCateName(subCategory.getSubCateName());
        dto.setSubCateCode(subCategory.getSubCateCode());
        if (subCategory.getCategory() != null) {
            dto.setCategoryId(subCategory.getCategory().getId());
            dto.setCategoryName(subCategory.getCategory().getCateName());
        }
        return dto;
    }

    private ProductResponseDTO convertToProductDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setProduceName(product.getProduceName());
        dto.setColor(product.getColor());
        dto.setQuantity(product.getQuantity());
        dto.setSellPrice(product.getSellPrice());
        dto.setOriginPrice(product.getOriginPrice());
        dto.setDescription(product.getDescription());

        // SubCategory info
        if (product.getSubCategory() != null) {
            dto.setSubCategoryId(product.getSubCategory().getId());
            dto.setSubCategoryName(product.getSubCategory().getSubCateName());
            dto.setSubCategoryCode(product.getSubCategory().getSubCateCode());

            // Category info
            if (product.getSubCategory().getCategory() != null) {
                dto.setCategoryId(product.getSubCategory().getCategory().getId());
                dto.setCategoryName(product.getSubCategory().getCategory().getCateName());
                dto.setCategoryCode(product.getSubCategory().getCategory().getCateCode());
            }
        }

        // Status info
        if (product.getStatus() != null) {
            dto.setStatusId(product.getStatus().getId());
            dto.setStatusName(product.getStatus().getStatusName());
        }

        // Brands info
        if (product.getBrands() != null && !product.getBrands().isEmpty()) {
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
            dto.setBrandName(product.getBrands().get(0).getBrandName());
            dto.setBrandId(product.getBrands().get(0).getId());
        }

        return dto;
    }
}