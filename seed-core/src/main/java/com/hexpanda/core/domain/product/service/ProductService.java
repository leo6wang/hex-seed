package com.hexpanda.core.domain.product.service;

import com.hexpanda.common.domain.product.dto.ProductCreateFormDTO;
import com.hexpanda.common.domain.product.dto.ProductDTO;
import com.hexpanda.common.domain.product.dto.ProductUpdateFormDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Product服务
 * @author Leo Wang
 * @since 2025/5/27
 */
@Service
public class ProductService {
    private final List<ProductDTO> mockProductDTOList = new ArrayList<>();

    @PostConstruct
    public void init() {
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId(1L);
        productDTO1.setName("iPhone16");
        productDTO1.setDescription("iPhone16");
        productDTO1.setPrice(BigDecimal.valueOf(2000));
        productDTO1.setStock(10L);
        mockProductDTOList.add(productDTO1);

        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setId(2L);
        productDTO2.setName("AirPods Pro");
        productDTO2.setDescription("AirPods Pro");
        productDTO2.setPrice(BigDecimal.valueOf(200));
        productDTO2.setStock(20L);
        mockProductDTOList.add(productDTO2);

        ProductDTO productDTO3 = new ProductDTO();
        productDTO3.setId(3L);
        productDTO3.setName("Xiaomi 6S");
        productDTO3.setDescription("Xiaomi 6S");
        productDTO3.setPrice(BigDecimal.valueOf(1000));
        productDTO3.setStock(3L);
        mockProductDTOList.add(productDTO3);
    }

    public List<ProductDTO> findAll() {
        return mockProductDTOList;
    }

    public ProductDTO findById(Long id) {
        if (id == null) {
            return null;
        }
        return mockProductDTOList.stream()
                .filter(productDTO -> id.equals(productDTO.getId()))
                .findFirst()
                .orElse(null);
    }

    public ProductDTO createNewProduct(ProductCreateFormDTO formDTO) {
        if (formDTO == null) {
            return null;
        }
        ProductDTO productDTO = convertToProductDTO(formDTO);
        mockProductDTOList.add(productDTO);
        return productDTO;
    }

    public ProductDTO updateProduct(ProductUpdateFormDTO formDTO) {
        if (formDTO == null) {
            return null;
        }
        ProductDTO productDTO = findById(formDTO.getId());
        if (productDTO == null) {
            return null;
        }
        if (formDTO.getName() != null) {
            productDTO.setName(formDTO.getName());
        }
        if (formDTO.getDescription() != null) {
            productDTO.setDescription(formDTO.getDescription());
        }
        if (formDTO.getPrice() != null) {
            productDTO.setPrice(formDTO.getPrice());
        }
        if (formDTO.getStock() != null) {
            productDTO.setStock(formDTO.getStock());
        }
        return productDTO;
    }

    private ProductDTO convertToProductDTO(ProductCreateFormDTO formDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(formDTO.getName());
        productDTO.setDescription(formDTO.getDescription());
        productDTO.setPrice(formDTO.getPrice());
        productDTO.setStock(formDTO.getStock());
        return productDTO;
    }
}
