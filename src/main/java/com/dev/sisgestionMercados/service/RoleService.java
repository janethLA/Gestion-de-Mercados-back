package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.sisgestionMercados.Output.RoleOutput;
import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;


	@Transactional
	public Role save(Role role) {

		Role persistedRole = roleRepository.save(role);
	     for(int i=0;i<persistedRole.getPrivileges().size();i++) {
	    	 String nameP=persistedRole.getPrivileges().get(i).getPrivilege();
	    	 String newNameP="ROLE_";
				for (int j=0;j<nameP.length();j++) {
					char c= nameP.toUpperCase().charAt(j);
							if(c==' ') {
								c='_';
							}
					newNameP=newNameP+c;
				}
			 persistedRole.getPrivileges().get(i).setPrivilege(newNameP);
	     }
	    
	     return persistedRole;
	}
	
	public Iterable<RoleOutput>  getAllRoles(){
		List <Role> allRoles = roleRepository.findAll();
		List <RoleOutput> allRolesByOrder = new ArrayList<RoleOutput>();
		
		for (Role found : allRoles) {
		
			if(!found.getRoleName().equalsIgnoreCase("ADMIN")) {
				List<Privilege> privileges = new ArrayList<Privilege>();
				
				for (int i = 0; i < found.getPrivileges().size(); i++) {
					Privilege name = new Privilege();
					String privilege = found.getPrivileges().get(i).getPrivilege().substring(5);
					String newNameP = "";
					for (int j = 0; j < privilege.length(); j++) {
						char c = privilege.toLowerCase().charAt(j);
						if (c == '_') {
							c = ' ';
						}
						newNameP = newNameP + c;
					}
					name.setIdPrivilege(found.getPrivileges().get(i).getIdPrivilege());
					name.setPrivilege(newNameP);
					privileges.add(name);
				}
				RoleOutput newRole = new RoleOutput();
				newRole.setIdRole(found.getIdRole());
				newRole.setRoleName(found.getRoleName());
				newRole.setDescription(found.getDescription());
				newRole.setPrivilege(privileges);
				allRolesByOrder.add(newRole);
			}

		}

		return allRolesByOrder;	
	}
	
	public Role findById(int idRole) {
		return roleRepository.findById(idRole).get();
	}
	
	 public boolean noExistsRoleName(String roleName) {
			
			boolean result=true;
			List <Role> allRoles = roleRepository.findAll();
			for(Role a:allRoles) {
				if(a.getRoleName()!=null){
				if(a.getRoleName().equalsIgnoreCase(roleName)) {
					result=false;
				}}
			}
			return result;
		}
}
