package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.Complement.Location;
import com.dev.sisgestionMercados.Input.LocationInput;
import com.dev.sisgestionMercados.Input.MarketInput;
import com.dev.sisgestionMercados.Output.ProductSearch;
import com.dev.sisgestionMercados.Output.WarehouseOutput;
import com.dev.sisgestionMercados.Output.WarehouseSearch;
import com.dev.sisgestionMercados.Output.WarehouseSearchByAtributes;
import com.dev.sisgestionMercados.entity.Warehouse;
import com.dev.sisgestionMercados.entity.Category;
import com.dev.sisgestionMercados.entity.Product;
import com.dev.sisgestionMercados.entity.Sector;
import com.dev.sisgestionMercados.entity.Setting;
import com.dev.sisgestionMercados.repository.MarketRepository;

@Service
public class MarketService {
	
	@Autowired
	private MarketRepository marketRepository;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SettingService settingService;
	
	
	public MarketInput save(MarketInput market, MultipartFile image) {
		Warehouse newMarket=new Warehouse();
		newMarket.setWarehouseName(market.getMarketName());;
		newMarket.setAddress(market.getAddress());
		newMarket.setLatitude(market.getLatitude());
		newMarket.setLongitude(market.getLongitude());
		if(!image.isEmpty()) {
			try {
				byte[] bytes=image.getBytes();
				newMarket.setWarehouseImage(bytes);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	    marketRepository.save(newMarket);
	    putSector(market.getIdSector(),newMarket);
	    return market;
	}
	
	private void putSector(int idSector,Warehouse market) {
		Sector sector=sectorService.findById(idSector);
		market.setSector(sector);
		marketRepository.save(market);
		
	}
	
	public Iterable<WarehouseOutput>  getAllWarehouse(){
		List <Warehouse> allWarehouse = marketRepository.findAll();
		List <WarehouseOutput> allWarehousesByOrder = new ArrayList<WarehouseOutput>();
		
		for (Warehouse found : allWarehouse ) {
		
			WarehouseOutput newWarehouse = new WarehouseOutput();
			newWarehouse.setIdMarket(found.getIdMarket());
			newWarehouse.setWarehouseName(found.getWarehouseName());
			newWarehouse.setAddress(found.getAddress());
			newWarehouse.setLatitude(found.getLatitude());
			newWarehouse.setLongitude(found.getLongitude());
			newWarehouse.setWarehouseImage(found.getWarehouseImage());
			newWarehouse.setSectorName(found.getSector().getSectorName());
			allWarehousesByOrder.add(newWarehouse);
			

		}

		return allWarehousesByOrder;	
	}
	
	public Warehouse getById(Integer warehouseId) {
		Warehouse warehouse = marketRepository.findById(warehouseId).orElse(null);
	    return warehouse;
	}
	
	public Iterable<WarehouseSearch> getAllProductSearch(double longitude,double latitude,String productName) {
		
		List <Warehouse> allWarehouse = marketRepository.findAll();
		List <WarehouseSearch> allWarehouseSearch = new ArrayList<WarehouseSearch>();
		double distMax = 1000; //en metros = 1KM 
		Location userLocation=new Location(latitude,longitude);
		
		for (Warehouse found : allWarehouse ) {
			
			Location newLocation=new Location(found.getLatitude(),found.getLongitude());
			
			if( userLocation.distanceTo( newLocation ) <= distMax ){
				
				List <ProductSearch> allProductSearch = new ArrayList<ProductSearch>();
				WarehouseSearch warehouse=new WarehouseSearch();
				warehouse.setIdMarket(found.getIdMarket());
				warehouse.setLatitude(found.getLatitude());
				warehouse.setLongitude(found.getLongitude());
				List <Category>  categories=found.getCategory();
				
				for (Category category : categories) {
					
					List <Product> products=category.getProduct(); 
					
					for (Product foundProduct: products) {
						
						ProductSearch product=new ProductSearch();
						product.setIdProduct(foundProduct.getIdProduct());
						product.setProductName(foundProduct.getProductName());
						product.setDescription(foundProduct.getDescription());
						product.setExpirationDate(foundProduct.getExpirationDate());
						product.setMeasurement(foundProduct.getMeasurement());
						product.setQuantity(foundProduct.getQuantity());
						product.setCategoryName(foundProduct.getCategory().getCategoryName());
						product.setWarehouseName(foundProduct.getCategory().getWarehouse().getWarehouseName());
						product.setPrice(foundProduct.getPrice().get(foundProduct.getPrice().size()-1).getPrice());
						product.setImage(foundProduct.getImage());
						
						if(foundProduct.getProductName().equalsIgnoreCase(productName)|| StringUtils.stripAccents(foundProduct.getProductName()).equalsIgnoreCase(productName) || foundProduct.getProductName().equalsIgnoreCase(StringUtils.stripAccents(productName))) {
							allProductSearch.add(product);
						}
					}
				}
				if(!allProductSearch.isEmpty()) {
					warehouse.setProducts(allProductSearch);
					allWarehouseSearch.add(warehouse);
				}
			}	
		}
		return allWarehouseSearch;	
	}
	
	public Iterable<WarehouseSearchByAtributes> getAllWarehouseSearchBySector(String sectorName){
		
		List <Warehouse> allWarehouse = marketRepository.findAll();
		List <WarehouseSearchByAtributes> allWarehouseSearch = new ArrayList<WarehouseSearchByAtributes>();
       
		for (Warehouse found : allWarehouse ) {
			
				WarehouseSearchByAtributes warehouse=new WarehouseSearchByAtributes();
				warehouse.setIdMarket(found.getIdMarket());
				warehouse.setWarehouseName(found.getWarehouseName());
				warehouse.setLatitude(found.getLatitude());
				warehouse.setLongitude(found.getLongitude());
				warehouse.setAddress(found.getAddress());
				warehouse.setWarehouseImage(found.getWarehouseImage());
				if(found.getSector().getSectorName().equalsIgnoreCase(sectorName)) {
					allWarehouseSearch.add(warehouse);
				}
	    }
		return allWarehouseSearch;
	}
	
	public Iterable<WarehouseSearchByAtributes> getAllWarehouseSearchByLocation(LocationInput location) {
        Setting setting=settingService.findById(1);
		List<Warehouse> allWarehouse = marketRepository.findAll();
		List<WarehouseSearchByAtributes> allWarehouseSearch = new ArrayList<WarehouseSearchByAtributes>();
		double distMax = setting.getSearchDistance()*1000; // en metros = 4KM
		Location userLocation = new Location(location.getLatitude(), location.getLongitude());

		for (Warehouse found : allWarehouse) {

			Location newLocation = new Location(found.getLatitude(), found.getLongitude());

			if (userLocation.distanceTo(newLocation) <= distMax) {

				WarehouseSearchByAtributes warehouse = new WarehouseSearchByAtributes();
				warehouse.setIdMarket(found.getIdMarket());
				warehouse.setWarehouseName(found.getWarehouseName());
				warehouse.setLatitude(found.getLatitude());
				warehouse.setLongitude(found.getLongitude());
				warehouse.setAddress(found.getAddress());
				warehouse.setWarehouseImage(found.getWarehouseImage());
				allWarehouseSearch.add(warehouse);

			}
		}
		return allWarehouseSearch;
	}
	
	public Iterable<WarehouseSearchByAtributes> getSearchOfAllWarehouses() {

		List<Warehouse> allWarehouse = marketRepository.findAll();
		List<WarehouseSearchByAtributes> allWarehouseSearch = new ArrayList<WarehouseSearchByAtributes>();

		for (Warehouse found : allWarehouse) {

				WarehouseSearchByAtributes warehouse = new WarehouseSearchByAtributes();
				warehouse.setIdMarket(found.getIdMarket());
				warehouse.setWarehouseName(found.getWarehouseName());
				warehouse.setLatitude(found.getLatitude());
				warehouse.setLongitude(found.getLongitude());
				warehouse.setAddress(found.getAddress());
				warehouse.setWarehouseImage(found.getWarehouseImage());
				allWarehouseSearch.add(warehouse);
		}
		return allWarehouseSearch;
	}
	
	

}
