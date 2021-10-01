package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.MarketInput;
import com.dev.sisgestionMercados.Output.UserOutput;
import com.dev.sisgestionMercados.Output.WarehouseOutput;
import com.dev.sisgestionMercados.entity.Warehouse;
import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.entity.Sector;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.MarketRepository;
import com.dev.sisgestionMercados.repository.SectorRepository;

@Service
public class MarketService {
	
	@Autowired
	private MarketRepository marketRepository;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private ModelMapper modelMapper;
	
	public MarketInput save(MarketInput market) {
		Warehouse newMarket=new Warehouse();
		newMarket.setWarehouseName(market.getMarketName());;
		newMarket.setAddress(market.getAddress());
		newMarket.setLatitude(market.getLatitude());
		newMarket.setLongitude(market.getLongitude());
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
			allWarehousesByOrder.add(newWarehouse);

		}

		return allWarehousesByOrder;	
	}
	
	public Warehouse getById(Integer warehouseId) {
		Warehouse warehouse = marketRepository.findById(warehouseId).orElse(null);
	    return warehouse;
	}

}
