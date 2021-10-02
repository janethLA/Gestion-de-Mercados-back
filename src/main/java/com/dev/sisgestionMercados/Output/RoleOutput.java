package com.dev.sisgestionMercados.Output;

import java.util.List;

import com.dev.sisgestionMercados.entity.Privilege;

public class RoleOutput {

	private int idRole;
	private String roleName;
	private String description;
	private List<Privilege> privilege;
	
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Privilege> getPrivilege() {
		return privilege;
	}
	public void setPrivilege(List<Privilege> privilege) {
		this.privilege = privilege;
	}
	
}
