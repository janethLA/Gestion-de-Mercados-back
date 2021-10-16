package com.dev.sisgestionMercados.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public FinalUser save(FinalUser finalUser) {
		FinalUser persistedFinalUser;
		try {
			Privilege privilege=privilegeService.findByPrivilege("ROLE_FINAL_USER");
			System.out.println("-----------:"+privilege.getPrivilege()+" y "+privilege.getIdPrivilege());
		    persistedFinalUser = finalUserRepository.save(finalUser);
			persistedFinalUser.setPrivilege(privilege);
		} catch (Exception e) {
			Privilege privilege=new Privilege();
			privilege.setPrivilege("ROLE_FINAL_USER");
			Privilege newPrivilege=privilegeService.save(privilege);
		    persistedFinalUser = finalUserRepository.save(finalUser);
			persistedFinalUser.setPrivilege(newPrivilege);
		}
	     return persistedFinalUser;
	}
	
	public FinalUser findById(int idFinalUser) {
		return finalUserRepository.findById(idFinalUser).get();
	}
}
