package com.satwik.ecommerce.service;

import java.util.List;
import com.satwik.ecommerce.model.Product;
import com.satwik.ecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public Product addProductToDB(Product product) {
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int productId) {
        return repo.findById(productId).orElse(null);
    }

    public Product updateProductInDB(int prodId, Product product) {
            return repo.save(product);

    }

    public void deleteProduct(int prodId) {
        repo.deleteById(prodId);
    }

    public List<Product> searchProductsWithName(String name){
        return repo.searchProduct(name);
    }
}
