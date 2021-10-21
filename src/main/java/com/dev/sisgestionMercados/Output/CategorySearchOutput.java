package com.dev.sisgestionMercados.Output;

import java.util.List;

import com.dev.sisgestionMercados.entity.Product;

public class CategorySearchOutput {

	private int idCategory;
	private String categoryName;
	private List<ProductSearchOutput> product;
	
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<ProductSearchOutput> getProduct() {
		return product;
	}
	public void setProduct(List<ProductSearchOutput> product) {
		this.product = product;
	}
	
}
