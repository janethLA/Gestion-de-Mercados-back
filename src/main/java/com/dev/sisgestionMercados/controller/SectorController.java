package com.dev.sisgestionMercados.controller;

import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PreAuthorize("hasRole('ADMINISTRAR_USUARIOS')")	
	@PostMapping("/createSector")
	public ResponseEntity<?> createSector(@RequestBody Sector sector){
		
		return ResponseEntity.ok(sectorService.save(sector));
	}
	
	@PermitAll	
	@GetMapping("/allSector")
	public Iterable<SectorOutput> getAllSectors(){
		
		return sectorService.getAllSectors();
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_USUARIOS')")	
	@GetMapping("/uniqueSectorName/{sectorName}")
	public ResponseEntity<?> uniqueSectorName(@PathVariable String sectorName){
		
		return ResponseEntity.ok(sectorService.noExistsSectorName(sectorName));//Devuelve true en caso de que el nombre es unico, devuelva false si ya existe ese nombre
	}
}
