package com.vidisha.ecommerceproject.Controller;


import com.vidisha.ecommerceproject.Model.Product;
import com.vidisha.ecommerceproject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api") //common for all
@CrossOrigin//because browser is using different port no.
public class ProductController {

    @Autowired
    private ProductService productservice;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){  //for return the status

        return new ResponseEntity<>(productservice.getAllProducts() , HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    private ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product= productservice.getProductById(id); //because value will be fetch from the service
        if(product.getId() > 0)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/products")  //may be error mssg or may be product
    public ResponseEntity<?> addProduct(@RequestPart Product product , @RequestPart MultipartFile imageFile){
        Product savedProduct= null;
        try {
            savedProduct = productservice.addProduct(product,imageFile);
            return new ResponseEntity<>(savedProduct ,HttpStatus.CREATED);
        } catch (IOException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = productservice.getProductById(productId);
        if(product.getId() >0)
            return new ResponseEntity<>(product.getImageData(),HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id , @RequestPart Product product , @RequestPart MultipartFile imageFile) {
        Product updatedProduct = null;
        try {
            updatedProduct = productservice.updateProduct(product, imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = productservice.getProductById(id);
        if(product != null){
            productservice.deleteProduct(id);
            return new ResponseEntity<>("deleted" ,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = productservice.searchProducts(keyword);
        System.out.println("searching with" + keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
