package com.dev.sisgestionMercados.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.sisgestionMercados.entity.Setting;
import com.dev.sisgestionMercados.repository.SettingRepository;


@Service
public class SettingService {
	@Autowired
	private ModelMapper modelMapper;
    @Autowired
    private SettingRepository settingRepository;
	
	
	@Transactional
	public Setting save(Setting setting) {

	    return settingRepository.save(setting);
	}
	
	public Setting findById(int id) {
		return settingRepository.findById(id).get();
	}
	
	public Setting registerParametersForReport(Setting setting) {
		
		Setting s=settingRepository.findById(1).get();
		s.setNameForReport(setting.getNameForReport());
		s.setAddresForReport(setting.getAddresForReport());
		s.setEmailForReport(setting.getEmailForReport());
		s.setTelephoneForReport(setting.getTelephoneForReport());
		return settingRepository.save(s);
	}
	
   public Setting registerGoogleMapsKey(String key) {
		
		Setting s=settingRepository.findById(1).get();
		s.setGoogleKey(key);
		return settingRepository.save(s);
	}
   
   public Setting registerSearchDistance(int distance) {
		
		Setting s=settingRepository.findById(1).get();
		s.setSearchDistance(distance);
		return settingRepository.save(s);
	}
   
   public Setting getSetting() {
		
		return settingRepository.findById(1).get();
	}
    public String getGoogleMapsKey() {
		
		Setting s=settingRepository.findById(1).get();
	
		return s.getGoogleKey();
	}
	
}
