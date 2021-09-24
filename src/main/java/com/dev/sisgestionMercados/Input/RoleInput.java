package com.dev.sisgestionMercados.Input;

import java.util.List;

import com.dev.sisgestionMercados.entity.Privilege;

public class RoleInput {

	private String roleName;
	private String description;
	private List<Privilege> privileges;
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
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

}
