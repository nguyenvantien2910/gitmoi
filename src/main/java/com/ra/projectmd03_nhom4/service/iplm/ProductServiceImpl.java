package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.IProductDao;
import com.ra.projectmd03_nhom4.dto.request.ProductRequest;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Autowired
    private FileService fileService;

    @Override
    public List<Product> findAll(int currentPage, int size) {
        return productDao.getAllProducts(currentPage, size);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public void saveOrUpdate(ProductRequest productRequest) {
        Product product = Product.builder()
                .productId(productRequest.getProductId())
                .productName(productRequest.getProductName())
                .productDescription(productRequest.getProductDescription())
                .sku(productRequest.getSku())
                .category(productRequest.getCategory())
                .stockQuantity(productRequest.getStockQuantity())
                .unitPrice(productRequest.getUnitPrice())
                .createdAt(productRequest.getCreatedAt())
                .updateAt(productRequest.getUpdateAt())
                .build();
        if (productRequest.getProductId() == null) {
            product.setImage(fileService.uploadFileToServer(productRequest.getFile()));
        } else {
            if (productRequest.getFile() != null && productRequest.getFile().getSize() > 0) {
                product.setImage(fileService.uploadFileToServer(productRequest.getFile()));
            } else {
                product.setImage(productDao.getImageByProductId(productRequest.getProductId()));
            }
        }
        productDao.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    @Override
    public String getImageByProductId(Long id) {
        return productDao.getImageByProductId(id);
    }

    @Override
    public Long countProduct() {
        return productDao.countAllProduct();
    }

    @Override
    public List<Product> searchProduct(String name) {
        return productDao.searchProduct(name);
    }
}
