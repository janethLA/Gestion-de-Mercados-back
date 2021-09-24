package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Output.PrivilegeOutput;
import com.dev.sisgestionMercados.entity.Privilege;
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

			PrivilegeOutput found = new PrivilegeOutput();
			String privilege = p.getPrivilege();
			found.setPrivilege(privilege);
			int size = allPrivilegeOutput.size();
			int j = 0;

			for (int i = 0; i < size; i++) {
				if (p.getPrivilege().equalsIgnoreCase(allPrivilegeOutput.get(i).getPrivilege())) {
					j = 1;
				}
			}

			if (allPrivilegeOutput.size() == 0 || j == 0) {
				found.setPrivilege(p.getPrivilege());
				allPrivilegeOutput.add(found);
			}
			
		}
		

		return allPrivilegeOutput;
	}
	
	public Privilege getById(Integer roleId) {
		Privilege roleAct = privilegeRepository.findById(roleId).orElse(null);
	    return roleAct;
	}
	
}
