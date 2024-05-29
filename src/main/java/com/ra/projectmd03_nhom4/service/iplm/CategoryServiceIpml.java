package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.ICategoryDao;
import com.ra.projectmd03_nhom4.dto.request.CategoryDTO;
import com.ra.projectmd03_nhom4.model.Category;
import com.ra.projectmd03_nhom4.service.ICategoryService;
import com.ra.projectmd03_nhom4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceIpml implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IProductService productService;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }


    @Override
    public void saveOrUpdate(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void block(Long id) {
        categoryDao.block(id);
    }

    @Override
    public boolean checkCategoryName(String categoryName) {
        return categoryDao.checkCategoryName(categoryName);
    }

//    @Override
//    public List<CategoryDTO> findAllCategoriesWithProductCount() {
//        return categoryDao.findAllCategoriesWithProductCount();
//    }

//    @Override
//    public boolean checkProductByCategoryId(int id) {
//        List<Product> products = productService.findAll();
//        for (Product product : products) {
//            if (product.getCategory().getCategoryId() == id) {
//                return false;
//            }
//        }
//        return true;
//    }


}
