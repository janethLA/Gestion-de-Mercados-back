package com.dev.sisgestionMercados.Output;

public class WarehouseOutput {
	
	private int idMarket;
	private String warehouseName;
	private String address;
	private double latitude;
	private double longitude;
	private byte[] warehouseImage;
	
	public int getIdMarket() {
		return idMarket;
	}
	public void setIdMarket(int idMarket) {
		this.idMarket = idMarket;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public byte[] getWarehouseImage() {
		return warehouseImage;
	}
	public void setWarehouseImage(byte[] warehouseImage) {
		this.warehouseImage = warehouseImage;
	}
	
	
}
