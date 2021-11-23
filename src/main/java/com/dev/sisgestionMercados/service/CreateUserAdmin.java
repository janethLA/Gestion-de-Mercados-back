package com.dev.sisgestionMercados.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.entity.Setting;
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
	
	@Autowired
	private SettingService settingService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(privilegeReposiroty.findAll().size()==0) {
			
			UserS newUser=new UserS();
			newUser.setName("Admin");
			newUser.setUserName("Admin");
			newUser.setEmail("admin@gmail.com");
			newUser.setPassword(encoder.encode("admin2021"));
			newUser.setTelephone(76767676);
			newUser.setWhatsappLink("https://wa.me/59176767676");
			newUser.setActive(true);
			newUser.setRegistrationDate(LocalDate.now());
			UserS saveUser=userService.save(newUser);
			
			Role role=new Role();
			role.setRoleName("ADMIN");
			role.setDescription("El admin se encarga de registrar usuarios, administar pedidos, almacenes, configurar datos del sistema");
			Role newRole=roleRepository.save(role);
			
			
			//Privilegios de un administrador
			Privilege privilege1=new Privilege();
			privilege1.setPrivilege("ROLE_ADMINISTRAR_USUARIOS");
			Privilege privilege2=new Privilege();
			privilege2.setPrivilege("ROLE_ADMINISTRAR_ALMACENES");
			Privilege privilege4=new Privilege();
			privilege4.setPrivilege("ROLE_ADMINISTRAR_PEDIDOS");
			Privilege privilege6=new Privilege();
			privilege6.setPrivilege("ROLE_VER_REPORTES");
			Privilege privilege7=new Privilege();
			privilege7.setPrivilege("ROLE_CONFIGURAR_SISTEMA");
			
			//Privilegios de usuario Encargado de actualizar precios
			Privilege privilege10=new Privilege();
			privilege10.setPrivilege("ROLE_ACTUALIZAR_PRECIOS");
			Privilege privilege8=new Privilege();
			privilege8.setPrivilege("ROLE_ACTUALIZAR_IMAGEN");
			
			//Privilegios de Delivery
			Privilege privilege9=new Privilege();
			privilege9.setPrivilege("ROLE_VER_PEDIDOS");
			
			Privilege privilegeAdmin1=privilegeReposiroty.save(privilege1);
			Privilege privilegeAdmin2=privilegeReposiroty.save(privilege2);
			Privilege privilegeAdmin4=privilegeReposiroty.save(privilege4);
			Privilege privilegeAdmin6=privilegeReposiroty.save(privilege6);
			Privilege privilegeAdmin7=privilegeReposiroty.save(privilege7);
			Privilege privilegeAdmin8=privilegeReposiroty.save(privilege8);
			Privilege privilegeAdmin10=privilegeReposiroty.save(privilege10);
			
			privilegeAdmin1.setRoles(newRole);
			privilegeAdmin2.setRoles(newRole);
			privilegeAdmin4.setRoles(newRole);
			privilegeAdmin6.setRoles(newRole);
			privilegeAdmin7.setRoles(newRole);
			privilegeAdmin8.setRoles(newRole);
			privilegeAdmin10.setRoles(newRole);
			
			privilegeReposiroty.save(privilegeAdmin1);
			privilegeReposiroty.save(privilegeAdmin2);
			privilegeReposiroty.save(privilegeAdmin4);
			privilegeReposiroty.save(privilegeAdmin6);
			privilegeReposiroty.save(privilege7);
			privilegeReposiroty.save(privilege8);
			privilegeReposiroty.save(privilege9);
			privilegeReposiroty.save(privilege10);
			
			UserRole userRole=new UserRole();
			UserRole userRole2=userRoleService.save(userRole);	
			userRole2.setRole(newRole);
			userRole2.setUser(saveUser);
			userRoleService.save(userRole);		
			
			Setting setting=new Setting();
			setting.setSearchDistance(4); //4 KM
			setting.setGoogleKey("AIzaSyAlZsuin6kTiBDLiELbZhUpgAeZ6UiYgWo");
			settingService.save(setting);
		}
		
	}

}
