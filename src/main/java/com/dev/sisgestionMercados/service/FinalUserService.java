package com.dev.sisgestionMercados.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.sisgestionMercados.Input.FinalUserInput;
import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.OrderDetail;
import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.repository.FinalUserRepository;

@Service
public class FinalUserService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private FinalUserRepository finalUserRepository ;
	@Autowired
	private PrivilegeService privilegeService ;

	@Transactional
	public FinalUserInput save(FinalUser finalUser) {
		FinalUser persistedFinalUser;
		FinalUserInput saved=new FinalUserInput();
		String code=UUID.randomUUID().toString().toUpperCase().substring(0, 6);
		try {
			Privilege privilege=privilegeService.findByPrivilege("ROLE_FINAL_USER");
			System.out.println("-----------:"+privilege.getPrivilege()+" y "+privilege.getIdPrivilege());
			finalUser.setActive(false);
			finalUser.setCode(code);
		    persistedFinalUser = finalUserRepository.save(finalUser);
			persistedFinalUser.setPrivilege(privilege);
		} catch (Exception e) {
			Privilege privilege=new Privilege();
			privilege.setPrivilege("ROLE_FINAL_USER");
			Privilege newPrivilege=privilegeService.save(privilege);
			finalUser.setActive(false);
			finalUser.setCode(code);
		    persistedFinalUser = finalUserRepository.save(finalUser);
			persistedFinalUser.setPrivilege(newPrivilege);
		}
		saved.setIdFinalUser(persistedFinalUser.getIdFinalUser());
		saved.setCode(code);
	    return saved;
	}
	
	public FinalUser findById(long idFinalUser) {
		return finalUserRepository.findById(idFinalUser).get();
	}
	
	public String verify(FinalUserInput user) {
		String result;
		FinalUser userF=findById(user.getIdFinalUser());
		if(userF.getCode().equals(user.getCode())) {
			userF.setActive(true);
			finalUserRepository.save(userF);
			result = "verificado";
		}else {
			result="no ha sido verificado";
		}
		return result;
	}
}
