package com.dev.sisgestionMercados.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.entity.Category;
import com.dev.sisgestionMercados.entity.Product;
import com.dev.sisgestionMercados.entity.Warehouse;

@Service
public class ProductService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryService categoryService;

	/*public Product save(Product category,int idCategory,MultipartFile image) {
		Category warehouse=categoryService.getById(idCategory);
		Product newProduct=new Product();
		if(!image.isEmpty()) {
			try {
				byte[] bytes=image.getBytes();
				Path path=Paths.get(path+"//"+image.getOriginalFilename());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		newProduct.setImage(image.getOriginalFilename());
	    return newProduct;
	}*/
}
