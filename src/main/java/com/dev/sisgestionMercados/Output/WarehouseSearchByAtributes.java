package com.dev.sisgestionMercados.Output;

public class WarehouseSearchByAtributes {

	private int idMarket;
	private String warehouseName;
	private double latitude;
	private double longitude;
	
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
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
}
