package com.dev.sisgestionMercados.Input;


public class MarketInput {

	private String marketName;
	private String address;
	private int idSector;
	private double latitude;
	private double longitude;
	private byte[] warehouseImage;
	
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIdSector() {
		return idSector;
	}
	public void setIdSector(int idSector) {
		this.idSector = idSector;
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
