package com.dev.sisgestionMercados.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.sisgestionMercados.entity.Price;
import com.dev.sisgestionMercados.repository.PriceRepository;

@Service
public class PriceService {
	@Autowired
	private ModelMapper modelMapper;
    @Autowired
    private PriceRepository priceRepository;
	
	
	@Transactional
	public Price save(Price price) {

	    return priceRepository.save(price);
	}
}
