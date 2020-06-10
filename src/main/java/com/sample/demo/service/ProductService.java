package com.sample.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.demo.dto.ProductReqDto;
import com.sample.demo.entity.Product;
import com.sample.demo.repository.ProductRepository;
import com.sample.demo.util.Constants;

@Service
public class ProductService {

	@Autowired
	ProductRepository productsRepository;

	public Product create(ProductReqDto productReqDto) {
		Product product = new Product();
		product.setName(productReqDto.getName());
		product.setPrice(productReqDto.getPrice());
		product.setManfDate(productReqDto.getManfDate());
		product.setExpDate(productReqDto.getExpDate());
		return productsRepository.save(product);
	}

	public Product getById(Long productId) {
		Optional<Product> opt= productsRepository.findById(productId);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	public List<Product> getAll() {
		return productsRepository.findAll();
	}

	public String update(Product product) {
		Product updatedProduct = null;
		updatedProduct = productsRepository.save(product);
		if(updatedProduct != null) {
			return Constants.PRODUCT_UPDATED;
		} 
		return Constants.PRODUCT_NOT_UPDATED;
	}

	public void delete(Long productId) {
		productsRepository.deleteById(productId);
	}
}
