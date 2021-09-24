package com.dev.sisgestionMercados.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Output.SectorOutput;
import com.dev.sisgestionMercados.entity.Sector;
import com.dev.sisgestionMercados.service.SectorService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/sector")
public class SectorController {

	@Autowired
	private SectorService sectorService;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@PostMapping("/createSector")
	public ResponseEntity<?> createUser(@RequestBody Sector sector){
		
		return ResponseEntity.ok(sectorService.save(sector));
	}
	@GetMapping("/allSector")
	public Iterable<SectorOutput> getAllRoles(){
		
		return sectorService.getAllSectors();
	}
}
