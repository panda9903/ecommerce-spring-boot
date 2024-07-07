package com.satwik.ecommerce.controller;

import java.util.List;

import com.satwik.ecommerce.model.Product;
import com.satwik.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String greet(){
        return "Hello World";
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> products = service.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/products/{prodId}")
    public ResponseEntity<Product> getProduct(@PathVariable int prodId){

        Product product = service.getProductById(prodId);

        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/products/{prodId}")
    public ResponseEntity<?> updateProduct(@PathVariable int prodId, @RequestBody Product updatedProduct){
        try{

            Product product = service.getProductById(prodId);

            if(product != null){
                Product updatedProd = service.updateProductInDB(prodId, updatedProduct);
                System.out.println(updatedProd.toString());
                return new ResponseEntity<>(updatedProd, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/products/{prodId}")
    public ResponseEntity<HttpStatus> deleteProductInDB(@PathVariable int prodId){
        try{
            Product product = service.getProductById(prodId);

            if(product != null){
                service.deleteProduct(prodId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProductsWithName(@RequestParam String name){
        List<Product> products = service.searchProductsWithName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping("/products")
    public ResponseEntity<?> addProductToDB(@RequestBody  Product newProduct){
        try{
            Product product = service.addProductToDB(newProduct);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
