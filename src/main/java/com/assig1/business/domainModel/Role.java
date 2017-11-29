package com.assig1.business.domainModel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Role {
	@Id
	@Column
	private int roleId;
	@Column
	private String roleName;
	@OneToMany(mappedBy="employeeRole", cascade=CascadeType.ALL)
	private List<Employee> empl;
	public Role() {	}
	public Role(int roleId, String roleName,  List<Employee> empl) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.empl = empl;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int id) {
		this.roleId = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public  List<Employee> getEmpl() {
		return empl;
	}
	public void setEmpl( List<Employee> empl) {
		this.empl = empl;
	}
	
	
}
