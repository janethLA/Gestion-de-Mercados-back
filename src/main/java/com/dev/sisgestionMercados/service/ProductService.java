package com.dev.sisgestionMercados.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.Complement.Location;
import com.dev.sisgestionMercados.Input.ProductInput;
import com.dev.sisgestionMercados.Output.CategorySearchOutput;
import com.dev.sisgestionMercados.Output.ProductOutput;
import com.dev.sisgestionMercados.Output.ProductSearch;
import com.dev.sisgestionMercados.Output.ProductSearchOutput;
import com.dev.sisgestionMercados.Output.WarehouseOutput;
import com.dev.sisgestionMercados.Output.WarehouseSearch;
import com.dev.sisgestionMercados.entity.Category;
import com.dev.sisgestionMercados.entity.Price;
import com.dev.sisgestionMercados.entity.Product;
import com.dev.sisgestionMercados.entity.Role;
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
	@Autowired
	private MarketService marketService;

	
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
		if(!image.isEmpty()) {
		
			try {
				byte[] bytes=image.getBytes();
			
				newProduct.setImage(bytes);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		Product productSaved=productRepository.save(newProduct);
		price.setProduct(productSaved);
		priceService.save(price);
		
	    return p;
	}
		
	public Iterable<Product> getAllProducts(){
		List <Product> products= productRepository.findAll();
		
		return products;
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
	
	public Product findById(int idProduct) {
		return productRepository.findById(idProduct).get();
	}
	
	public Iterable<CategorySearchOutput> getAllByproductName(int id, String productName) {
		
		Warehouse found = marketService.getById(id);
		List<CategorySearchOutput> allCategorySearch = new ArrayList<CategorySearchOutput>();
	

		List<Category> categories = found.getCategory();

		for (Category foundCategory : categories) {

			CategorySearchOutput category = new CategorySearchOutput();
			category.setIdCategory(foundCategory.getIdCategory());
			category.setCategoryName(foundCategory.getCategoryName());

			List<Product> products = foundCategory.getProduct();
			List<ProductSearchOutput> allProductSearch = new ArrayList<ProductSearchOutput>();
			for (Product foundProduct : products) {

				if (foundProduct.getProductName().equalsIgnoreCase(productName)
						|| StringUtils.stripAccents(foundProduct.getProductName()).equalsIgnoreCase(productName)
						|| foundProduct.getProductName().equalsIgnoreCase(StringUtils.stripAccents(productName))) {
					ProductSearchOutput product = new ProductSearchOutput();
					product.setIdProduct(foundProduct.getIdProduct());
					product.setProductName(foundProduct.getProductName());
					product.setDescription(foundProduct.getDescription());
					product.setExpirationDate(foundProduct.getExpirationDate());
					product.setMeasurement(foundProduct.getMeasurement());
					product.setQuantity(foundProduct.getQuantity());
					product.setPrice(foundProduct.getPrice());
					product.setImage(foundProduct.getImage());
					allProductSearch.add(product);
				}
			}
			if (!allProductSearch.isEmpty()) {
				category.setProduct(allProductSearch);
				allCategorySearch.add(category);
			}
		}
		return allCategorySearch;
	}
	
	public String updatePrice(int id, Price newPrice) {
		Product product=productRepository.findById(id).get();
		List<Price> price=product.getPrice();
		newPrice.setProduct(product);
		price.add(newPrice);
		productRepository.save(product);
		for(Price p:price) {
			System.out.println("Precio : "+p.getPrice());
		}
		
		return "Se actualizo el precio del producto "+product.getProductName();
	}  
	
	public String updateImage(int id, MultipartFile image)  {
		Product product=productRepository.findById(id).get();
		try {
			product.setImage(image.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productRepository.save(product);
		
		return "Se actualizo la imagen del producto "+product.getProductName();
	} 
}
