package com.hexpanda.controller;

import com.hexpanda.common.domain.product.dto.ProductDTO;
import com.hexpanda.common.domain.product.dto.ProductCreateFormDTO;
import com.hexpanda.common.domain.product.dto.ProductUpdateFormDTO;
import com.hexpanda.core.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leo Wang
 * @since 2025/5/27
 */
@Tag(name = "商品管理", description = "商品相关接口")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "获取所有商品", description = "查询所有商品信息")
    @GetMapping("/products")
    public List<ProductDTO> getAllProduct() {
        return productService.findAll();
    }

    @Operation(summary = "根据ID获取商品", description = "根据商品ID查询商品信息")
    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@Parameter(description = "商品ID", required = true)
                                         @PathVariable("id")
                                         Long id) {
        return productService.findById(id);
    }

    @Operation(summary = "创建商品", description = "新增一个商品")
    @PostMapping("/products")
    public ProductDTO createProduct(@RequestBody ProductCreateFormDTO formDTO) {
        return productService.createNewProduct(formDTO);
    }

    @Operation(summary = "更新商品", description = "根据ID更新商品信息")
    @PutMapping("/products/{id}")
    public ProductDTO updateProduct(@Parameter(description = "商品ID", required = true)
                                        @PathVariable("id") Long id,
                                    @RequestBody ProductUpdateFormDTO formDTO) {
        formDTO.setId(id);
        return productService.updateProduct(formDTO);
    }
}
