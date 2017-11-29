package com.assig1.business.domainModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Employee {
	@Id
	@Column
	@NotNull(message="Personal Numerical Code Cannot Be Empty!")
	private long employeePersonalNumericalCode;
	@Column
	private String employeeName;
	@Column
	private String employeeIdCardNumber;
	@ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action=OnDeleteAction.CASCADE)
	@JoinTable(name="EmployeesAndRoles",
			joinColumns=@JoinColumn(name="employee_PNC"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private Role employeeRole;
	
	@Transient
	private int employeeRoleId;
	
	@Column
	private String username;
	@Column
	private String password;
	@Column
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	
	public Employee(){}
	
	public Employee(String employeeName, String employeeIdCardNumber,
			long employeePersonalNumericalCode, Role employeeRole, int employeeRoleId, String username,
			String password, UserStatus status) {
		super();
//		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeIdCardNumber = employeeIdCardNumber;
		this.employeePersonalNumericalCode = employeePersonalNumericalCode;
		this.employeeRole = employeeRole;
		this.employeeRoleId = employeeRoleId;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeIdCardNumber() {
		return employeeIdCardNumber;
	}

	public void setEmployeeIdCardNumber(String employeeIdCardNumber) {
		this.employeeIdCardNumber = employeeIdCardNumber;
	}

	public long getEmployeePersonalNumericalCode() {
		return employeePersonalNumericalCode;
	}

	public void setEmployeePersonalNumericalCode(long employeePersonalNumericalCode) {
		this.employeePersonalNumericalCode = employeePersonalNumericalCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(Role employeeRole) {
		this.employeeRole = employeeRole;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public int getEmployeeRoleId() {
		return employeeRoleId;
	}

	public void setEmployeeRoleId(int employeeRoleId) {
		this.employeeRoleId = employeeRoleId;
	}

//	public int getEmployeeId() {
//		return employeeId;
//	}
//
//	public void setEmployeeId(int employeeId) {
//		this.employeeId = employeeId;
//	}

}
