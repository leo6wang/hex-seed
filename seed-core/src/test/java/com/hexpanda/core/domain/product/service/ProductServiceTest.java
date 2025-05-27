package com.hexpanda.core.domain.product.service;

import com.hexpanda.common.domain.product.dto.ProductCreateFormDTO;
import com.hexpanda.common.domain.product.dto.ProductUpdateFormDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Leo Wang
 * @since 2025/5/28
 */
class ProductServiceTest {

    /**
     * 测试获取所有商品列表
     */
    @Test
    void findAll() {
        ProductService service = new ProductService();
        service.init();
        assertEquals(3, service.findAll().size());
    }

    /**
     * 测试根据ID查找商品，包含正常、异常和不存在的情况
     */
    @Test
    void findById() {
        ProductService service = new ProductService();
        service.init();
        assertNotNull(service.findById(1L));
        assertEquals("iPhone16", service.findById(1L).getName());
        assertNull(service.findById(999L));
        assertNull(service.findById(null));
    }

    /**
     * 测试创建新商品，包含正常和参数为null的情况
     */
    @Test
    void createNewProduct() {
        ProductService service = new ProductService();
        service.init();
        ProductCreateFormDTO form = new ProductCreateFormDTO();
        form.setName("Test");
        form.setDescription("desc");
        form.setPrice(java.math.BigDecimal.TEN);
        form.setStock(5L);
        assertEquals(3, service.findAll().size());
        service.createNewProduct(form);
        assertEquals(4, service.findAll().size());
        assertEquals("Test", service.findAll().get(3).getName());
        assertNull(service.createNewProduct(null));
    }

    /**
     * 测试更新商品，包含正常、参数为null和商品不存在的情况
     */
    @Test
    void updateProduct() {
        ProductService service = new ProductService();
        service.init();
        ProductUpdateFormDTO form = new ProductUpdateFormDTO();
        form.setId(1L);
        form.setName("newName");
        form.setDescription("newDesc");
        form.setPrice(java.math.BigDecimal.ONE);
        form.setStock(99L);
        assertEquals("iPhone16", service.findById(1L).getName());
        service.updateProduct(form);
        assertEquals("newName", service.findById(1L).getName());
        assertEquals("newDesc", service.findById(1L).getDescription());
        assertEquals(java.math.BigDecimal.ONE, service.findById(1L).getPrice());
        assertEquals(99L, service.findById(1L).getStock());
        // null参数
        assertNull(service.updateProduct(null));
        // 不存在的id
        ProductUpdateFormDTO notExist = new ProductUpdateFormDTO();
        notExist.setId(999L);
        assertNull(service.updateProduct(notExist));
    }
}
