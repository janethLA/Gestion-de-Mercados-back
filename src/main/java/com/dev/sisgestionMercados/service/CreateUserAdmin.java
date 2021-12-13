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
			newUser.setActive(true);
			newUser.setRegistrationDate(LocalDate.now());
			UserS saveUser=userService.save(newUser);
			
			//Rol de superusuario (ADMIN)
			Role role=new Role();
			role.setRoleName("ADMIN");
			role.setDescription("El admin se encarga de registrar usuarios, administar pedidos, almacenes, configurar datos del sistema");
			Role newRole=roleRepository.save(role);
			
			//Rol de Administrador de pedidos
			Role role1=new Role();
			role1.setRoleName("Administrador de Pedidos");
			role1.setDescription("Encargado de asignar pedidos a un delivery y comprador, ver reporte de pedidos y gestionar pagos");
			Role newRole1=roleRepository.save(role1);
			
			//Rol de Delivery
			Role role2=new Role();
			role2.setRoleName("Delivery");
			role2.setDescription("Encargado de ver pedidos para entregar al usuario");
			Role newRole2=roleRepository.save(role2);
			
			//Rol de Comprador y Actualizar productos
			Role role3=new Role();
			role3.setRoleName("Comprador y Actualizador de productos");
			role3.setDescription("Encargado de actualizar precios e imagen y comprar los productos del pedido");
			Role newRole3=roleRepository.save(role3);
			
			//Rol de Delivery-Comprador
			Role role4=new Role();
			role4.setRoleName("Delivery-Comprador");
			role4.setDescription("Encargado de ver pedidos para entregar al usuario y comprar los productos del pedido");
			Role newRole4=roleRepository.save(role4);
			
			//Privilegios del SuperUsuario (ADMIN)
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
			Privilege privilege16=new Privilege();
			privilege16.setPrivilege("ROLE_ACTUALIZAR_PRECIOS");
			Privilege privilege15=new Privilege();
			privilege15.setPrivilege("ROLE_ACTUALIZAR_IMAGEN");
			
			//Privilegios de un administrador de pedidos
			Privilege privilege14=new Privilege();
			privilege14.setPrivilege("ROLE_ADMINISTRAR_PEDIDOS");
			
			
			//Privilegios de usuario Comprador y Actualizador de precios
			Privilege privilege10=new Privilege();
			privilege10.setPrivilege("ROLE_ACTUALIZAR_PRECIOS");
			Privilege privilege8=new Privilege();
			privilege8.setPrivilege("ROLE_ACTUALIZAR_IMAGEN");
			
			//Privilegios de Delivery
			Privilege privilege9=new Privilege();
			privilege9.setPrivilege("ROLE_VER_PEDIDOS");

			//Privilegios del Delivery-Comprador
			Privilege privilege11=new Privilege();
			privilege11.setPrivilege("ROLE_ACTUALIZAR_PRECIOS");
			Privilege privilege12=new Privilege();
			privilege12.setPrivilege("ROLE_ACTUALIZAR_IMAGEN");
			Privilege privilege13=new Privilege();
			privilege13.setPrivilege("ROLE_VER_PEDIDOS");
			
			
			//Privilegios Asignados al Superusuario
			privilegeReposiroty.save(privilege1);
			privilegeReposiroty.save(privilege2);
			privilegeReposiroty.save(privilege4);
			privilegeReposiroty.save(privilege6);
			privilegeReposiroty.save(privilege7);
			privilegeReposiroty.save(privilege8);
			privilegeReposiroty.save(privilege9);
			privilegeReposiroty.save(privilege9);
			privilegeReposiroty.save(privilege10);
			privilegeReposiroty.save(privilege16);
			privilegeReposiroty.save(privilege15);
			
			privilege1.setRoles(newRole);
			privilege2.setRoles(newRole);
			privilege4.setRoles(newRole);
			privilege6.setRoles(newRole);
			privilege7.setRoles(newRole);
			privilege8.setRoles(newRole);
			privilege10.setRoles(newRole);
			privilege16.setRoles(newRole);
			privilege15.setRoles(newRole);
			
			privilegeReposiroty.save(privilege1);
			privilegeReposiroty.save(privilege2);
			privilegeReposiroty.save(privilege4);
			privilegeReposiroty.save(privilege6);
			privilegeReposiroty.save(privilege7);
			privilegeReposiroty.save(privilege8);
			privilegeReposiroty.save(privilege9);
			privilegeReposiroty.save(privilege10);
			privilegeReposiroty.save(privilege16);
			privilegeReposiroty.save(privilege15);
			
			
			//Privilegios Asignados al Administrador de pedidos
			privilegeReposiroty.save(privilege14);
			privilege14.setRoles(newRole1);
			privilegeReposiroty.save(privilege14);
			
			//Privilegios Asignados al Delivery
			privilegeReposiroty.save(privilege9);
			privilege9.setRoles(newRole2);
			privilegeReposiroty.save(privilege9);
			
			//Privilegios Asignados al Comprador y Actualizar productos
			privilegeReposiroty.save(privilege10);
			privilegeReposiroty.save(privilege8);
			privilege10.setRoles(newRole3);
			privilege8.setRoles(newRole3);
			privilegeReposiroty.save(privilege10);
			privilegeReposiroty.save(privilege8);
			
			//Privilegios Asignados al Delivery-Comprador
			privilegeReposiroty.save(privilege11);
			privilegeReposiroty.save(privilege12);
			privilegeReposiroty.save(privilege13);
			privilege11.setRoles(newRole4);
			privilege12.setRoles(newRole4);
			privilege13.setRoles(newRole4);
			privilegeReposiroty.save(privilege11);
			privilegeReposiroty.save(privilege12);
			privilegeReposiroty.save(privilege13);
			
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
