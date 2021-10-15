package com.dev.sisgestionMercados.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.Input.ProductInput;
import com.dev.sisgestionMercados.Output.ProductOutput;
import com.dev.sisgestionMercados.Output.WarehouseOutput;
import com.dev.sisgestionMercados.entity.Category;
import com.dev.sisgestionMercados.entity.Price;
import com.dev.sisgestionMercados.entity.Product;
import com.dev.sisgestionMercados.entity.Warehouse;
import com.dev.sisgestionMercados.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PriceService priceService;

	/*public ProductOutput save(int idCategory,MultipartFile image) {
		Category category=categoryService.getById(idCategory);
		Product newProduct=new Product();
		ProductOutput p=new ProductOutput();
		//newProduct.setProductName(product.getProductName());
		//newProduct.setDescription(product.getDescription());
		//newProduct.setCategory(category);
		//List<Price> prices=new ArrayList<Price>();
		//Price price=new Price();
		//price.setPrice(product.getPrice());
		//newProduct.setPrice(prices);
		
		if(!image.isEmpty()) {
			Path path=Paths.get("src//main//resources//static//images");
			String pathAbolute=path.toFile().getAbsolutePath();
			try {
				byte[] bytes=image.getBytes();
				Path pathComplete=Paths.get(pathAbolute+"//"+image.getOriginalFilename());
				Files.write(pathComplete, bytes);
				newProduct.setImage(image.getOriginalFilename());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		productRepository.save(newProduct);
	    return p;
	}*/
	
	public ProductInput save(ProductInput product,int idCategory,MultipartFile image) {
		
		Category category=categoryService.getById(idCategory);
		Product newProduct=new Product();
		ProductInput p=new ProductInput();
		newProduct.setProductName(product.getProductName());
		newProduct.setDescription(product.getDescription());
		newProduct.setCategory(category);
		newProduct.setMeasurement(product.getMeasurement());
		newProduct.setQuantity(product.getQuantity());
		
		try {
			newProduct.setExpirationDate(product.getExpirationDate());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		List<Price> prices=new ArrayList<Price>();
		Price price=new Price();
		price.setPrice(product.getPrice());
		//newProduct.setPrice(prices);
		
		if(!image.isEmpty()) {
			//Path path=Paths.get("src//main//resources//static//images");
			//String pathAbolute=path.toFile().getAbsolutePath();
			try {
				byte[] bytes=image.getBytes();
				//Path pathComplete=Paths.get(pathAbolute+"//"+image.getOriginalFilename());
				//Files.write(pathComplete, bytes);
				newProduct.setImage(bytes);//aquiii
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		Product productSaved=productRepository.save(newProduct);
		price.setProduct(productSaved);
		priceService.save(price);
		
	    return p;
	}
	
	public Iterable<ProductOutput> getAllProducts(){
		List <Product> products= productRepository.findAll();
		List <ProductOutput> allProducts= new ArrayList<ProductOutput>();
		for(Product newProduct: products) {
			ProductOutput product=new ProductOutput();
			product.setIdProduct(newProduct.getIdProduct());
			product.setProductName(newProduct.getProductName());
			product.setDescription(newProduct.getDescription());
			product.setMeasurement(newProduct.getMeasurement());
			product.setExpirationDate(newProduct.getExpirationDate());	
			product.setQuantity(newProduct.getQuantity());
			try {
				product.setPrice(newProduct.getPrice().get(newProduct.getPrice().size()-1).getPrice());
				product.setCategoryName(newProduct.getCategory().getCategoryName());
				product.setWarehouseName(newProduct.getCategory().getWarehouse().getWarehouseName());
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			product.setImage(newProduct.getImage());
			
		    allProducts.add(product);
		}
		
		return allProducts;
	}
	
	public Iterable<String> getAllMeasurement(){
		List <Product> products= productRepository.findAll();
		List <String> allMeasurement= new ArrayList<String>();
		for(Product newProduct: products) {
			
			String measurement=newProduct.getMeasurement();
			int j = 0;

			for (int i = 0; i < allMeasurement.size(); i++) {
				if (measurement.equalsIgnoreCase(allMeasurement.get(i))) {
					j = 1;
				}
			}

			if (allMeasurement.size() == 0 || j == 0) {
				allMeasurement.add(measurement);
			}
		}
		
		return allMeasurement;
	}
}
