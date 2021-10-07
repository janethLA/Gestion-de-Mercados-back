package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.dev.sisgestionMercados.Output.RoleOutput;
import com.dev.sisgestionMercados.Output.SectorOutput;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.entity.Sector;
import com.dev.sisgestionMercados.repository.SectorRepository;


@Service
public class SectorService {

	@Autowired
	private SectorRepository sectorRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public Sector findById(int id) {
		return sectorRepository.findById(id).get();
	}
	
	public Sector save(Sector sector) {
		return sectorRepository.save(sector);
	}
	
	public Iterable<SectorOutput>  getAllSectors(){
		List <Sector> allSectors = sectorRepository.findAll();
		List <SectorOutput> allSectorsByOrder = new ArrayList<SectorOutput>();
		
		for (Sector found : allSectors ) {
		
			SectorOutput newRole = new SectorOutput();
			newRole.setIdSector(found.getIdSector());
			newRole.setSectorName(found.getSectorName());
			allSectorsByOrder.add(newRole);

		}

		return allSectorsByOrder;	
	}
	
    public boolean noExistsSectorName(String userName) {
		
		boolean result=true;
		List <Sector> allSector = sectorRepository.findAll();
		for(Sector a:allSector) {
			if(a.getSectorName()!=null){
			if(a.getSectorName().equalsIgnoreCase(userName)) {
				result=false;
			}}
		}
		return result;
	}
}
