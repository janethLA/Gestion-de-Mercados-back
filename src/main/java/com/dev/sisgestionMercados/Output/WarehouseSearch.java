package com.dev.sisgestionMercados.Output;

import java.util.List;

public class WarehouseSearch {

	private int idMarket;
	private double latitude;
	private double longitude;
	private List<ProductSearch> products;
	
	public int getIdMarket() {
		return idMarket;
	}
	public void setIdMarket(int idMarket) {
		this.idMarket = idMarket;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public List<ProductSearch> getProducts() {
		return products;
	}
	public void setProducts(List<ProductSearch> products) {
		this.products = products;
	}
	
}
