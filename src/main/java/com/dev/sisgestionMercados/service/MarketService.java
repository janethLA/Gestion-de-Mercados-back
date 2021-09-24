package com.dev.sisgestionMercados.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.MarketInput;
import com.dev.sisgestionMercados.entity.Market;
import com.dev.sisgestionMercados.entity.Sector;
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
		Market newMarket=new Market();
		newMarket.setMarketName(market.getMarketName());;
		newMarket.setAddress(market.getAddress());
	    marketRepository.save(newMarket);
	    putSector(market.getIdSector(),newMarket);
	    return market;
	}
	
	private void putSector(int idSector,Market market) {
		Sector sector=sectorService.findById(idSector);
		market.setSector(sector);
		marketRepository.save(market);
		
	}

}
