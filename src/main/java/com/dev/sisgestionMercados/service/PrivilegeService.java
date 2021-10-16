package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Output.PrivilegeOutput;
import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.PrivilegeRepository;


@Service
public class PrivilegeService {

	@Autowired
	private PrivilegeRepository privilegeRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public Iterable<PrivilegeOutput> getAllPrivileges() {
		List<Privilege> allPrivileges = privilegeRepository.findAll();
		List<PrivilegeOutput> allPrivilegeOutput = new ArrayList<PrivilegeOutput>();

		for (Privilege p : allPrivileges) {

			if(!p.getPrivilege().equals("ROLE_FINAL_USER")) {
				PrivilegeOutput found = new PrivilegeOutput();
				found.setPrivilege(p.getPrivilege().substring(5));
				String newNameP="";
				for (int i=0;i<found.getPrivilege().length();i++) {
					char c=found.getPrivilege().toLowerCase().charAt(i);
							if(c=='_') {
								c=' ';
							}
					newNameP=newNameP+c;
				}
				
				int size = allPrivilegeOutput.size();
				int j = 0;

				for (int i = 0; i < size; i++) {
					if (newNameP.equalsIgnoreCase(allPrivilegeOutput.get(i).getPrivilege())) {
						j = 1;
					}
				}

				if (allPrivilegeOutput.size() == 0 || j == 0) {
					found.setPrivilege(newNameP);
					allPrivilegeOutput.add(found);
				}
			}
			
			
		}
		

		return allPrivilegeOutput;
	}
	
	public Privilege getById(Integer roleId) {
		Privilege roleAct = privilegeRepository.findById(roleId).orElse(null);
	    return roleAct;
	}
	
	public Privilege save(Privilege privilege) {
		Privilege privilegeSaved = privilegeRepository.save(privilege);
	    return privilegeSaved;
	}
	
	public Privilege findByPrivilege(String privilege) {
	
		Privilege found= privilegeRepository.findByPrivilege(privilege);
		return found;
	}
	
}
