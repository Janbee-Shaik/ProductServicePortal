package com.sample.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.dto.ProductReqDto;
import com.sample.demo.entity.Product;
import com.sample.demo.service.ProductService;
import com.sample.demo.util.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/products")
@Api(description  = "Operations pertaining to Product Service")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@ApiOperation(value = "Create new product")
	@PostMapping("/")
	public ResponseEntity<Product> create(@RequestBody ProductReqDto productReqDto) {
		Product product =  productService.create(productReqDto);
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}
	@ApiOperation(value = "Retreive a product")
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getById(@PathVariable Long productId) {
		Product product = productService.getById(productId);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retreive all the products")
	@GetMapping("/")
	public ResponseEntity<List<Product>> getAll() {
		List<Product> productList =  productService.getAll();
		return new ResponseEntity<>(productList,HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update a product")
	@PutMapping("/{productId}")
	public ResponseEntity<String> update(@PathVariable String productId, @RequestBody Product product) {
		String message = productService.update(product);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete a product")
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> delete(@PathVariable Long productId) {
		productService.delete(productId);
		return new ResponseEntity<>(Constants.PRODUCT_DELETED,HttpStatus.OK);
	}
}
