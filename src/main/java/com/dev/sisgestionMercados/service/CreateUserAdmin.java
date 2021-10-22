package com.dev.sisgestionMercados.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.entity.UserRole;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.PrivilegeRepository;
import com.dev.sisgestionMercados.repository.RoleRepository;


@Component
public class CreateUserAdmin implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private PrivilegeRepository privilegeReposiroty;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(privilegeReposiroty.findAll().size()==0) {
			
			UserS newUser=new UserS();
			newUser.setName("Admin");
			newUser.setUserName("Admin");
			newUser.setEmail("admin@gmail.com");
			newUser.setPassword(encoder.encode("admin2021"));
			newUser.setTelephone(72792314);
			newUser.setRegistrationDate(LocalDate.now());
			UserS saveUser=userService.save(newUser);
			
			Role role=new Role();
			role.setRoleName("ADMIN");
			role.setDescription("El admin se encarga de registrar usuarios, unidades de gasto, administracion, crear nuevos roles");
			Role newRole=roleRepository.save(role);
			
			
			//Privilegios de un administrador
			Privilege privilege1=new Privilege();
			privilege1.setPrivilege("ROLE_ADMINISTRAR_USUARIOS");
			//privilegeAdmin.setIdentifier(1);
			Privilege privilege2=new Privilege();
			privilege2.setPrivilege("ROLE_ADMINISTRAR_ALMACENES");
			//privilege2.setIdentifier(2);
			Privilege privilege3=new Privilege();
			privilege3.setPrivilege("ROLE_ADMINISTRAR_CLIENTES");
			//privilege3.setIdentifier(2);
			Privilege privilege4=new Privilege();
			privilege4.setPrivilege("ROLE_ADMINISTRAR_PEDIDOS");
			//privilege4.setIdentifier(2);
			Privilege privilege5=new Privilege();
			privilege5.setPrivilege("ROLE_ADMINISTRAR_ENVIOS");
			//privilege5.setIdentifier(3);
			Privilege privilege6=new Privilege();
			privilege6.setPrivilege("ROLE_VER_REPORTES");
			//privilege6.setIdentifier(3);
			
			//Privilegios de usuario Encargado de actualizar precios
			Privilege privilege7=new Privilege();
			privilege7.setPrivilege("ROLE_VER_PRECIOS");
			//privilege7.setIdentifier(3);
			Privilege privilege8=new Privilege();
			privilege8.setPrivilege("ROLE_ACTUALIZAR_PRECIOS");
			
			//Privilegios de Delivery
			Privilege privilege9=new Privilege();
			privilege9.setPrivilege("ROLE_VER_PEDIDOS");
			
			Privilege privilegeAdmin1=privilegeReposiroty.save(privilege1);
			Privilege privilegeAdmin2=privilegeReposiroty.save(privilege2);
			Privilege privilegeAdmin3=privilegeReposiroty.save(privilege3);
			Privilege privilegeAdmin4=privilegeReposiroty.save(privilege4);
			Privilege privilegeAdmin5=privilegeReposiroty.save(privilege5);
			Privilege privilegeAdmin6=privilegeReposiroty.save(privilege6);
			
			privilegeAdmin1.setRoles(newRole);
			privilegeAdmin2.setRoles(newRole);
			privilegeAdmin3.setRoles(newRole);
			privilegeAdmin4.setRoles(newRole);
			privilegeAdmin5.setRoles(newRole);
			privilegeAdmin6.setRoles(newRole);
			
			privilegeReposiroty.save(privilegeAdmin1);
			privilegeReposiroty.save(privilegeAdmin2);
			privilegeReposiroty.save(privilegeAdmin3);
			privilegeReposiroty.save(privilegeAdmin4);
			privilegeReposiroty.save(privilegeAdmin5);
			privilegeReposiroty.save(privilegeAdmin6);
			privilegeReposiroty.save(privilege7);
			privilegeReposiroty.save(privilege8);
			privilegeReposiroty.save(privilege9);
			
			UserRole userRole=new UserRole();
			UserRole userRole2=userRoleService.save(userRole);	
			userRole2.setRole(newRole);
			userRole2.setUser(saveUser);
			//userRole2.setSector(null);
			userRoleService.save(userRole);		
		}
		
	}

}
