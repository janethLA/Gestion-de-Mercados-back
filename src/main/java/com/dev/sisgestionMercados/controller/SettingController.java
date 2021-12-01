package com.dev.sisgestionMercados.controller;

import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.UserInput;
import com.dev.sisgestionMercados.Output.UserOutput;
import com.dev.sisgestionMercados.entity.Setting;
import com.dev.sisgestionMercados.service.SettingService;
import com.dev.sisgestionMercados.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping("/setting")
public class SettingController {

	@Autowired
	private SettingService settingService;
	@Autowired
	private ModelMapper modelMapper;


	@PreAuthorize("hasRole('CONFIGURAR_SISTEMA')")
	@PutMapping("/registerParametersForReport")
	public ResponseEntity<?> registerParametersForReport(@RequestBody Setting setting) {

		return ResponseEntity.ok(settingService.registerParametersForReport(setting));
	}
	
	@PreAuthorize("hasRole('CONFIGURAR_SISTEMA')")
	@PutMapping("/registerGoogleMapsKey")
	public ResponseEntity<?> registerGoogleMapsKey(@RequestBody String key) {

		return ResponseEntity.ok(settingService.registerGoogleMapsKey(key));
	}
	
	@PreAuthorize("hasRole('CONFIGURAR_SISTEMA')")
	@PutMapping("/registerSearchDistance")
	public ResponseEntity<?> registerSearchDistance(@RequestBody Integer distance) {

		return ResponseEntity.ok(settingService.registerSearchDistance(distance));
	}


	@PreAuthorize("hasRole('CONFIGURAR_SISTEMA')")
	@GetMapping("/getSetting")
	public Setting getSetting() {

		return settingService.getSetting();
	}

	@PermitAll
	@GetMapping("/getGoogleMapsKey")
	public String getGoogleMapsKey() {

		return settingService.getGoogleMapsKey();
	}
	

}
