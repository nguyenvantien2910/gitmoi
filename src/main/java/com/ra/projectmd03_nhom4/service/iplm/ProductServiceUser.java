package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.IProductDaoUser;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.service.IProductServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class ProductServiceUser implements IProductServiceUser {
    @Autowired
    private IProductDaoUser productDAOUser;

    @Override
    @Transactional
    public List<Product> findAll() {
        return productDAOUser.findAll();
    }

    @Override
    @Transactional
    public Product findById(Long id) {
        return productDAOUser.findById(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Product product) {
        productDAOUser.saveOrUpdate(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productDAOUser.deleteById(id);
    }
}
