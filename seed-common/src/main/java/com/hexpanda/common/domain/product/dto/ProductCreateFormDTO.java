package com.hexpanda.common.domain.product.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Leo Wang
 * @since 2025/5/27
 */
@Data
public class ProductCreateFormDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Long stock;
}
